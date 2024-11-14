import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpSession;

@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        String scheduleIdStr = request.getParameter("scheduleId");
        String commentText = request.getParameter("commentText");
        String ratingStr = request.getParameter("rating");

        try (Connection conn = DatabaseConnection.getConnection()) {
            if (scheduleIdStr != null) {
                int scheduleId = Integer.parseInt(scheduleIdStr);

                // Handle comment submission
                if (commentText != null && !commentText.trim().isEmpty()) {
                    saveComment(conn, userId, scheduleId, commentText);
                }

                // Handle rating submission
                if (ratingStr != null && !ratingStr.trim().isEmpty()) {
                    int rating = Integer.parseInt(ratingStr);
                    saveRating(conn, userId, scheduleId, rating);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("ScheduleOverall.jsp");
    }

    private void saveComment(Connection conn, int userId, int scheduleId, String commentText) throws Exception {
        String sql = "INSERT INTO Comments (user_id, schedule_id, comment_text) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, scheduleId);
            stmt.setString(3, commentText);
            stmt.executeUpdate();
        }
    }

    private void saveRating(Connection conn, int userId, int scheduleId, int rating) throws Exception {
        String sql = "INSERT INTO Ratings (user_id, schedule_id, rating) VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE rating = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, scheduleId);
            stmt.setInt(3, rating);
            stmt.setInt(4, rating);
            stmt.executeUpdate();
        }
    }
}

// DATABASE TABLES
// CREATE TABLE Comments (
// comment_id INT PRIMARY KEY AUTO_INCREMENT,
// user_id INT,
// schedule_id INT,
// comment_text TEXT,
// comment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
// FOREIGN KEY (user_id) REFERENCES Travellers(user_id),
// FOREIGN KEY (schedule_id) REFERENCES Itinerary(schedule_id)
// );

// CREATE TABLE Ratings (
// rating_id INT PRIMARY KEY AUTO_INCREMENT,
// user_id INT,
// schedule_id INT,
// rating INT CHECK (rating BETWEEN 1 AND 5),
// rating_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
// FOREIGN KEY (user_id) REFERENCES Travellers(user_id),
// FOREIGN KEY (schedule_id) REFERENCES Itinerary(schedule_id),
// UNIQUE (user_id, schedule_id) -- Prevents duplicate ratings from the same
// user for the same schedule
// );
