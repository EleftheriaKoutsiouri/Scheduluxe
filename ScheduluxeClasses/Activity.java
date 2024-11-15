package ScheduluxeClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    // getters
    public String getActivityName() {
        return activityName;
    }

    public String getDetails() {
        return details;
    }

    // Search Activities
    public List<Activity> searchActivities() {
        List<Activity> activityList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT * FROM Activities WHERE destination = ? AND budget = ? AND type IN (?)")) {
            stmt.setString(1, destination);
            stmt.setString(2, budget);
            stmt.setString(3, String.join(",", type)); // Μετατρέπουμε τη λίστα τύπων σε string
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Activity activity = new Activity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("startTime"),
                        rs.getString("endTime"));
                activityList.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activityList;
    }

}
