<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map, Scheduluxe.*, java.net.URLEncoder" %> 
<%@ page errorPage="ErrorPage.jsp" %>

<%
    int totalDays = Integer.parseInt(request.getParameter("totalDays"));
    int currentDay = Integer.parseInt(request.getParameter("day") != null ? request.getParameter("day") : "1");
    int scheduleId = (Integer) request.getAttribute("scheduleId");
    int userId = (Integer) session.getAttribute("userId");

    Schedule schedule = new Schedule();
    Map<Integer, List<Map<String, Object>>> totalSchedule = schedule.getScheduleForUser(userId, scheduleId, totalDays);
    
    if (totalSchedule == null) {
        out.println("No schedule available.");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Schedule By Day</title>
    <%@ include file="head.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ScheduleDay.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <!-- Header section with navigation menu -->
    <header>
        <nav class="nav-menu">
            <div class="logo">  
                <img src="<%=request.getContextPath()%>/images/logo.png" alt="Icon">
                <h1>Scheduluxe</h1>
            </div>
            
            <!-- Hamburger Menu Button -->
            <button class="menu-toggle">
                <span class="hamburger-icon">&#9776;</span>
            </button>
            <div class="dropdown">
                <ul class="nav-links">  
                    <li><a href="First.jsp">Home</a></li>  
                    <li><a href="Aboutus.jsp">About us</a></li>  
                    <li><a href="EditProfile.jsp"><span class="material-symbols-outlined user-icon">account_circle</span></a></li>  
                </ul>
            </div>
        </nav>
    </header>

    <main>
        <!-- Schedule container for day navigation and activities list -->
        <div class="schedule-container">
            <!-- Day container with navigation arrows -->
            <div class="day-container">
                <%
                    // Hide the left arrow if we're on the first day
                    boolean showLeftArrow = currentDay > 1;

                    // Hide the right arrow if we're on the last day
                    boolean showRightArrow = currentDay < totalDays;
                %>
            
                <%-- Left Arrow --%>
                <a href="<%= request.getContextPath() %>/Scheduluxe/ShowScheduleDay.jsp?day=<%= currentDay - 1 %>&totalDays=<%= totalDays %>" class="arrow" 
                   <%= showLeftArrow ? "" : "style='display:none;'" %>>
                    <i class="fa-solid fa-arrow-left" style="color: #000000; font-size: 40px;"></i>
                </a>
            
                <h2>Day <%= currentDay %></h2>
            
                <%-- Right Arrow --%>
                <a href="<%= request.getContextPath() %>/Scheduluxe/ShowScheduleDay.jsp?day=<%= currentDay + 1 %>&totalDays=<%= totalDays %>" class="arrow" 
                   <%= showRightArrow ? "" : "style='display:none;'" %>>
                    <i class="fa-solid fa-arrow-right" style="color: #000000; font-size: 40px;"></i>
                </a>
            </div>

            <!-- List of activities for the day -->
            <div class="activity-list">
                <%
                    String[] times = {"09:00-11:00", "11:00-13:00", "13:00-15:00", "15:00-17:00", "17:00-19:00", "19:00-21:00"};
                    Map<String, Activity> daySchedule = totalSchedule.get(currentDay);
                    for (int i = 0; i < times.length; i++) {
                        Activity activity = daySchedule.get(times[i]);
                %>
                <%
                    String details = activity.getDetails();
                    String encodedDetails = URLEncoder.encode(details, "UTF-8");

                    // Replace '+' with spaces so that it appears as normal text
                    encodedDetails = encodedDetails.replace("+", " ");

                %>
                        <div class="activity-item" onclick="loadActivityDetails('<%= encodedDetails %>')">
                            <div class="icon-activity">
                                <div class="icon-container">
                                    <img src="<%=request.getContextPath()%>/images/todo.png" alt="Activity Icon">
                                </div>
                                <h3><%= activity.getActivityName() %></h3>
                            </div>
                            <h4><%= times[i] %></h4>
                        </div>

                <%
                    }
                    Integer schId = (Integer) request.getAttribute("scheduleId");
                    int scheduleId = schId != null ? schId : 0;
                %>

                <a href="<%= request.getContextPath() %>/Scheduluxe/ShowOverallSchedule.jsp?scheduleId=<%=scheduleId %>">
                    <button type="button" class="button-overall">View Overall Schedule</button>
                </a>
            </div>
        </div>

        <!-- Additional information container with map and details section -->
        <div class="other-info-container">
            <!-- Map container with Leaflet library -->
            <div class="map-container" id="map">
                <script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>
                <script src="<%= request.getContextPath() %>/js/map.js"></script> <!-- Link to custom map JavaScript file -->
            </div>

            <!-- Details section with title and description -->
            <div class="details-container">
                <h2>Details</h2>
                <p id="activity-details">Select an activity to view details here.</p>
            </div>

        </div>
    </main>

    <script>
        function loadActivityDetails(activityDetails) {
            // Decode the encoded plain text (if necessary)
            var decodedDetails = decodeURIComponent(activityDetails);
    
            // Directly inject the plain text into the container
            document.getElementById('activity-details').innerText = decodedDetails;
        }
    </script>

    <!-- External JavaScript for menu toggle -->
    <script src="<%=request.getContextPath()%>/js/menuToggle.js"></script>
</body>
</html>
