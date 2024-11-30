import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import Scheduluxe.Traveler;

public class EditProfileUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userID") == null) {
            response.sendRedirect("SigninController.jsp");
            return;
        }

        String userID = (String) session.getAttribute("userID");
        Traveler traveler = Traveler.getTraveler(userID, password);

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String password = request.getParameter("password");

        // Φτιαχνω νεο αντικειμενο traveler με ορισματα τις νεες παραμετρους (με
        // ελεγχους οτι εχει αλλαξει κατι) και καλω με το νεο αντικειμενο την
        // saveOrUpdate()
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

        boolean updated = traveler2.saveOrUpdate();

        if (updated) {
            request.setAttribute("message", "Success");
        } else {
            request.setAttribute("message", "Failure");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("EditProfile.jsp");
        dispatcher.forward(request, response);

    }

}