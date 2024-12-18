<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ page import="Scheduluxe.*" %>
<%@ page errorPage="ErrorPage.jsp" %>

<%
    Map<Integer, Map<String, Activity>> totalSchedule = (Map<Integer, Map<String, Activity>>) session.getAttribute("totalSchedule");
    if (totalSchedule == null) {
        out.println("<p style='color: red;'>Error: No schedule data available.</p>");
        return; // Σταματά την εκτέλεση αν δεν υπάρχουν τα δεδομένα
    }
    int totalDays = Integer.parseInt(request.getParameter("totalDays") != null ? request.getParameter("totalDays") : "3");
    Schedule sch = new Schedule();
    String[] timeSlots = sch.getTimeSlots(); 
%>

<!DOCTYPE html>
<html lang="en">
<head> 
    <title>Scheduluxe Overall Schedule</title>
    <%@ include file="head.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ScheduleOverall.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
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
                    <li><a href="First.jsp">Home</a></li>  
                    <li><a href="Aboutus.jsp">About us</a></li> 
                    <li><a href="EditProfile.jsp"><span class="material-symbols-outlined">account_circle</span></a></li>  
                </ul>  
            </div>
        </nav>
    </header>
    
    <main>
        <div class="schedule-container">
            <h2>Overall Schedule</h2>
            <table class="table table-bordered table-condensed table-hover">
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
                        for (String timeSlot : timeSlots) { 
                    %>
                    <tr>
                        <td><%= timeSlot %></td>
                        <% 
                            for (int day = 1; day <= totalDays; day++) { 
                                Map<String, Activity> daySchedule = totalSchedule.get(day);
                                Activity activity = daySchedule != null ? daySchedule.get(timeSlot) : null; // Retrieve activity for the time slot
                        %>
                        <td>
                            <% if (activity != null) { %>
                                <%= activity.getActivityName() %>
                            <%}%>
                          
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
                <form action="<%=request.getContextPath()%>/servlet/FeedbackServlet" method="POST">
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
            document.querySelector('.download-btn').addEventListener('click', function() {
                const element = document.querySelector('.schedule-container');
                const options = {
                    margin:       1,  
                    filename:     'schedule.pdf',
                    image:        { type: 'jpeg', quality: 0.98 },
                    html2canvas:  { scale: 2, width: element.offsetWidth }, 
                    jsPDF:        { unit: 'in', format: 'a4', orientation: 'landscape' } 
                };
                html2pdf().set(options).from(element).save();
            });
        </script>
        <script>
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
