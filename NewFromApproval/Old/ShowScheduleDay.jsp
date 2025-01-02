<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map, Scheduluxe.*, java.net.URLEncoder" %> 

<%@ include file="AuthenticationGuard.jsp" %>
<%
    int currentDay = Integer.parseInt(request.getParameter("day") != null ? request.getParameter("day") : "1");
    Integer scheduleIdObj = (Integer) request.getAttribute("scheduleId");
    int scheduleId = scheduleIdObj != null ? scheduleIdObj : Integer.parseInt(request.getParameter("scheduleId"));
    
    Traveler traveler = (Traveler) session.getAttribute("travelerObj");
    int userId = traveler.getId(traveler.getUsername(), traveler.getPassword());

    Schedule schedule = new Schedule();
    int totalDays = schedule.findDaysFromScheduleByUser(userId, scheduleId);

    Map<Integer, Map<String, Activity>> totalSchedule = schedule.getScheduleForUser(userId, scheduleId);
    
    if (totalSchedule == null) {
        out.println("No schedule available.");
    }

    int destId = (Integer) session.getAttribute("destinationId");
    CreationSchedule cs = new CreationSchedule();
    List<Object> info = cs.getDestinationInfo(destId);
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
    <header>
        <nav class="nav-menu">
            <div class="logo">  
                <img src="<%=request.getContextPath()%>/images/logo.png" alt="Icon">
                <h1>Scheduluxe</h1>
            </div>
            <button class="menu-toggle">
                <span class="hamburger-icon">&#9776;</span>
            </button>
            <div class="dropdown">
                <ul class="nav-links">  
                    <li><a href="<%=request.getContextPath()%>/Scheduluxe/Home.jsp">Home</a></li>  
                    <li><a href="<%=request.getContextPath()%>/Scheduluxe/Aboutus.jsp">About us</a></li>  
                    <li><a href="<%=request.getContextPath()%>/Scheduluxe/EditProfile.jsp"><span class="material-symbols-outlined user-icon">account_circle</span></a></li>  
                </ul>
            </div>
        </nav>
    </header>

    <main>
        <div class="schedule-container">
            <div class="day-container">
                <%
                    boolean showLeftArrow = currentDay > 1;
                    boolean showRightArrow = currentDay < totalDays;
                %>
            
                <%-- Left Arrow --%>
                <a href="<%= request.getContextPath() %>/Scheduluxe/ShowScheduleDay.jsp?day=<%= currentDay - 1 %>&totalDays=<%= totalDays %>&scheduleId=<%= scheduleId %>" class="arrow" 
                   <%= showLeftArrow ? "" : "style='display:none;'" %>>
                    <i class="fa-solid fa-arrow-left" style="color: #000000; font-size: 40px;"></i>
                </a>
            
                <h2>Day <%= currentDay %></h2>
            
                <%-- Right Arrow --%>
                <a href="<%= request.getContextPath() %>/Scheduluxe/ShowScheduleDay.jsp?day=<%= currentDay + 1 %>&totalDays=<%= totalDays %>&scheduleId=<%= scheduleId %>" class="arrow" 
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
                %>
                <a href="<%= request.getContextPath() %>/Scheduluxe/ShowOverallSchedule.jsp?scheduleId=<%= scheduleId %>">
                    <button type="button" class="button-overall">View Overall Schedule</button>
                </a>
            </div>
        </div>
        <div class="other-info-container">
            <!-- Map container with Leaflet library -->
            <div class="map-container" id="map-container" 
                data-latitude="<%= info.get(1) %>" 
                data-longitude="<%= info.get(2) %>" 
                data-name="<%= info.get(3) %>">
            </div>
            <!-- Details section with title and description -->
            <div class="details-container">
                <h2>Details</h2>
                <p id="activity-details">
                    <%= info.get(0)%>
                    <br><br>
                    Select an activity to view details here.
                </p>
            </div>

        </div>
    </main>
    <script>
        /* function that responds to onclick: 
        what is happening behind?
        1. on the server-side, jsp dynamically generates the HTML code 
        2. when the page is loaded, the HTML page is delivered to client
        3. when the user clicks on an activity, the function is executed
        4. then dynamically the element <p id="activity-details"> of the HTML page is being dynamically updated
        
        
        Advantages:
        - there is not a reloading of the whole page. Basically, the only thing that changes is the DOM (Document Object Model = programming interface provided by the browser that represents the structure of a webpage (HTML document) as a tree of objects. That is because the thing that changes dynamically is an element of HTML (<p id="activity-details">)
        - that means that there is not an additional server request
        - so everything happens on the client-side in memory
        */
        function loadActivityDetails(activityDetails) {
            var decodedDetails = decodeURIComponent(activityDetails);
            document.getElementById('activity-details').innerText = decodedDetails;
        }
    </script>
    <script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>
    <script>
        const mapContainer = document.getElementById('map-container');
        const latitude = parseFloat(mapContainer.getAttribute('data-latitude'));
        const longitude = parseFloat(mapContainer.getAttribute('data-longitude'));
        const destName = mapContainer.getAttribute("data-name");
        const map = L.map('map-container').setView([latitude, longitude], 13); 
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);
        L.marker([latitude, longitude]).addTo(map)
            .bindPopup(destName) 
            .openPopup();
    </script>
    <script src="<%=request.getContextPath()%>/js/menuToggle.js"></script>
</body>
</html>
