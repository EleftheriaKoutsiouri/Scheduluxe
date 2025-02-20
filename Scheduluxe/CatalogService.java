package Scheduluxe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CatalogService {

    // Fetch Destinations from DB
    public List<Destination> fetchDestinations() throws Exception {
        
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "SELECT destinationId, destinationName FROM Destinations";

        List<Destination> destinations = new ArrayList<Destination>();

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Destination dest = new Destination(
                    rs.getInt("destinationId"), rs.getString("destinationName")
                );
                destinations.add(dest);
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

    // Fetch Types from DB
    public List<Type> fetchTypes() throws Exception {
        
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "SELECT * FROM ActivityTypes";

        List<Type> types = new ArrayList<Type>();

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Type type = new Type(
                    rs.getInt("typeId"), rs.getString("typeName")
                );
                types.add(type);
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

    // Fetch Budgets from DB
    public List<Budget> fetchBudgets() throws Exception {
        
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "SELECT * FROM BudgetType";

        List<Budget> budgets = new ArrayList<Budget>();

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Budget bud = new Budget(
                    rs.getInt("budgetId"), rs.getString("budgetName")
                );
                budgets.add(bud);
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

    public Destination fetchDestinationInfo(int destId) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "SELECT destinationDetails, latitude, longitude, destinationName FROM Destinations WHERE destinationId = ?;";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, destId);
            
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                rs.close();
                stmt.close();
                db.close();
                throw new Exception("Could not find the destination with this id");
            }

            Destination destination = new Destination(
                    rs.getString("destinationName"),
                    rs.getString("destinationDetails"),
                    rs.getFloat("latitude"),
                    rs.getFloat("longitude")
            );

            rs.close();
            stmt.close();
            db.close();

            return destination;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {
            }
        }
    }
}

