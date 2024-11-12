import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class GetActivityDetailsServlet extends HttpServlet {
    protected void dopost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String activityIdParam = request.getParameter("id");
        int activityId = Integer.parseInt(activityIdParam);

        // Retrieve activity details (simulating here, replace with actual method)
        Activity activity = Schedule.getActivityById(activityId);

        // Send back the activity details as plain text or HTML
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<strong>" + activity.getActivityName() + "</strong><br>");
            out.println(activity.getDescription()); 
        }
    }
}
