package ScheduluxeClasses;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Activity {
    private int activityId;
    private String activityName;
    private String details;
    private String startTime;
    private String endTime;

    // Constructor
    public Activity(int activityId, String activityName, String details, String startTime, String endTime) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.details = details;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters
    public int getActivityId() {
        return activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getDetails() {
        return details;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    // Method to search activities based on user preferences
    public static List<Activity> searchActivities(int userId) throws Exception {
        List<Activity> activityList = new ArrayList<>();
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;

        // SQL query to find matching activities based on user preferences
        String sql = "SELECT a.ActivityID, a.ActivityName, a.Details, a.StartTime, a.EndTime " +
                "FROM Activities a " +
                "JOIN Preferences p ON a.DestinationID = p.DestinationID " +
                "AND a.BudgetID = p.BudgetID " +
                "AND a.TypeID = p.TypeID " +
                "WHERE p.UserID = ? " +
                "ORDER BY a.StartTime";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
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
            if (con != null) {
                con.close();
            }
        }
        return activityList;
    }
}
