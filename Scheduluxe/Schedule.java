package Scheduluxe;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Schedule {
    // Constructor
    public Schedule() {
    }

    // Method to search activities based on user preferences
    public List<Activity> searchActivities(int destinationId, List<Integer> typeIds, int budgetId) throws Exception {
        List<Activity> activityList = new ArrayList<>();
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;

        // build the query based on how many of the "types" the user has checked
        StringBuilder sql = new StringBuilder(
                "SELECT DISTINCT a.ActivityID, a.ActivityName, a.Details, a.StartTime, a.EndTime " +
                        "FROM Activities a " +
                        "WHERE destinationID = ? AND budgetId = ? AND typeId IN (");

        for (int i = 0; i < typeIds.size(); i++) {
            sql.append("?");
            if (i < typeIds.size() - 1) {
                sql.append(",");
            }
        }
        sql.append(") ORDER BY a.StartTime");

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setInt(1, destinationId);
            stmt.setInt(2, budgetId);

            for (int i = 0; i < typeIds.size(); i++) {
                stmt.setInt(i + 3, typeIds.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Activity activity = new Activity(
                        rs.getInt("ActivityID"),
                        rs.getString("ActivityName"),
                        rs.getString("Details"),
                        rs.getString("StartTime"),
                        rs.getString("EndTime"));
                activityList.add(activity);
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            throw new Exception("Error retrieving activities: " + e.getMessage());
        } finally {
            db.close();
            if (con != null) {
                con.close();
            }
        }
        return activityList;
    }

    public Map<Integer, Map<String, Activity>> assignActivitiesToTimeSlots(List<Activity> activities, int totalDays)
            throws Exception {

        Map<Integer, Map<String, Activity>> schedule = new HashMap<>();
        String[] timeSlots = getTimeSlots();

        // Track already assigned activities to ensure uniqueness
        Set<Activity> assignedActivities = new HashSet<>();

        for (int day = 1; day <= totalDays; day++) {
            schedule.put(day, new LinkedHashMap<>());
            // use of LinkedHashMap instead of HashMap to keep the timeslots and their
            // assigned activities in order

            for (String timeSlot : timeSlots) {
                boolean activityScheduled = false;

                for (Activity activity : activities) {
                    if (assignedActivities.contains(activity)) {
                        continue; // Skip already assigned activities
                    }

                    String[] slotTimes = timeSlot.split("-");
                    String slotStartTime = slotTimes[0] + ":00";
                    String slotEndTime = slotTimes[1] + ":00";

                    // Check if activity fits in the time slot
                    if (activity.getStartTime().compareTo(slotStartTime) >= 0
                            && activity.getEndTime().compareTo(slotEndTime) <= 0) {
                        schedule.get(day).put(timeSlot, activity);
                        assignedActivities.add(activity);
                        activityScheduled = true;
                        break; // Move to the next time slot
                    }
                }

                // If no valid activity found for this time slot, fill with placeholder activity
                if (!activityScheduled) {
                    System.out.println("You should get free time");
                    // schedule.get(day).put(timeSlot, new Activity("Free Slot",
                    // timeSlot.split("-")[0], timeSlot.split("-")[1]));
                }
            }
        }

        return schedule;
    }

    public int saveSchedule(Map<Integer, Map<String, Activity>> schedule, int userId) throws Exception {
        int scheduleId = generateScheduleId();
        saveInSchedules(schedule, scheduleId);
        saveScheduleForUser(userId, scheduleId);

        return scheduleId;
    }

    // private int generateScheduleId(Connection con) throws SQLException {
    // String sql = "SELECT MAX(scheduleId) FROM Schedules";
    // PreparedStatement stmt = con.prepareStatement(sql);
    // ResultSet rs = stmt.executeQuery();
    // int newScheduleId = 1;
    // if (rs.next()) {
    // newScheduleId = rs.getInt(1) + 1;
    // }
    // rs.close();
    // stmt.close();
    // return newScheduleId;
    // }

    private int generateScheduleId() throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "SELECT MAX(scheduleId) FROM Schedules";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            int newScheduleId = 1;
            if (rs.next()) {
                newScheduleId = rs.getInt(1) + 1;
            }
            rs.close();
            stmt.close();
            db.close();

            return newScheduleId;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {

            }
        }

    }

    public Map<Integer, List<Map<String, Object>>> getScheduleForUser(int userId, int scheduleId, int totalDays)
            throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "SELECT s.ActivityID, s.Day, s.TimeSlot " +
                "FROM schedules s " +
                "JOIN schedulesbytraveler sbt ON s.ScheduleID = sbt.ScheduleID " +
                "WHERE sbt.UserID = ? AND s.ScheduleID = ?";

        Map<Integer, List<Map<String, Object>>> fullSchedule = new HashMap<>();

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, scheduleId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int activityId = rs.getInt("ActivityID");
                int day = rs.getInt("Day");
                String timeSlot = rs.getString("TimeSlot");

                Map<String, Object> activityData = new HashMap<>();
                activityData.put("ActivityID", activityId);
                activityData.put("Day", day);
                activityData.put("TimeSlot", timeSlot);

                if (!fullSchedule.containsKey(day)) {
                    fullSchedule.put(day, new ArrayList<>());
                }

                fullSchedule.get(day).add(activityData);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error retrieving schedule for user: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return fullSchedule;
    }

    private void saveInSchedules(Map<Integer, Map<String, Activity>> schedule, int scheduleId) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;

        String sql = "INSERT INTO Schedules (scheduleId, activityId, day, timeSlot) VALUES (?, ?, ?, ?)";

        try {
            con = db.getConnection();

            PreparedStatement stmt = con.prepareStatement(sql);

            for (Map.Entry<Integer, Map<String, Activity>> dayEntry : schedule.entrySet()) {
                int day = dayEntry.getKey();

                Map<String, Activity> activitiesBySlot = new LinkedHashMap<>(dayEntry.getValue());

                activitiesBySlot.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByKey()) // Ταξινόμηση βάσει του timeSlot
                        .forEachOrdered(slotEntry -> {
                            try {
                                String timeSlot = slotEntry.getKey();
                                Activity activity = slotEntry.getValue();

                                stmt.setInt(1, scheduleId); // Το ίδιο scheduleId για όλο το πρόγραμμα
                                stmt.setInt(2, activity.getActivityId()); // Το ID της δραστηριότητας
                                stmt.setInt(3, day); // Ημέρα
                                stmt.setString(4, timeSlot); // Χρονικό διάστημα

                                stmt.executeUpdate();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
            }

            stmt.close();
            db.close();
        } catch (Exception e) {
            throw new Exception("Error saving schedule to database: " + e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {

            }
        }
    }

    private void saveScheduleForUser(int userId, int scheduleId) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "INSERT INTO schedulesbytraveler (UserID, scheduleId, savedDate) VALUES (?, ?, ?)";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, userId);
            stmt.setInt(2, scheduleId);
            stmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected <= 0) {
                stmt.close();
                db.close();
                throw new Exception("Error inserting schedule for user!");
            }

            stmt.close();
            db.close();

        } catch (Exception e) {
            throw new Exception("Error inserting schedule for user: " + e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {

            }
        }
    }

    public boolean hasUserProgram(int userId) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "SELECT * FROM ismgroup38.schedulesbytraveler WHERE userId = ?;";
        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Map<String, String>> getPastSchedules(int userId) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        List<Map<String, String>> pastSchedules = new ArrayList<>();
        if (!hasUserProgram(userId)) {
            return null;
        }
        String sql = "SELECT d.DestinationName, d.DestinationPhotoPath, s.savedDate, s.scheduleId " +
                "FROM schedulesbytraveler s " +
                "INNER JOIN destinations d ON s.DestinationID = d.DestinationID " +
                "WHERE s.UserID = ? " +
                "ORDER BY s.savedDate DESC " +
                "LIMIT 2;";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Map<String, String> scheduleData = new HashMap<>();
                scheduleData.put("destinationName", rs.getString("DestinationName"));
                scheduleData.put("photoPath", rs.getString("DestinationPhotoPath"));
                scheduleData.put("savedDate", rs.getDate("savedDate").toString());
                pastSchedules.add(scheduleData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return pastSchedules;
    }

    public boolean saveFeedback(int userId, int scheduleId, String comment, int rating) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "UPDATE schedulesbytraveler SET comment = ?, rating = ?, savedDate = NOW() WHERE UserID = ? AND scheduleId = ?";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            // Ορισμός των παραμέτρων
            stmt.setString(1, comment);
            stmt.setInt(2, rating);
            stmt.setInt(3, userId);
            stmt.setInt(4, scheduleId);

            int rowsUpdated = stmt.executeUpdate();
            stmt.close();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (con != null) {
                con.close();
            }
            db.close();
        }
    }

    public Map<Integer, List<Activity>> getFullSchedule(int totalDays, List<Activity> activities) throws Exception {
        // Retrieve the assigned schedule
        Map<Integer, Map<String, Activity>> schedule = assignActivitiesToTimeSlots(activities, totalDays);

        // Prepare the full schedule in the desired format
        Map<Integer, List<Activity>> fullSchedule = new HashMap<>();
        String[] timeSlots = getTimeSlots();

        for (int day = 1; day <= totalDays; day++) {
            List<Activity> dayActivities = new ArrayList<>();

            // Fetch activities for the current day or create an empty map if no activities
            // exist
            Map<String, Activity> daySchedule = schedule.getOrDefault(day, new LinkedHashMap<>());

            for (String timeSlot : timeSlots) {
                Activity activity = daySchedule.getOrDefault(timeSlot, null);
                dayActivities.add(activity);
            }

            fullSchedule.put(day, dayActivities);
        }

        return fullSchedule;
    }

    public String[] getTimeSlots() {

        String[] TIME_SLOTS = {
                "09:00-11:00", "11:00-13:00", "13:00-15:00",
                "15:00-17:00", "17:00-19:00", "19:00-21:00"
        };

        return TIME_SLOTS;
    }

}
