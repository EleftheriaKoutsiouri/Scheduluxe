<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Scheduluxe.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="javax.servlet.RequestDispatcher" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Schedule Creation</title>
</head>
<body>
    <h1>Schedule Creation Debugging</h1>
    <%
        // Initialize variables to store form parameters
        String destination = request.getParameter("destination");
        String[] typeIdsParam = request.getParameterValues("typeId");
        List<Integer> typeIds = new ArrayList<Integer>(); // Use explicit generic type for older Java versions
        if (typeIdsParam != null) {
            for (String typeIdStr : typeIdsParam) {
                typeIds.add(Integer.parseInt(typeIdStr));
            }
        }

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

                int destinationId = creationSchedule.getIdFromDatabase(
                    "Destinations", "DestinationName", destination, "DestinationID"
                );
                out.println("<p>Destination ID: " + destinationId + "</p>");

                // Assuming you get `types` from user input elsewhere
                String[] typesArray = request.getParameterValues("type");
                List<String> types = typesArray != null ? Arrays.asList(typesArray) : new ArrayList<String>();
                
                typeIds = creationSchedule.getTypesIdFromDatabase(types);
                out.println("<p>Type IDs: " + typeIds.size() + "</p>");

                int budgetId = creationSchedule.getIdFromDatabase(
                    "BudgetType", "BudgetName", budget, "BudgetID"
                );
                out.println("<p>Budget ID: " + budgetId + "</p>");

                // Create a schedule
                Schedule schedule = new Schedule();

                List<Activity> activities = schedule.searchActivities(destinationId, typeIds, budgetId);
                out.println("<p>Found " + activities.size() + " activities.</p>");

                Map<Integer, Map<String, Activity>> totalSchedule = 
                    schedule.assignActivitiesToTimeSlots(activities, totalDays);
                out.println("<p>Schedule created for " + totalDays + " days.</p>");

                // Debug activity names
                for (Map.Entry<Integer, Map<String, Activity>> dayEntry : totalSchedule.entrySet()) {
                    out.println("<h3>Day " + dayEntry.getKey() + "</h3>");
                    for (Map.Entry<String, Activity> timeEntry : dayEntry.getValue().entrySet()) {
                        out.println("<p>" + timeEntry.getKey() + ": " + timeEntry.getValue().getActivityName() + "</p>");
                    }
                }

                out.println("<p>Schedule saved successfully.</p>");
            } catch (Exception e) {
                request.setAttribute("error", "An error occurred: " + e.getMessage());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Scheduluxe/ErrorPage.jsp");
                dispatcher.forward(request, response);
            }
        }
    %>
</body>
</html>
