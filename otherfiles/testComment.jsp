<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="Scheduluxe.*" %>


<%

String userId = request.getParameter("userId");
String scheduleId = request.getParameter("scheduleId");
String comment = request.getParameter("comment");
String rating = request.getParameter("rating");
String error = (String)request.getAttribute("error");
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

    <%=userId %>
    <br>
    <%=scheduleId %>
    <br>
    <%=comment %>
    <br>
    <%=rating %>

    <%=error %>


</body>
</html>

