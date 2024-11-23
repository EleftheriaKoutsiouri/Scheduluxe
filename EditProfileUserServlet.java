import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import ScheduluxeClasses.Traveler;

public class EditProfileUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userID") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        
        String userID = (String) session.getAttribute("userID");
        Traveler traveler = Traveler.getTravelerByUsername(userID);

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String password = request.getParameter("password");
        
        traveler.setfirstName(firstName)
        traveler.saveOrUpdate()
    
    }


}