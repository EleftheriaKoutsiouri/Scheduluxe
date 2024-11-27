import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import ScheduluxeClasses.TripCharacteristics;

public class TripCharacteristicsServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    try {
      // Retrieve the form data from the request
      String destination = request.getParameter("destination");
      int days = Integer.parseInt(request.getParameter("days"));
      String[] tripTypes = request.getParameterValues("type");
      String budget = request.getParameter("budget");
      TripCharacteristics trip = new TripCharacteristics();

      // Convert destination and budget to corresponding IDs
      int destinationId = trip.getIdFromDatabase("Destinations", "DestinationName", destination,
          "DestinationID");
      int budgetId = trip.getIdFromDatabase("BudgetType", "BudgetName", budget, "BudgetID");

      trip.saveTripCharacteristics(destinationId, days, budgetId, tripTypes);

      // Send a confirmation message
      out.println("<h3>TripCharacteristicshave been saved successfully!</h3>");
    } catch (Exception e) {
      e.printStackTrace();
      out.println("<h3>Failed to save TripCharacteristics: " + e.getMessage() + "</h3>");
    } finally {
      out.close();
    }
  }
}
