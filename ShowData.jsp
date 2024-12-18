<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, Scheduluxe.Activity" %>

<%
    Map<Integer, Map<String, Activity>> scheduleData = 
        (Map<Integer, Map<String, Activity>>) request.getAttribute("scheduleData");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Schedule Data</title>
</head>
<body>
    <h2>Schedule Data</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Day</th>
                <th>Time Slot</th>
                <th>Activity Name</th>
                <th>Details</th>
            </tr>
        </thead>
        <tbody>
            <% 
                if (scheduleData != null) {
                    for (Map.Entry<Integer, Map<String, Activity>> dayEntry : scheduleData.entrySet()) {
                        int day = dayEntry.getKey();
                        Map<String, Activity> daySchedule = dayEntry.getValue();

                        for (Map.Entry<String, Activity> timeSlotEntry : daySchedule.entrySet()) {
                            String timeSlot = timeSlotEntry.getKey();
                            Activity activity = timeSlotEntry.getValue();
            %>
            <tr>
                <td><%= day %></td>
                <td><%= timeSlot %></td>
                <td><%= activity.getActivityName() %></td>
                <td><%= activity.getDetails() %></td>
            </tr>
            <% 
                        }
                    }
                } else {
            %>
            <tr>
                <td colspan="4">No data available.</td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>