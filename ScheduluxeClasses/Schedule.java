package ScheduluxeClasses;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class Schedule {
    private int scheduleId;
    private List<Activity> activities;
    private int destinationId;
    private int budgetId;
    private List<Integer> typeIds;
    private int days;

    // Constructor
    public Schedule(int scheduleId, int destinationId, int budgetId, List<Integer> typeIds, int days) {
        this.scheduleId = scheduleId;
        this.destinationId = destinationId;
        this.budgetId = budgetId;
        this.typeIds = typeIds;
        this.days = days;
        this.activities = new ArrayList<>();
    }

    // Fetch destination details
    public String fetchDestination() {
        String destination = "";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT name FROM Destinations WHERE id = ?")) {
            stmt.setInt(1, destinationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                destination = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destination;
    }

    // Fetch budget details
    public String fetchBudget() {
        String budget = "";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT range FROM Budgets WHERE id = ?")) {
            stmt.setInt(1, budgetId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                budget = rs.getString("range");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return budget;
    }

    // Fetch types based on type IDs
    public List<String> fetchType() {
        List<String> types = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT type FROM Types WHERE id = ?")) {
            for (Integer typeId : typeIds) {
                stmt.setInt(1, typeId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    types.add(rs.getString("type"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    // Search activities based on criteria
    public List<Activity> searchActivities() {
        List<Activity> activityList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn
                        .prepareStatement("SELECT * FROM Activities WHERE destination_id = ? AND budget_id = ?")) {
            stmt.setInt(1, destinationId);
            stmt.setInt(2, budgetId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Activity activity = new Activity(rs.getInt("id"), rs.getString("name"), rs.getString("type"),
                        rs.getTime("startTime"), rs.getTime("endTime"));
                activityList.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activityList;
    }

    public void viewMap() {
        // Code to integrate map display here
    }

    // Comment and rate (Implementation depends on the comment and rating system)
    public void commentRate() {
        // Code to handle comments and ratings
    }
}
