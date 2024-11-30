package Scheduluxe;

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

    public int getIdFromDatabase(String tableName, String columnName, String value, String idColumn) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        int id = 0;

        String query = "SELECT " + idColumn + " FROM " + tableName + " WHERE " + columnName + " = ?";
        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, value);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt(1);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            throw new Exception("Error retrieving ID from " + tableName + ": " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return id;
    }

    public int getDays(int Days) {
        return Days;
    }
}
