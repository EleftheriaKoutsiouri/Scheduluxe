package Scheduluxe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScheduleDAO {

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

    public Schedule createSchedule(List<Activity> activities, int totalDays, int userId)
            throws Exception {

        int scheduleId = generateScheduleId();
        Map<Integer, Map<String, Activity>> schedule = new HashMap<>();
        String[] timeSlots = Schedule.TIMESLOTS;

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
            }
        }

        Schedule overallSchedule = new Schedule(scheduleId, schedule, userId, totalDays);
        return overallSchedule;
    }

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
    

    private boolean hasUserProgram(int userId) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "SELECT * FROM schedulesbytraveler WHERE userId = ?;";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    
    public List<Schedule> fetchPastSchedules(int userId) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        List<Schedule> pastSchedules = new ArrayList<>();

        if (!hasUserProgram(userId)) {
            return null;
        }

        String sql = 
            "SELECT DISTINCT s.scheduleId, st.savedDate, d.destinationName, d.destinationPhotoPath, st.days "
            + "FROM schedulesbytraveler st "
            + "JOIN schedules s ON s.scheduleId = st.scheduleId " 
            + "JOIN activities a ON a.activityId = s.activityId " 
            + "JOIN destinations d ON d.destinationId = a.destinationID " 
            + "WHERE st.userId = ? ORDER BY savedDate DESC LIMIT 2;";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Schedule schedule = new Schedule(
                    rs.getInt("scheduleId"),
                    rs.getInt("days"),
                    rs.getDate("savedDate").toString(),
                    new Destination(
                        rs.getString("d.destinationName"), 
                        rs.getString("d.destinationPhotoPath")
                    )  
                );
                pastSchedules.add(schedule);
            }

            stmt.close();
            rs.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (Exception e){
            }
        }

        return pastSchedules;
           
    }


    private Map<Integer, Map<String, Activity>> fetchOverallSchedule(int scheduleId) throws Exception {

        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "SELECT a.*, s.Day, s.TimeSlot " +
                "FROM schedules s " +
                "JOIN activities a ON s.ActivityID = a.ActivityID " +
                "WHERE s.ScheduleID = ?";

        Map<Integer, Map<String, Activity>> fullSchedule = new HashMap<>();

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, scheduleId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int activityId = rs.getInt("ActivityID");
                String activityName = rs.getString("ActivityName");
                String details = rs.getString("Details");
                String timeSlot = rs.getString("TimeSlot");
                String startTime = rs.getString("StartTime");
                String endTime = rs.getString("EndTime");
                int day = rs.getInt("Day");

                Activity activity = new Activity(activityId, activityName, details, startTime, endTime);
                fullSchedule.putIfAbsent(day, new HashMap<>());
                fullSchedule.get(day).put(timeSlot, activity);
            }

            rs.close();
            stmt.close();
            db.close();

            return fullSchedule;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {
            }
        }
    }

    public Schedule fetchPastScheduleById(int scheduleId) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        Schedule pastSchedule = null;

        String sql = "SELECT days, scheduleId, comment, rating, userId FROM schedulesbytraveler WHERE scheduleId = ?;";

        try {
            Map<Integer, Map<String, Activity>> overallSchedule = fetchOverallSchedule(scheduleId);

            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, scheduleId);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                stmt.close();
                rs.close();
                db.close();
                throw new Exception("Could not find the specific past schedule by this id");
            }

            pastSchedule = new Schedule(
                    rs.getInt("scheduleId"),
                    overallSchedule,
                    rs.getInt("userID"),
                    rs.getInt("days"),
                    rs.getString("comment"),
                    rs.getInt("rating")
            );
            
            stmt.close();
            rs.close();
            db.close();

            return pastSchedule;

        } catch (Exception e) {
            throw new Exception("something went wrong" + e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e){
            }
        }

        
    }
}
