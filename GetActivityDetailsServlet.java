import javax.servlet.*;
import javax.servlet.http.*;

import Scheduluxe.Activity;

import java.io.*;
import java.util.Map;

public class GetActivityDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Λήψη της παράμετρος id από το request
        String activityIdStr = request.getParameter("id");
        if (activityIdStr == null || activityIdStr.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Activity ID is missing.");
            return;
        }

        int activityId;
        try {
            activityId = Integer.parseInt(activityIdStr);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid Activity ID.");
            return;
        }

        // Ανάκτηση του totalSchedule από το request (όπως στο JSP)
        Map<Integer, Map<String, Activity>> totalSchedule = (Map<Integer, Map<String, Activity>>) request.getAttribute("totalSchedule");

        if (totalSchedule == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error: No schedule data available.");
            return;
        }

        // Αναζήτηση για την δραστηριότητα με το activityId
        for (Map<String, Activity> daySchedule : totalSchedule.values()) {
            for (Activity activity : daySchedule.values()) {
                if (activity.getActivityId() == activityId) {
                    // Επιστροφή των λεπτομερειών της δραστηριότητας
                    response.setContentType("text/plain");
                    response.getWriter().write(activity.getDetails());
                    return;
                }
            }
        }

        // Αν δεν βρεθεί η δραστηριότητα με το activityId
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.getWriter().write("Activity not found.");
    }
}
