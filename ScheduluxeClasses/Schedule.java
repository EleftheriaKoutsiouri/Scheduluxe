package ScheduluxeClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Schedule {

    private int scheduleId;
    private String destination;
    private int days;
    private String type;
    private String budget;
    private String SavedDate;

    // Constructor
    public Schedule() {
    }

    public Schedule(int scheduleId, String destination, int days, String type, String budget, String SavedDate) {
        this.scheduleId = scheduleId;
        this.destination = destination;
        this.days = days;
        this.type = type;
        this.budget = budget;
        this.SavedDate = SavedDate;
    }

    // Getters and Setters
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getSavedDate() {
        return SavedDate;
    }

    public void setSavedDate(String SavedDate) {
        this.SavedDate = SavedDate;
    }

    // Method to retrieve past schedules for a specific user
    public static List<Schedule> fetchPastSchedules(int travelerId) {
        List<Schedule> schedules = new ArrayList<>();

        // SQL query to fetch past schedules
        String query = "SELECT * FROM past_schedules WHERE traveler_id = ?";

        try (Connection con = new DatabaseConnection().getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, travelerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Schedule schedule = new Schedule(
                        rs.getInt("schedule_id"),
                        rs.getString("destination"),
                        rs.getInt("days"),
                        rs.getString("type"),
                        rs.getString("budget"),
                        rs.getString("SavedDate"));
                schedules.add(schedule);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return schedules;
    }

    // Method to store a new schedule in the database
    public boolean save(int travelerId) {
        String query = "INSERT INTO past_schedules (traveler_id, destination, days, type, budget, SavedDate) " +
                "VALUES (?, ?, ?, ?, ?, NOW())";

        try (Connection con = new DatabaseConnection().getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, travelerId);
            ps.setString(2, this.destination);
            ps.setInt(3, this.days);
            ps.setString(4, this.type);
            ps.setString(5, this.budget);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
