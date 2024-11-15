package ScheduluxeClasses;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class Schedule {
    private int scheduleId;
    private List<Activity> activities;
    private String destination;
    private String budget;
    private List<String> type;
    private int days;


    // Constructor
    public Schedule(String destination, String budget, List<String> type, int days) {
        this.destination = destination;
        this.budget = budget;
        this.type = type;
        this.days = days;
        this.activities = new ArrayList<>();
    }



    // Fetch destination details -> TODO ONLY GET THE NAMES
    public String fetchDestination() {
        String destination = "";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT name FROM Destinations")) {
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

    /*
     * we need to:
     * 1) match the preferences of the user with the activities of the database
     * through a sql query and then
     * 2) take the data we want to show in the schdule such as (activityName,
     * details) and then
     * 3) take the data we want to have for the logic IN THE BACKGROUND to work.
     * i) This means that we take the name or coordinates depending the api's and do
     * the logic of the maps
     * ii) take the available hours of each activity and find the optimal overall
     * programme that will match each activity through all of the days of the
     * journey (this is the logic of the whole programme!!!!!)
     * NOTE: we have to figure out what will happen if we make the query and the
     * cativities are many but the travel is only one day. How do we choose?
     */

    public void viewMap() {
        // Code to integrate map display here
    }
}
