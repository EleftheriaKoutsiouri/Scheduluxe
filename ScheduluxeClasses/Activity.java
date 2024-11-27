package ScheduluxeClasses;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
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
    public static List<Activity> searchActivities() throws Exception {
        List<Activity> activityList = new ArrayList<>();
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;

        // SQL query to find matching activities based on preferences
        String sql = "SELECT DISTINCT a.ActivityID, a.ActivityName, a.Details, a.StartTime, a.EndTime " +
                "FROM Activities a " +
                "JOIN TripCharacteristics t ON a.DestinationID = t.DestinationID " +
                "WHERE t.DestinationID = ? AND t.BudgetID = ? AND t.TypeID IN " +
                "ORDER BY a.StartTime";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
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
