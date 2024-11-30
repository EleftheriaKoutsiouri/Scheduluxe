import java.io.*;
import java.util.List;
import java.util.Map;
import Scheduluxe.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CreationScheduleServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Εκτύπωση debug μηνυμάτων
    System.out.println("CreationScheduleServlet: ξεκίνησε η επεξεργασία του αιτήματος POST.");

    // Ανάκτηση παραμέτρων από τη φόρμα
    String destination = request.getParameter("destination");
    String type = request.getParameter("type");
    String budget = request.getParameter("budget");
    String daysParam = request.getParameter("totalDays");

    System.out.println("Παράμετροι: Destination=" + destination + ", Type=" + type + ", Budget=" + budget
        + ", TotalDays=" + daysParam);

    int totalDays;
    try {
      totalDays = Integer.parseInt(daysParam);
    } catch (NumberFormatException e) {
      System.err.println("Σφάλμα: Το TotalDays δεν είναι έγκυρος αριθμός. Τιμή: " + daysParam);
      request.setAttribute("error", "Invalid number of days provided.");
      RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/ErrorPage.jsp");
      dispatcher.forward(request, response);
      return;
    }

    try {
      // Ανάκτηση IDs από τη βάση
      CreationSchedule creationSchedule = new CreationSchedule();
      System.out.println("Ανάκτηση IDs από τη βάση δεδομένων...");

      int destinationId = creationSchedule.getIdFromDatabase("Destinations", "DestinationName", destination,
          "DestinationID");
      System.out.println("Destination ID: " + destinationId);

      int typeId = creationSchedule.getIdFromDatabase("ActivityTypes", "TypeName", type, "typeID");
      System.out.println("Type ID: " + typeId);

      int budgetId = creationSchedule.getIdFromDatabase("BudgetType", "BudgetName", budget, "BudgetID");
      System.out.println("Budget ID: " + budgetId);

      // Δημιουργία προγράμματος
      Schedule schedule = new Schedule();
      System.out.println("Αναζήτηση δραστηριοτήτων με βάση τα κριτήρια...");

      List<Activity> activities = schedule.searchActivities(destinationId, typeId, budgetId);
      System.out.println("Βρέθηκαν " + activities.size() + " δραστηριότητες.");

      Map<Integer, Map<String, Activity>> totalSchedule = schedule.assignActivitiesToTimeSlots(activities, totalDays);
      System.out.println("Το πρόγραμμα δημιουργήθηκε για " + totalDays + " ημέρες.");

      // Αποθήκευση προγράμματος στη βάση
      System.out.println("Αποθήκευση του προγράμματος στη βάση δεδομένων...");
      schedule.saveSchedule(totalSchedule);
      System.out.println("Το πρόγραμμα αποθηκεύτηκε επιτυχώς.");

      // Προσθήκη δεδομένων στο request scope για προβολή στο JSP
      request.setAttribute("totalSchedule", totalSchedule);
      request.setAttribute("activities", activities);

      // Προώθηση στη JSP για προβολή
      System.out.println("Προώθηση στη σελίδα ShowScheduleDay.jsp...");
      RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/ShowScheduleDay.jsp");
      dispatcher.forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", "An error occurred: " + e.getMessage());
      RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/ErrorPage.jsp");
      dispatcher.forward(request, response);
    }
  }
}
