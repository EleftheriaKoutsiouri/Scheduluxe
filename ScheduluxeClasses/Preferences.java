package ScheduluxeClasses;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Preferences {
    // Fetch Destinations
    public List<String> fetchDestinations() throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "SELECT destinationName FROM Destinations";
        List<String> destinations = new ArrayList<>();

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                destinations.add(rs.getString("destinationName"));
            }
            rs.close();
            stmt.close();
            return destinations;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    // Fetch Types
    public List<String> fetchTypes() throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "SELECT typeName FROM ActivityTypes";
        List<String> types = new ArrayList<>();

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                types.add(rs.getString("typeName"));
            }
            rs.close();
            stmt.close();
            return types;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    // Fetch Budgets
    public List<String> fetchBudgets() throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "SELECT budgetName FROM BudgetType";
        List<String> budgets = new ArrayList<>();

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                budgets.add(rs.getString("budgetName"));
            }
            rs.close();
            stmt.close();
            return budgets;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
}
