<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ScheduluxeClasses.*" %>
<%
    // Creating an object of the Schedule class
    Schedule sch = new Schedule();

    // Fetching data from the database
    List<String> destinations = sch.getDestinations();
    List<String> preferences = sch.getPreferences();
    List<String> budget = sch.getBudget();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Preferences</title>
    <%@ include file="header.jsp" %>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/SelectionPage.css">
    
</head>

<body>
    <main>
        <form action="PreferencesServlet" method="post">
            <div class="form-group">
                <label for="destination">Destination</label>
                <select id="destination" name="destination" required>
                    <option value="" disabled selected hidden>Select destination</option>
                    <% for (String dest : destinations) { %>
                        <option value="<%= dest %>"><%= dest %></option>
                    <% } %>
                </select>
            </div>
            <div class="form-group">
                <label for="date">Days</label>
                <input type="number" min="1" max="4" id="date" name="date" placeholder="Select days" required>
            </div>
            <div class="form-group">
                <label for="type">Type</label>
                <% for (String pref : preferences) { %>
                    <input type="checkbox" name="type" value="<%= pref %>"><%= pref %><br>
                <% } %>
            </div>
            <div class="form-group">
                <label for="budget">Budget</label>
                <select id="budget" name="budget" required>
                    <option value="" disabled selected hidden>Select budget</option>
                    <% for (String bdg : budget) { %>
                        <option value="<%= bdg %>"><%= bdg %></option>
                    <% } %>
                </select>
            </div>
            <button type="submit">Submit</button>
        </form>
    </main>
</body>
</html>
