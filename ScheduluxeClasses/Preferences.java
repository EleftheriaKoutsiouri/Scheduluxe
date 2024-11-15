package ScheduluxeClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Preferences {

    public boolean saveUserPreferences(int userId, String destination, int days, String[] tripType, String budget) {
        // Μετατροπή της λίστας tripType σε μία ενιαία συμβολοσειρά επειδή ο χρηστής
        // μπορεί να διαλέξει πολλά types
        String tripTypeStr = String.join(", ", tripType);

        String sql = "INSERT INTO Preferences (userId, destination, days, tripType, budget) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId); // Χρήστης
            stmt.setString(2, destination); // Προορισμός
            stmt.setInt(3, days); // Ημέρες
            stmt.setString(4, tripTypeStr); // Συμβολοσειρά με τα είδη ταξιδιών
            stmt.setString(5, budget); // Προϋπολογισμός

            int rowsAffected = stmt.executeUpdate(); // Εκτέλεση του query
            return rowsAffected > 0; // Αν έχουν επηρεαστεί γραμμές, επιστρέφουμε true
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Αν υπάρχει σφάλμα, επιστρέφουμε false
        }
    }

}
