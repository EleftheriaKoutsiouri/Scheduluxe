import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import ScheduluxeClasses.Preferences;

public class PreferencesServlet extends HttpServlet {

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

      // Retrieve the userId from the session
      HttpSession session = request.getSession(false);
      int userId = (int) session.getAttribute("userId");

      Preferences preferences = new Preferences();

      // Convert destination and budget to corresponding IDs
      int destinationId = preferences.getIdFromDatabase("Destinations", "DestinationName", destination,
          "DestinationID");
      int budgetId = preferences.getIdFromDatabase("BudgetType", "BudgetName", budget, "BudgetID");

      // Save preferences in the database
      preferences.savePreferences(userId, destinationId, budgetId, tripTypes);

      // Send a confirmation message
      out.println("<h3>Your preferences have been saved successfully!</h3>");
    } catch (Exception e) {
      e.printStackTrace();
      out.println("<h3>Failed to save your preferences: " + e.getMessage() + "</h3>");
    } finally {
      out.close();
    }
  }
}
