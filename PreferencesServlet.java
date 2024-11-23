import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import ScheduluxeClasses.DatabaseConnection;

public class PreferencesServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    try {
      // Παίρνουμε τις τιμές από τη φόρμα
      String destination = request.getParameter("destination");
      int days = Integer.parseInt(request.getParameter("days"));
      String[] tripTypes = request.getParameterValues("type");
      String budget = request.getParameter("budget");

      // Παίρνουμε το userId από το session
      HttpSession session = request.getSession(false);
      int userId = (int) session.getAttribute("userId");

      // Μετατροπή destination και budget σε αντίστοιχα IDs
      int destinationId = getIdFromDatabase("Destinations", "DestinationName", destination, "DestinationID");
      int budgetId = getIdFromDatabase("BudgetType", "BudgetName", budget, "BudgetID");

      // Αποθήκευση προτιμήσεων στη βάση
      savePreferences(userId, destinationId, budgetId, tripTypes);

      out.println("<h3>Your preferences have been saved successfully!</h3>");
    } catch (Exception e) {
      e.printStackTrace();
      out.println("<h3>Failed to save your preferences: " + e.getMessage() + "</h3>");
    } finally {
      out.close();
    }
  }

  private void savePreferences(int userId, int destinationId, int budgetId, String[] tripTypes) throws Exception {
    DatabaseConnection db = new DatabaseConnection();
    Connection con = null;

    String sql = "INSERT INTO Preferences (UserID, TypeID, BudgetID, DestinationID) VALUES (?, ?, ?, ?)";
    try {
      con = db.getConnection();
      for (String tripType : tripTypes) {
        int typeId = getIdFromDatabase("ActivityTypes", "TypeName", tripType, "TypeID");
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, userId);
        stmt.setInt(2, typeId);
        stmt.setInt(3, budgetId);
        stmt.setInt(4, destinationId);
        stmt.executeUpdate();
        stmt.close();
      }
    } catch (Exception e) {
      throw new Exception("Error saving preferences: " + e.getMessage());
    } finally {
      if (con != null) {
        con.close();
      }
    }
  }

  private int getIdFromDatabase(String tableName, String columnName, String value, String idColumn) throws Exception {
    DatabaseConnection db = new DatabaseConnection();
    Connection con = null;
    int id = 0;

    String query = "SELECT " + idColumn + " FROM " + tableName + " WHERE " + columnName + " = ?";
    try {
      con = db.getConnection();
      PreparedStatement stmt = con.prepareStatement(query);
      stmt.setString(1, value);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        id = rs.getInt(1);
      }

      rs.close();
      stmt.close();
    } catch (Exception e) {
      throw new Exception("Error retrieving ID from " + tableName + ": " + e.getMessage());
    } finally {
      if (con != null) {
        con.close();
      }
    }
    return id;
  }
}
