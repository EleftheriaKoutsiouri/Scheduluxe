import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Scheduluxe.Schedule;
import Scheduluxe.Traveler;

public class FeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Traveler traveler = (Traveler) session.getAttribute("travelerObj");

        try {
            int userId = traveler.getId(traveler.getUsername(), traveler.getPassword());
            //int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
            int scheduleId = (int)request.getAttribute("scheduleId");
            String comment = request.getParameter("commentText");
            int rating = Integer.parseInt(request.getParameter("rating"));
            Schedule schedule = new Schedule();

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/testComment.jsp");
            dispatcher.forward(request, response);

            if (comment != null && !comment.trim().isEmpty()) {
                schedule.saveFeedback(userId, scheduleId, comment, rating); // Save comment with a default rating of 0
            }

            //if (rating != 0) {
                //schedule.saveFeedback(userId, scheduleId, "", rating); // Save rating with an empty comment
            //}
        } catch (Exception e) {
            e.getMessage();
            request.setAttribute("error", e.getMessage());
            //response.sendRedirect("<%=request.getContextPath()%>/Scheduluxe/ShowOverallSchedule.jsp?error=1");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/testComment.jsp");
            dispatcher.forward(request, response);
        }
    }
}
