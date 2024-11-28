import javax.servlet.*;
import javax.servlet.http.*;
import Scheduluxe.Schedule;

import java.io.IOException;

public class SaveDayScheduleServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Λήψη παραμέτρων από το αίτημα
        int userId = Integer.parseInt(request.getParameter("userId"));
        int day = Integer.parseInt(request.getParameter("day"));
        String[] activityIds = request.getParameterValues("activityIds");

        // Έλεγχος αν υπάρχουν δραστηριότητες
        if (activityIds == null || activityIds.length == 0) {
            response.getWriter().write("No activities selected.");
            return;
        }

        // Δημιουργία αντικειμένου Schedule
        Schedule schedule = new Schedule(userId);
        boolean success = true;

        // Αποθήκευση κάθε δραστηριότητας για την ημέρα
        for (int i = 0; i < activityIds.length; i++) {
            int activityId = Integer.parseInt(activityIds[i]);
            String timeSlot = request.getParameter("timeSlot" + i);

            // Έλεγχος αν το χρονικό διάστημα είναι έγκυρο
            if (timeSlot == null || timeSlot.isEmpty()) {
                response.getWriter().write("Time slot missing for activity " + activityId);
                return;
            }

            // Αποθήκευση του προγράμματος για την δραστηριότητα
            try {
                if (!schedule.saveSchedule(userId, activityId, day, timeSlot)) {
                    success = false;
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Απάντηση στον πελάτη
        if (success) {
            response.getWriter().write("Day schedule saved successfully!");
        } else {
            response.getWriter().write("Error saving day schedule.");
        }
    }
}
