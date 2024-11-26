import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ScheduluxeClasses.Schedule;

public class FeedbackServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve user ID from session
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
        String commentText = request.getParameter("commentText");
        String ratingStr = request.getParameter("star");

        try {
            // Create a Schedule object to save feedback
            Schedule schedule = new Schedule(userId);

            // Save the comment if provided
            if (commentText != null && !commentText.trim().isEmpty()) {
                schedule.saveFeedback(scheduleId, commentText, 0); // Save comment with a default rating of 0
            }

            // Save the rating if provided
            if (ratingStr != null && !ratingStr.trim().isEmpty()) {
                int rating = Integer.parseInt(ratingStr); // Parse rating
                schedule.saveFeedback(scheduleId, "", rating); // Save rating with an empty comment
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("ScheduleOverall.jsp"); // Redirect to the schedule overview page
    }
}
