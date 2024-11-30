<%@ page import="java.util.List, java.util.Map" %>
<%@ page import="ScheduleClasses.*" %>

<%
    int totalDays = Integer.parseInt(request.getParameter("totalDays") != null ? request.getParameter("totalDays") : "3");
    Schedule schedule = new Schedule();
    // Assuming you have a method to get the schedule data for multiple days
    Map<Integer, List<Activity>> fullSchedule = schedule.getFullSchedule(totalDays); 
    String[] timeSlots = schedule.getTimeSlots(); // This should return an array of time slots, e.g. "9:00 AM", "11:00 AM", etc.
%>

<!DOCTYPE html>
<html lang="en">
<head> 
    <title>Scheduluxe Overall Schedule</title>
    <%@ include file="header.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/ScheduleOverall.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.2/html2pdf.bundle.min.js"></script>  
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
                    <li><a href="WelcomePage.jsp">Home</a></li>  
                    <li><a href="About.jsp">About us</a></li> 
                    <li><a href="EditProfile.jsp"><span class="material-symbols-outlined">account_circle</span></a></li>  
                </ul>  
            </div>
        </nav>
    </header>
    
    <main>
        <div class="schedule-container">
            <h2>Overall Schedule</h2>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Time Slot</th>
                        <% for (int day = 1; day <= totalDays; day++) { %>
                            <th>Day <%= day %></th>
                        <% } %>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        for (int i = 0; i < timeSlots.length; i++) { 
                    %>
                    <tr>
                        <td><%= timeSlots[i] %></td>
                        <% 
                            // For each day, get the activity for this time slot
                            for (int day = 1; day <= totalDays; day++) { 
                                List<Activity> dayActivities = fullSchedule.get(day);
                                Activity activity = (dayActivities != null && dayActivities.size() > i) ? dayActivities.get(i) : null;
                        %>
                        <td>
                            <% if (activity != null) { %>
                                <%= activity.getActivityName() %>
                            <% } else { %>
                                No activity
                            <% } %>
                        </td>
                        <% } %>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        
        <div class="feedback-container">
            <div class="comment-box">
                <h3>Leave a Comment</h3>
                <form action="servlet/FeedbackServlet" method="POST">
                    <div class="comment-input">
                        <textarea name="commentText" class="form-control" rows="3" placeholder="Share your thoughts about the schedule..."></textarea>
                        <input type="hidden" name="scheduleId" value="${scheduleId}"/>
                        <button type="submit" class="btn submit-btn">Submit</button>
                    </div>
                </form>
            </div>
            <div class="stars">
                <h3>Rate the Schedule</h3>
                <form id="ratingForm">
                    <input class="star star-5" id="star-5" type="radio" name="star" onclick="submitRating(5)" />
                    <label class="star star-5" for="star-5"></label>
                
                    <input class="star star-4" id="star-4" type="radio" name="star" onclick="submitRating(4)" />
                    <label class="star star-4" for="star-4"></label>
                
                    <input class="star star-3" id="star-3" type="radio" name="star" onclick="submitRating(3)" />
                    <label class="star star-3" for="star-3"></label>
                
                    <input class="star star-2" id="star-2" type="radio" name="star" onclick="submitRating(2)" />
                    <label class="star star-2" for="star-2"></label>
                
                    <input class="star star-1" id="star-1" type="radio" name="star" onclick="submitRating(1)" />
                    <label class="star star-1" for="star-1"></label>
                </form>
            </div>
            <div class="download-box">
                <button class="btn download-btn">
                    <span class="glyphicon glyphicon-download-alt"></span> Schedule
                </button>
            </div>            
        </div>
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        
        <script>
            src="js/DownloadSchedule.js">
            function submitRating(starValue) {
                const xhr = new XMLHttpRequest();
                xhr.open("POST", "FeedbackServlet", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        alert("Rating submitted successfully!");
                    }
                };

                xhr.send("action=rate&rating=" + starValue);
            }

        </script>
    </main>
    
    <script src="<%=request.getContextPath()%>/js/menuToggle.js"></script>
    
</body>
</html>
