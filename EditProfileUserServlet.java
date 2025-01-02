import java.io.*;
import Scheduluxe.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EditProfileUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("travelerObj") == null) {
            response.sendRedirect("Connect.jsp");
            return;
        }

        Traveler traveler = (Traveler) session.getAttribute("travelerObj");

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String password = request.getParameter("password");

        if (firstname == null || firstname.isEmpty()) {
            firstname = traveler.getFirstname();
        }

        if (lastname == null || lastname.isEmpty()) {
            lastname = traveler.getLastname();
        }

        if (username == null || username.isEmpty()) {
            username = traveler.getUsername();
        }

        if (email == null || email.isEmpty()) {
            email = traveler.getEmail();
        }

        if (country == null || country.isEmpty()) {
            country = traveler.getCountry();
        }

        if (password == null || password.isEmpty()) {
            password = traveler.getPassword();
        }

        Traveler traveler2 = new Traveler(username, firstname, lastname, email, country, password);
        boolean updated;
        try {
            updated = traveler2.saveOrUpdate();
            if (updated) {
                session.setAttribute("travelerObj", traveler2);
                request.setAttribute("message", "Success");
            } else {
                request.setAttribute("message", "Failure");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/EditProfile.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}