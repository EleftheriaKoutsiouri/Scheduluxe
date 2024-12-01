<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Scheduluxe.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Schedule Creation</title>
</head>
<body>
    <h1>Schedule Creation Debugging</h1>
    <%
        // Ανάκτηση του userId από το session
        //HttpSession session = request.getSession();
        //int userId = (Integer) session.getAttribute("userID");
        
        // Initialize variables to store form parameters
        String destination = request.getParameter("destination");
        String type = request.getParameter("type");
        String budget = request.getParameter("budget");
        String daysParam = request.getParameter("totalDays");

        int totalDays = 0;
        boolean hasError = false;

        // Validate the number of days input
        try {
            totalDays = Integer.parseInt(daysParam);
            out.println("<p>Total Days: " + totalDays + "</p>");
        } catch (NumberFormatException e) {
            out.println("<p style='color: red;'>Error: TotalDays is not a valid number. Value: " + daysParam + "</p>");
            request.setAttribute("error", "Invalid number of days provided.");
            hasError = true;
        }

        if (!hasError) {
            try {
                // Retrieve IDs from the database
                CreationSchedule creationSchedule = new CreationSchedule();
                out.println("<p>Fetching IDs from the database...</p>");

                int destinationId = creationSchedule.getIdFromDatabase("Destinations", "DestinationName", destination, "DestinationID");
                out.println("<p>Destination ID: " + destinationId + "</p>");

                int typeId = creationSchedule.getIdFromDatabase("ActivityTypes", "TypeName", type, "typeID");
                out.println("<p>Type ID: " + typeId + "</p>");

                int budgetId = creationSchedule.getIdFromDatabase("BudgetType", "BudgetName", budget, "BudgetID");
                out.println("<p>Budget ID: " + budgetId + "</p>");

                // Create a schedule
                Schedule schedule = new Schedule();

                List<Activity> activities = schedule.searchActivities(destinationId, typeId, budgetId);
                out.println("<p>Found " + activities.size() + " activities.</p>");

                Map<Integer, Map<String, Activity>> totalSchedule = schedule.assignActivitiesToTimeSlots(activities, totalDays);
                out.println("<p>Schedule created for " + totalDays + " days.</p>");

                // Save the schedule in the database
                out.println("<p>Saving the schedule to the database...</p>");
                schedule.saveSchedule(totalSchedule);
                int scheduleId = schedule.getScheduleId();
                //schedule.saveScheduleForUser(userId, scheduleId);
                out.println("<p>Debug: TotalSchedule = " + totalSchedule + "</p>");

                // Debug activity names
                for (Map.Entry<Integer, Map<String, Activity>> dayEntry : totalSchedule.entrySet()) {
                    out.println("<h3>Day " + dayEntry.getKey() + "</h3>");
                    for (Map.Entry<String, Activity> timeEntry : dayEntry.getValue().entrySet()) {
                        out.println("<p>" + timeEntry.getKey() + ": " + timeEntry.getValue().getActivityName() + "</p>");
                    }
                }

                out.println("<p>Schedule saved successfully.</p>");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/ShowScheduleDay.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                request.setAttribute("error", "An error occurred: " + e.getMessage());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/ErrorPage.jsp");
                dispatcher.forward(request, response);
            }
        }
    %>
</body>
</html>
