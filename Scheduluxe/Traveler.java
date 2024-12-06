package Scheduluxe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Traveler {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String originCountry;
    private String password;

    // Constructor
    public Traveler(String username, String firstname, String lastname, String email, String originCountry, String password) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.originCountry = originCountry;
        this.password = password;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return originCountry;
    }

    public String getPassword() {
        return password;
    }

    // getter that has retrieve the id of the user from the database
    public int getId(String username, String password) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        int id;

        String query = "SELECT userid FROM Travelers WHERE username = ? AND password = ?";

        try {
            con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                throw new Exception("The user with this id does not exist");
            }

            id = rs.getInt(1);

            rs.close();
            pstmt.close();
            db.close();

            return id;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {

            }
        }
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // TODO
    // Μέθοδος αποθήκευσης ή ενημέρωσης χρήστη στη βάση
    public boolean saveOrUpdate() throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String query = "INSERT INTO Travelers (username, firstname, lastname, email, originCountry, password) " +
               "VALUES (?, ?, ?, ?, ?, ?) " +
               "ON DUPLICATE KEY UPDATE " +
               "firstname = VALUES(firstname), lastname = VALUES(lastname), email = VALUES(email), originCountry = VALUES(originCountry), password = VALUES(password)";


        /*"INSERT INTO Travelers (username, firstname, lastname, email, country, password) " +
                   "VALUES (?, ?, ?, ?, ?, ?) " +
                   "ON DUPLICATE KEY UPDATE " +
                   "firstname = VALUES(firstname), " +
                   "lastname = VALUES(lastname), " +
                   "email = VALUES(email), " +
                   "country = VALUES(country), " +
                   "password = VALUES(password)"; */
        /*
         * "INSERT INTO Travelers (username, firstname, lastname, email, country,
         * password)
         * VALUES (?, ?, ?, ?, ?, ?)
         * ON DUPLICATE KEY UPDATE
         * firstname = ?, lastname = ?, email = ?, country = ?, password = ?
         * ";
         */

        try {
            con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, this.username);
            pstmt.setString(2, this.firstname);
            pstmt.setString(3, this.lastname);
            pstmt.setString(4, this.email);
            pstmt.setString(5, this.originCountry);
            pstmt.setString(6, this.password);

            int rowsAffected = pstmt.executeUpdate();

            // Αν εκτελέστηκε η ενημέρωση ή η εισαγωγή, επιστρέφει true
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
