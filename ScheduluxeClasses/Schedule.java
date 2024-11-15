package ScheduluxeClasses;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    // Attributes
    private List<String> destination;
    private int days;
    private List<String> type;
    private List<String> budget;

    // Default Constructor
    public Schedule() {
        this.destination = new ArrayList<>();
        this.days = 0;
        this.type = new ArrayList<>();
        this.budget = new ArrayList<>();
    }

    // Fetch Destinations
    public List<String> fetchDestinations() {
        List<String> destinations = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT destinationName FROM Destinations")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                destinations.add(rs.getString("destinationName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destinations;
    }

    // Fetch Types
    public List<String> fetchTypes() {
        List<String> types = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT typeName FROM ActivityTypes")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                types.add(rs.getString("typeName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    // Fetch Budgets
    public List<String> fetchBudgets() {
        List<String> budgets = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT budgetName FROM BudgetType")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                budgets.add(rs.getString("budgetName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return budgets;
    }

    // Getters
    public List<String> getDestinations() {
        return fetchDestinations();
    }

    public List<String> getPreferences() {
        return fetchTypes();
    }

    public List<String> getBudget() {
        return fetchBudgets();
    }
}
