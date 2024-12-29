package NewFromApproval;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import Scheduluxe.Activity;
import Scheduluxe.Traveler;


public class CreationScheduleServlet  extends HttpServlet {

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

        String destination = request.getParameter("destination");
        String[] typesString = request.getParameterValues("type");
        String budget = request.getParameter("budget");
        String days = request.getParameter("totalDays");

        try {
            int totalDays = Integer.parseInt(days);
            int destinationId = Integer.parseInt(destination);
            int budgetId = Integer.parseInt(budget);

            List<Integer> typesId = new ArrayList<>();
            if (typesString != null) {
                for (String type : typesString) {
                    int typeId = Integer.parseInt(type);
                    typesId.add(typeId);
                }
            }
            

            // TODO Create a schedule
            // ScheduleDAO scheduleDao = new ScheduleDAO();
            // List<Activity> activities = scheduleDao.searchActivities(destinationId, typesId, budgetId);

            // Map<Integer, Map<String, Activity>> totalSchedule = schedule.assignActivitiesToTimeSlots(activities,
            //         totalDays);

            // int userId = traveler.getId(traveler.getUsername(), traveler.getPassword());
            // int scheduleId = schedule.saveSchedule(totalSchedule, userId, totalDays);

            // //to appear the map and the details about the destination in the ShowScheduleDay => to chnage if ajax
            // session.setAttribute("destinationId", destinationId);


            // request.setAttribute("scheduleId", scheduleId);
            
            // RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/ShowScheduleDay.jsp");
            // dispatcher.forward(request, response);

        

        } catch (Exception e) {
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/ErrorPage.jsp");
            dispatcher.forward(request, response);
            return;
        }
}
