package Scheduluxe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TravelerService {

    // Μέθοδος ανάκτησης χρήστη από τη βάση
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
                    rs.getString("country"),
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

    public static Traveler createTraveler(String username, String email, String password) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        PreparedStatement pstmt;

        String queryOfExistance = "SELECT COUNT(*) FROM Travelers WHERE username = ? OR email = ?";
        String queryOfCreation = "INSERT INTO Travelers (username, firstname, lastname, email, country, password)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            con = db.getConnection();
            pstmt = con.prepareStatement(queryOfExistance);

            pstmt.setString(1, username);
            pstmt.setString(2, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                rs.close();
                pstmt.close();
                db.close();
                throw new Exception("The username or the email are already in use. Please type something different!");
            }

            pstmt = con.prepareStatement(queryOfCreation);

            pstmt.setString(1, username);
            pstmt.setNull(2, java.sql.Types.VARCHAR);
            pstmt.setNull(3, java.sql.Types.VARCHAR);
            pstmt.setString(4, email);
            pstmt.setNull(5, java.sql.Types.VARCHAR);
            pstmt.setString(6, password);

            pstmt.executeUpdate();

            Traveler traveler = new Traveler(
                    rs.getString("username"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("email"),
                    rs.getString("country"),
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

    /*
     * public void createTraveler(String username, String email, String password)
     * throws Exception { // Η ΜΕΘΟΔΟΣ ΠΟΥ
     * // ΑΡΧΙΚΑ ΘΑ ΚΑΛΕΙΤΑΙ
     * // ΓΙΑ ΝΑ ΔΗΜΙΟΥΡΓΗΘΕΙ
     * // Ο ΧΡΗΣΤΗΣ ΤΟΥ SIGN
     * // UP
     * DatabaseConnection db = new DatabaseConnection(); // ΕΦΟΣΟΝ ΔΗΜΙΟΥΡΓΕΙΤΑΙ
     * ACCOUNT ΜΟΝΟ ΜΕ
     * // USERNAME,EMAIL,PASSWORD ΠΡΕΠΕΙ ΝΑ ΠΕΡΝΑΜΕ ΣΤΟΝ ΣΥΓΚΕΚΡΙΜΕΝΟ
     * // ΠΙΝΑΚΑ Travelers
     * PreparedStatement pstmt = null; // ΜΟΝΟ ΤΑ 3 ΠΕΔΙΑ ΠΟΥ ΔΙΝΕΙ ΚΑΙ ΤΑ ΥΠΟΛΟΙΠΑ
     * (ΟΠΩΣ COUNTRY) ΝΑ ΤΑ ΠΕΡΝΑΕΙ ΩΣ
     * // NULL
     * Connection con = null;
     * String query =
     * "INSERT INTO Travelers (username, firstname, lastname, email, country, password)"
     * +
     * "VALUES (?, ?, ?, ?, ?, ?)";
     * 
     * try {
     * con = db.getConnection();
     * pstmt = con.prepareStatement(query);
     * pstmt.setString(1, username);
     * pstmt.setNull(2, java.sql.Types.VARCHAR);
     * pstmt.setNull(3, java.sql.Types.VARCHAR);
     * pstmt.setString(4, email);
     * pstmt.setNull(5, java.sql.Types.VARCHAR);
     * pstmt.setString(6, password);
     * 
     * pstmt.executeUpdate();
     * } catch (SQLException e) {
     * e.printStackTrace();
     * } finally {
     * if (pstmt != null)
     * pstmt.close();
     * if (con != null)
     * con.close();
     * }
     * }
     * 
     * 
     * public boolean signupcheck(String username, String email) throws Exception {
     * boolean userexists = false;
     * DatabaseConnection db = new DatabaseConnection();
     * Connection con = null;
     * String query =
     * "SELECT COUNT(*) FROM Travelers WHERE username = ? OR email = ?";
     * try {
     * con = db.getConnection();
     * PreparedStatement pstmt = con.prepareStatement(query);
     * pstmt.setString(1, username);
     * pstmt.setString(2, email);
     * ResultSet rs = pstmt.executeQuery();
     * if (rs.next() && rs.getInt(1) > 0) {
     * userexists = true;
     * }
     * rs.close();
     * pstmt.close();
     * con.close();
     * } catch (Exception e) {
     * e.printStackTrace(); // Log the error for debugging
     * }
     * return userexists;
     * }
     */

}
