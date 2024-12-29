package NewFromApproval;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Old {
    

    public Map<Integer, Map<String, Activity>> getScheduleForUser(int userId, int scheduleId)
            throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "SELECT a.*, s.Day, s.TimeSlot " +
                "FROM schedules s " +
                "JOIN schedulesbytraveler sbt ON s.ScheduleID = sbt.ScheduleID " +
                "JOIN activities a ON s.ActivityID = a.ActivityID " +
                "WHERE sbt.UserID = ? AND s.ScheduleID = ?";

        Map<Integer, Map<String, Activity>> fullSchedule = new HashMap<>();

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, scheduleId);
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

   
    public int findDaysFromScheduleByUser(int userId, int scheduleId) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "SELECT days FROM schedulesbytraveler WHERE UserID = ? AND scheduleId = ?;";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, userId);
            stmt.setInt(2, scheduleId);

            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                rs.close();
                stmt.close();
                db.close();
                throw new Exception("Error finding the totals days because they do not exist! ");
            }
            
            int totalDays = rs.getInt("days");

            rs.close();
            stmt.close();
            db.close();

            return totalDays;

        } catch (Exception e) {
            throw new Exception("Error finding the totals days of the past schedule of the user: " + e.getMessage());
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Map<String, Object>> getPastSchedules(int userId) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        List<Map<String, Object>> pastSchedules = new ArrayList<>();

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
                Map<String, Object> scheduleData = new HashMap<>();
                scheduleData.put("destinationName", rs.getString("d.destinationName"));
                scheduleData.put("photoPath", rs.getString("d.destinationPhotoPath"));
                scheduleData.put("savedDate", rs.getDate("savedDate").toString());
                scheduleData.put("scheduleId", rs.getInt("scheduleId"));
                scheduleData.put("days", rs.getInt("days"));
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

    public String getComment(int userId, int scheduleId) throws Exception {

        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;

        String sql = "SELECT COMMENT FROM schedulesbytraveler WHERE UserID = ? AND scheduleId = ?;";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, scheduleId);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            if ((rs.getString("comment") == null) || rs.getString("comment").isEmpty()) {
                return "no comment";
            } else {
                String comment = rs.getString("comment");
                return comment;
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
        return "Share your thoughts about the schedule...";

    }

    public int getRating(int userId, int scheduleId) throws Exception {

        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;

        String sql = "SELECT RATING FROM schedulesbytraveler WHERE UserID = ? AND scheduleId = ?;";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, scheduleId);
            ResultSet rs = stmt.executeQuery();

            rs.next();

            int rating = rs.getInt("rating");
            return rating;

            
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

        return 0;

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

}

