import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import ScheduluxeClasses.*;

public class PreferencesServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    // Παίρνουμε τις τιμές από τη φόρμα
    String destination = request.getParameter("destination");
    int days = request.getParameter("days");
    String[] tripType = request.getParameterValues("type");
    String budget = request.getParameter("budget");

    // Αντιπροσωπευτικό userId (πρέπει να το λάβουμε από το session του χρήστη)
    HttpSession session = request.getSession(false);
    int userId = (int) session.getAttribute("userId");

    // Αποθήκευση δεδομένων στη βάση
    Preferences preferences = new Preferences();
    boolean isSaved = preferences.saveUserPreferences(userId, destination, days, tripType, budget);

    // if (isSaved) {
    // out.println("<h3>Your preferences have been saved successfully!</h3>");
    // } else {
    // out.println("<h3>Failed to save your preferences. Please try again.</h3>");
    // }

    out.close();
  }
}
