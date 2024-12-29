package Scheduluxe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreationSchedule {
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
            db.close();
            return destinations;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {
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
            db.close();
            return types;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {
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
            db.close();
            return budgets;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {
            }
        }
    }


    public List<Object> getDestinationInfo(int destId) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "SELECT destinationDetails, latitude, longitude, destinationName FROM Destinations WHERE destinationId = ?;";
        List<Object> info = new ArrayList<>();

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, destId);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                info.add(rs.getString("destinationDetails"));
                info.add(rs.getDouble("latitude"));
                info.add(rs.getDouble("longitude"));
                info.add(rs.getString("destinationName"));
            }

            rs.close();
            stmt.close();
            db.close();

            return info;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {
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
            db.close();
        } catch (Exception e) {
            throw new Exception("Error retrieving ID from " + tableName + ": " + e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {
            }
        }
        return id;
    }

    

    public List<Integer> getTypesIdFromDatabase(List<String> types) throws Exception {
        List<Integer> typeIds = new ArrayList<>();
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
    
        // Updated query to join with ActivityTypes table
        String query = "SELECT DISTINCT at.typeId " +
                       "FROM ActivityTypes at " +
                       "JOIN Activities a ON a.typeId = at.typeId " +
                       "WHERE at.TypeName = ?";
    
        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
    
            for (String typeName : types) {
                stmt.setString(1, typeName);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    typeIds.add(rs.getInt("typeId"));
                }
                rs.close();
            }
            stmt.close();
        } catch (Exception e) {
            throw new Exception("Error retrieving IDs: " + e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {
            }
        }
        return typeIds;
    }
    
}
