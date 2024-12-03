import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import Scheduluxe.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CreationScheduleServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   // Initialize variables to store form parameters
        String destination = request.getParameter("destination");
        String[] typeIdsParam = request.getParameterValues("typeId");
        List<Integer> typeIds = new ArrayList<Integer>(); // Use explicit generic type for older Java versions
        if (typeIdsParam != null) {
            for (String typeIdStr : typeIdsParam) {
                typeIds.add(Integer.parseInt(typeIdStr));
            }
        }

        String budget = request.getParameter("budget");
        String daysParam = request.getParameter("totalDays");

        int totalDays = 0;
        boolean hasError = false;

        // Validate the number of days input
        try {
            totalDays = Integer.parseInt(daysParam);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid number of days provided.");
            hasError = true;
        }

        if (!hasError) {
            try {
                // Retrieve IDs from the database
                CreationSchedule creationSchedule = new CreationSchedule();

                int destinationId = creationSchedule.getIdFromDatabase(
                    "Destinations", "DestinationName", destination, "DestinationID"
                );

                // Assuming you get `types` from user input elsewhere
                String[] typesArray = request.getParameterValues("type");
                List<String> types = typesArray != null ? Arrays.asList(typesArray) : new ArrayList<String>();
                
                typeIds = creationSchedule.getTypesIdFromDatabase(types);

                int budgetId = creationSchedule.getIdFromDatabase(
                    "BudgetType", "BudgetName", budget, "BudgetID"
                );

                // Create a schedule
                Schedule schedule = new Schedule();

                List<Activity> activities = schedule.searchActivities(destinationId, typeIds, budgetId);

                Map<Integer, Map<String, Activity>> totalSchedule = schedule.assignActivitiesToTimeSlots(activities, totalDays);
                schedule.saveSchedule(totalSchedule);
                request.setAttribute("totalSchedule",totalSchedule);
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
