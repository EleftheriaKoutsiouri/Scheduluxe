package ScheduluxeClasses;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class Traveler {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String country;
    private String password;
 
    // Constructor
    public Traveler(String username, String firstname, String lastname, String email, String country, String password) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.country = country;
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
        return country;
    }
 
    public String getPassword() {
        return password;
    }

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

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPassword(String password) {
        this.password = password;
    }
 
    // Μέθοδος ανάκτησης χρήστη από τη βάση
    public static Traveler getTraveler(String username, String password) throws Exception {
        Traveler traveler = null;
        String query = "SELECT * FROM Travelers WHERE username = ? AND password = ?";
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
 
            if (rs.next()) {
                traveler = new Traveler(
                        rs.getString("username"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        rs.getString("country"),
                        rs.getString("password"));
            }
 
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return traveler;
    }
 
    // Μέθοδος αποθήκευσης ή ενημέρωσης χρήστη στη βάση
    public boolean saveOrUpdate() throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String query = """
                INSERT INTO Travelers (username, firstname, lastname, email, country, password)
                VALUES (?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE
                firstname = ?, lastname = ?, email = ?, country = ?, password = ?
                """;
 
        try {
            con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, this.username);
            pstmt.setString(2, this.firstname);
            pstmt.setString(3, this.lastname);
            pstmt.setString(4, this.email);
            pstmt.setString(5, this.country);
            pstmt.setString(6, this.password);
            pstmt.setString(7, this.firstname);
            pstmt.setString(8, this.lastname);
            pstmt.setString(9, this.email);
            pstmt.setString(10, this.country);
            pstmt.setString(11, this.password);
 
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean createTraveler() throws Exception {       // Η ΜΕΘΟΔΟΣ ΠΟΥ ΑΡΧΙΚΑ ΘΑ ΚΑΛΕΙΤΑΙ ΓΙΑ ΝΑ ΔΗΜΙΟΥΡΓΗΘΕΙ Ο ΧΡΗΣΤΗΣ ΤΟΥ SIGN UP
        DatabaseConnection db = new DatabaseConnection();    // ΕΦΟΣΟΝ ΔΗΜΙΟΥΡΓΕΙΤΑΙ ACCOUNT ΜΟΝΟ ΜΕ USERNAME,EMAIL,PASSWORD ΠΡΕΠΕΙ ΝΑ ΦΤΙΑΞΟΥΜΕ ΕΝΑΝ ΑΛΛΟ ΠΙΝΑΚΑ
                                                            // ΕΝΑΝ ΑΛΛΟ ΠΙΝΑΚΑ ΜΕ ΜΟΝΟ ΑΥΤΑ ΤΑ ΤΡΙΑ ΠΕΔΙΑ     Η    ΝΑ ΠΑΙΡΝΑΜΕ ΣΤΟΝ ΣΥΓΚΕΚΡΙΜΕΝΟ ΠΙΝΑΚΑ Travelers
                                                            // ΜΟΝΟ ΤΑ 3 ΠΕΔΙΑ ΠΟΥ ΔΙΝΕΙ ΚΑΙ ΤΑ ΥΠΟΛΟΙΠΑ (ΟΠΩΣ COUNTRY) ΝΑ ΤΑ ΠΕΡΝΑΕΙ ΩΣ NULL
        Connection con = null;
        String query = """
                INSERT INTO Travelers (username, firstname, lastname, email, country, password)
                VALUES (?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE
                firstname = ?, lastname = ?, email = ?, country = ?, password = ?
                """;
 
        try {
            con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, this.username);
            pstmt.setString(2, this.firstname);
            pstmt.setString(3, this.lastname);
            pstmt.setString(4, this.email);
            pstmt.setString(5, this.country);
            pstmt.setString(6, this.password);
            pstmt.setString(7, this.firstname);
            pstmt.setString(8, this.lastname);
            pstmt.setString(9, this.email);
            pstmt.setString(10, this.country);
            pstmt.setString(11, this.password);
 
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean signupcheck(String username, String email) throws Exception {
        boolean userexists = false;
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String query = "SELECT COUNT(*) FROM Travelers WHERE username = ? OR email = ?";
        try {
            con = db.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                userexists = true;
            }
            rs.close();
            pstmt.close();
            con.close();
        }catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
        }
        return userexists;
    }
}

