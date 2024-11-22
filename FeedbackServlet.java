import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpSession;

import ScheduluxeClasses.DatabaseConnection;

public class FeedbackServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        String scheduleIdStr = request.getParameter("scheduleId");
        String commentText = request.getParameter("commentText");
        String ratingStr = request.getParameter("rating");

        // Ελέγχουμε αν το scheduleId είναι έγκυρο και αν υπάρχει
        if (scheduleIdStr != null && !scheduleIdStr.trim().isEmpty()) {
            try {
                int scheduleId = Integer.parseInt(scheduleIdStr);

                // Αν υπάρχει σχόλιο, το αποθηκεύουμε στη βάση
                if (commentText != null && !commentText.trim().isEmpty()) {
                    saveComment(request, response, userId, scheduleId, commentText);
                }

                // Αν υπάρχει βαθμολογία, την αποθηκεύουμε στη βάση
                if (ratingStr != null && !ratingStr.trim().isEmpty()) {
                    int rating = Integer.parseInt(ratingStr);
                    saveRating(request, response, userId, scheduleId, rating);
                }

            } catch (NumberFormatException e) {
                // Αν η τιμή του scheduleId ή rating δεν είναι έγκυρη, καταγράφουμε το σφάλμα
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("ScheduleOverall.jsp");
    }

    private void saveComment(HttpServletRequest request, HttpServletResponse response, int userId, int scheduleId,
            String commentText) throws Exception {
        String sql = "INSERT INTO Comments (user_id, schedule_id, comment_text) VALUES (?, ?, ?)";
        DatabaseConnection db = new DatabaseConnection(); // Δημιουργούμε την σύνδεση με τη βάση δεδομένων
        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, scheduleId);
            stmt.setString(3, commentText);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error saving comment: " + e.getMessage());
        } finally {
            try {
                db.close(); // Κλείνουμε τη σύνδεση με τη βάση δεδομένων
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveRating(HttpServletRequest request, HttpServletResponse response, int userId, int scheduleId,
            int rating) throws Exception {
        String sql = "INSERT INTO Ratings (user_id, schedule_id, rating) VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE rating = ?";
        DatabaseConnection db = new DatabaseConnection();
        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, scheduleId);
            stmt.setInt(3, rating);
            stmt.setInt(4, rating);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error saving rating: " + e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
