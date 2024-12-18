import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Scheduluxe.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CreationScheduleServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Traveler traveler = (Traveler) session.getAttribute("travelerObj");

        if (traveler == null) {
            request.setAttribute("error", "No traveler information found in session.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/ErrorPage.jsp");
            dispatcher.forward(request, response);
        }

        String destination = request.getParameter("destination");

        String[] typesString = request.getParameterValues("type");

        List<String> types = new ArrayList<String>();
        if (typesString != null) {
            for (String type : typesString) {
                types.add(type);
            }
        }

        String budget = request.getParameter("budget");
        String days = request.getParameter("totalDays");

        int totalDays = 0;
        boolean hasError = false;

        try {
            totalDays = Integer.parseInt(days);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid number of days provided.");
            hasError = true;
        }

        if (!hasError) {
            try {
                CreationSchedule creationSchedule = new CreationSchedule();

                int destinationId = creationSchedule.getIdFromDatabase(
                        "Destinations", "DestinationName", destination, "DestinationID");

                List<Integer> typeIds = creationSchedule.getTypesIdFromDatabase(types);

                int budgetId = creationSchedule.getIdFromDatabase(
                        "BudgetType", "BudgetName", budget, "BudgetID");

                // Create a schedule
                Schedule schedule = new Schedule();

                List<Activity> activities = schedule.searchActivities(destinationId, typeIds, budgetId);

                Map<Integer, Map<String, Activity>> totalSchedule = schedule.assignActivitiesToTimeSlots(activities,
                        totalDays);

                int userId = traveler.getId(traveler.getUsername(), traveler.getPassword());
                int scheduleId = schedule.saveSchedule(totalSchedule, userId);
                request.setAttribute("scheduleId", scheduleId);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/ShowScheduleDay.jsp");
                dispatcher.forward(request, response);

            } catch (Exception e) {
                request.setAttribute("error", "An error occurred: " + e.getMessage());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/ErrorPage.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
