package Scheduluxe;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Schedule {
    // Constructor
    public Schedule(int userId) {
    }

    // Αποθήκευση προγράμματος
    public boolean saveSchedule(int userId, int activityId, int day, String timeSlot) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String query = "INSERT INTO Schedules (UserID, ActivityID, Day, time_slot) VALUES (?, ?, ?, ?)";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, userId);
            stmt.setInt(2, activityId);
            stmt.setInt(3, day);
            stmt.setString(4, timeSlot);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Αποθήκευση σχολίων και αξιολογήσεων
    public boolean saveFeedback(int scheduleId, String comment, int rating) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "UPDATE Schedules SET Comment = ?, Rate = ? WHERE ScheduleID = ?";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            // Εισαγωγή παραμέτρων
            stmt.setString(1, comment);
            stmt.setInt(2, rating);
            stmt.setInt(3, scheduleId);

            // Εκτέλεση του update
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Ανάκτηση του προγράμματος μαζί με το σχόλιο και την αξιολόγηση
    public Map<String, Object> viewSchedule(int scheduleId) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "SELECT ActivityID, Day, time_slot, Comment, Rate FROM Schedules WHERE ScheduleID = ?";

        Map<String, Object> scheduleData = new HashMap<>();

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, scheduleId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                scheduleData.put("ActivityID", rs.getInt("ActivityID"));
                scheduleData.put("Day", rs.getInt("Day"));
                scheduleData.put("TimeSlot", rs.getString("time_slot"));
                scheduleData.put("Comment", rs.getString("Comment"));
                scheduleData.put("Rate", rs.getInt("Rate"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error retrieving schedule details: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return scheduleData; // Επιστροφή του Map με τα δεδομένα
    }
}
