package Scheduluxe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TravelerService {

    // get the Traveler if has sign in with the right credentials
    public static Traveler getTraveler(String username, String password) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;

        String query = "SELECT * FROM Travelers WHERE username = ? AND password = ?";

        try {
            con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                rs.close();
                pstmt.close();
                db.close();
                throw new Exception("Wrong username or password, please try again!");
            }

            Traveler traveler = new Traveler(
                    rs.getString("username"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("email"),
                    rs.getString("origincountry"),
                    rs.getString("password"));

            rs.close();
            pstmt.close();
            db.close();

            return traveler;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {

            }
        }
    }

    // check if the user with this username or email exists
    private static boolean userExists(String username, String email) throws Exception {

        boolean userExists = false;

        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String query = "SELECT * FROM Travelers WHERE username = ? OR email = ?";

        try {
            con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, username);
            pstmt.setString(2, email);
        
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                userExists = true;
                return userExists;
            }

            rs.close();
            pstmt.close();
            con.close();

            return userExists;


        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {

            }
        }
    }

    //create Traveler when has sign up and the username or email do not already exist
    public static Traveler createTraveler(String username, String email, String password) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;

        String query = "INSERT INTO Travelers (username, password, email, firstname, lastname, origincountry)" +
                "VALUES (?, ?, ?, ?, ?, ?);";

        try {
            boolean userexists = userExists(username,email);

            if (userexists) {
                db.close();
                throw new Exception("The username or the email are already in use. Please try again!");
            }

            con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.setNull(4, java.sql.Types.VARCHAR);
            pstmt.setNull(5, java.sql.Types.VARCHAR);
            pstmt.setNull(6, java.sql.Types.VARCHAR);
            

            pstmt.executeUpdate();

            Traveler traveler = new Traveler(username, null, null, email, null, password);

            pstmt.close();
            db.close();

            return traveler;

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
