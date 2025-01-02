import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Scheduluxe.Schedule;

public class FeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Schedule schedule = (Schedule) session.getAttribute("schedule");
        try {
            String comment = request.getParameter("comment");
            int rating = Integer.parseInt(request.getParameter("rating"));

            schedule.setComment(comment);
            schedule.setRating(rating);
            schedule.saveFeedback();

            // the session needs update since the object has changed
            session.setAttribute("schedule", schedule);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/ShowOverallSchedule.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/ShowOverallSchedule.jsp");
            dispatcher.forward(request, response);
        }
    }
}
