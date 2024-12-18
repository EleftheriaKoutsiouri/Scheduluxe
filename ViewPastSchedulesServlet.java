import java.io.*;
import java.util.List;
import java.util.Map;
import Scheduluxe.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ViewPastSchedulesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Traveler traveler = (Traveler) session.getAttribute("travelerObj");
        int userId;
        try {
            Schedule schedule = new Schedule();
            userId = traveler.getId(traveler.getUsername(), traveler.getPassword());
            List<Map<String, String>> pastSchedules = schedule.getPastSchedules(userId);
            request.setAttribute("pastSchedules", pastSchedules);

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("EditProfile.jsp").forward(request, response);
    }
}