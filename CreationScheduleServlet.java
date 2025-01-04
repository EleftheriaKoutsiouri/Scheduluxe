import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import Scheduluxe.Activity;
import Scheduluxe.Schedule;
import Scheduluxe.ScheduleDAO;
import Scheduluxe.Traveler;

public class CreationScheduleServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Traveler traveler = (Traveler) session.getAttribute("travelerObj");

        if (traveler == null) {
            request.setAttribute("error", "No traveler information found in session.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/ErrorPage.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String destinationIdString = request.getParameter("destination");
        String[] typesIdString = request.getParameterValues("type");
        String budgetIdString = request.getParameter("budget");
        String daysIdString = request.getParameter("totalDays");

        try {
            int totalDays = Integer.parseInt(daysIdString);
            int destinationId = Integer.parseInt(destinationIdString);
            int budgetId = Integer.parseInt(budgetIdString);

            List<Integer> typesId = new ArrayList<>();
            if (typesIdString != null) {
                for (String type : typesIdString) {
                    int typeId = Integer.parseInt(type);
                    typesId.add(typeId);
                }
            }

            ScheduleDAO scheduleDAO = new ScheduleDAO();
            List<Activity> activities = scheduleDAO.searchActivities(destinationId, typesId, budgetId);

            int userId = traveler.getId(traveler.getUsername(), traveler.getPassword());

            Schedule schedule = scheduleDAO.createSchedule(activities, totalDays, userId);
            schedule.saveSchedule();

            // to appear the map and the details about the destination in the
            // ShowScheduleDay => to change if ajax
            session.setAttribute("destinationId", destinationId);

            // pass the whole object of schedule
            session.setAttribute("schedule", schedule);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/ShowScheduleDay.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/ErrorPage.jsp");
            dispatcher.forward(request, response);
            return;
        }
    }

}
