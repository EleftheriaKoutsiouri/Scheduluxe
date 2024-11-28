import java.io.*;
import Scheduluxe.Preferences;
import Scheduluxe.Traveler;

import javax.servlet.*;
import javax.servlet.http.*;

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

      // Retrieve the traveler object from the session
      HttpSession session = request.getSession(false); // Prevent creating a new session
      if (session == null || session.getAttribute("travelerObj") == null) {
        throw new Exception("User session is invalid. Please log in again.");
      }

      Traveler traveler = (Traveler) session.getAttribute("travelerObj");
      String username = traveler.getUsername();
      String password = traveler.getPassword();
      int userId = traveler.getId(username, password);
      Preferences preferences = new Preferences();

      // Convert destination and budget to corresponding IDs
      int destinationId = preferences.getIdFromDatabase("Destinations", "DestinationName", destination,
          "DestinationID");
      int budgetId = preferences.getIdFromDatabase("BudgetType", "BudgetName", budget, "BudgetID");

      // Save preferences
      preferences.savePreferences(userId, destinationId, days, tripTypes, budgetId);

      response.getWriter().println("<h3>Your preferences have been saved successfully!</h3>");
    } catch (Exception e) {
      e.printStackTrace();
      response.getWriter().println("<h3>Failed to save your preferences: " + e.getMessage() + "</h3>");
    } finally {
      out.close();
    }
  }
}
