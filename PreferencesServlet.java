import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import ScheduluxeClasses.*;

public class PreferencesServlet extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = new PrintWriter(response.getWriter(), true);

    String destination = request.getParameter("destination");
    String daystr = request.getParameter("days");
    int days = Integer.parseInt(daystr); // String to int
    String[] triptype = request.getParameter("type");
    String budget = request.getParameter("budget");

    Schedule sch = new Schedule(0, destination, days, null, days)




      }
}