<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ page import="Scheduluxe.*" %>

<%@ include file="AuthenticationGuard.jsp" %>
<%
    Schedule schedule;

    String scheduleIdParam = request.getParameter("scheduleId");

    if (scheduleIdParam != null && !scheduleIdParam.isEmpty()) {
        int scheduleId = Integer.parseInt(scheduleIdParam);

        ScheduleDAO scheduleDAO = new ScheduleDAO();
        schedule = scheduleDAO.fetchPastScheduleById(scheduleId);
        session.setAttribute("schedule", schedule);
    } else {
        schedule = (Schedule) session.getAttribute("schedule");
    }

    if (schedule == null) {
%>
        <jsp:forward page="ErrorPage.jsp" />
<%
    }
    
    int scheduleId = schedule.getScheduleId();
    int totalDays = schedule.getTotalDays();
    Map<Integer, Map<String, Activity>> totalSchedule = schedule.getOverallSchedule();
    
    String[] timeSlots = Schedule.TIMESLOTS;
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
                    <li><a href="<%=request.getContextPath()%>/Scheduluxe/Home.jsp">Home</a></li>  
                    <li><a href="<%=request.getContextPath()%>/Scheduluxe/Aboutus.jsp">About us</a></li> 
                    <li><a href="<%=request.getContextPath()%>/Scheduluxe/EditProfile.jsp"><span class="material-symbols-outlined">account_circle</span></a></li>  
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
                    <% for (String timeSlot : timeSlots) { %>
                    <tr>
                        <td><%= timeSlot %></td>
                        <% for (int day = 1; day <= totalDays; day++) { 
                            Map<String, Activity> daySchedule = totalSchedule.get(day);
                            Activity activity = daySchedule != null ? daySchedule.get(timeSlot) : null; %>
                        <td>
                            <% if (activity != null) { %>
                                <%= activity.getActivityName() %>
                            <%}%>
                        </td>
                        <% } %>
                    </tr>
                    <% } %>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="<%=totalDays + 1%>" class="text-end">
                            <button class="btn download-btn">
                                <span class="glyphicon glyphicon-download-alt"></span> Schedule
                            </button>
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>
        <div class="feedback-container">
            <form action="<%=request.getContextPath()%>/servlet/FeedbackServlet" method="POST">
                <div class="comment-box">
                    <h3>Leave a Comment & Rate us</h3>
                    <div class="comment-input">
                        <%
                            if ((schedule.getComment()).equals("no comment")) {
                        %>
                                <textarea name="comment" class="form-control" rows="3" placeholder="Share your thoughts about the schedule..."></textarea>
                        <%
                            } else {
                        %>   
                                <textarea name="comment" class="form-control" rows="3"><%= schedule.getComment() %></textarea>
                        <%
                            }
                        %>
                    </div>
                </div>
                <div class="stars">
                    <%
                        int rating = schedule.getRating();
                    %>
                    <input class="star star-5" id="star-5" type="radio" name="rating" value="5" <%= (rating == 5 ? "checked" : "") %> />
                    <label class="star star-5" for="star-5"></label>
        
                    <input class="star star-4" id="star-4" type="radio" name="rating" value="4" <%= (rating == 4 ? "checked" : "") %> />
                    <label class="star star-4" for="star-4"></label>
        
                    <input class="star star-3" id="star-3" type="radio" name="rating" value="3" <%= (rating == 3 ? "checked" : "") %> />
                    <label class="star star-3" for="star-3"></label>
        
                    <input class="star star-2" id="star-2" type="radio" name="rating" value="2" <%= (rating == 2 ? "checked" : "") %> />
                    <label class="star star-2" for="star-2"></label>
        
                    <input class="star star-1" id="star-1" type="radio" name="rating" value="1" <%= (rating == 1 ? "checked" : "") %> />
                    <label class="star star-1" for="star-1"></label>
                </div>
                <input type="hidden" id="ratingValue" name="rating" value="0" />
                <div class="submit-box">
                    <button type="submit" class="btn submit-btn">Submit</button>
                </div>
            </form>
        </div>
        <script>
            document.querySelectorAll('input[name="rating"]').forEach(function(star) {
                star.addEventListener('change', function() {
                    document.getElementById('ratingValue').value = this.value;
                });
            });
        </script>
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </main>
    <script src="<%=request.getContextPath()%>/js/menuToggle.js"></script>
</body>
</html>
