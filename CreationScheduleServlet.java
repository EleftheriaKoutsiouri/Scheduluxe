import java.io.*;
import java.util.List;
import java.util.Map;

import Scheduluxe.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class CreationScheduleServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Ανάκτηση παραμέτρων από τη φόρμα
    String destination = request.getParameter("destination");
    String type = request.getParameter("type");
    String budget = request.getParameter("budget");
    int totalDays = Integer.parseInt(request.getParameter("totalDays"));

    try {
      // Ανάκτηση IDs από τη βάση
      Preferences preferences = new Preferences();
      int destinationId = preferences.getIdFromDatabase("Destinations", "DestinationName", destination,
          "DestinationID");
      int typeId = preferences.getIdFromDatabase("ActivityTypes", "TypeName", type, "typeID");
      int budgetId = preferences.getIdFromDatabase("BudgetType", "BudgetName", budget, "BudgetID");

      // Δημιουργία προγράμματος
      Schedule schedule = new Schedule();
      List<Activity> activities = schedule.searchActivities(destinationId, typeId, budgetId);
      Map<Integer, Map<String, Activity>> totalSchedule = schedule.assignActivitiesToTimeSlots(activities, totalDays);

      // Αποθήκευση προγράμματος στη βάση
      schedule.saveSchedule(totalSchedule);

      // Προσθήκη δεδομένων στο request scope για προβολή στο JSP
      request.setAttribute("totalSchedule", totalSchedule);
      request.setAttribute("activities", activities);

      // Προώθηση στη JSP για προβολή
      RequestDispatcher dispatcher = request.getRequestDispatcher("ShowScheduleDay.jsp");
      dispatcher.forward(request, response);

    } catch (Exception e) {
      // Διαχείριση σφαλμάτων
      request.setAttribute("error", "An error occurred: " + e.getMessage());
      RequestDispatcher dispatcher = request.getRequestDispatcher("ErrorPage.jsp");
      dispatcher.forward(request, response);
    }
  }
}
