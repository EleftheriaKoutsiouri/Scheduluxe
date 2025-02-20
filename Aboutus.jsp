<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String currentURI = request.getRequestURI();
session.setAttribute("targetPage", currentURI);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="head.jsp"%>
    <title>About Us</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/About.css">    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">  
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
                    <li><a href="<%=request.getContextPath()%>/Scheduluxe/Aboutus.jsp" class="active">About us</a></li>
                    <%if (session.getAttribute("travelerObj") == null) {%>
                       <li><a href="<%=request.getContextPath()%>/Scheduluxe/Connect.jsp">Get Started</a></li> 
                    <%}%>
                    <%if (session.getAttribute("travelerObj") != null) {%>
                       <li><a href="<%=request.getContextPath()%>/Scheduluxe/logout.jsp"><span class="material-symbols-outlined">logout</span></a></li>
                    <%}%>
                       <li><a href="<%=request.getContextPath()%>/Scheduluxe/EditProfile.jsp"><span class="material-symbols-outlined">account_circle</span></a></li>  
                </ul> 
            </div>
        </nav>
    </header>
    <main>
        <div class="content">
            <h1 class="heading">About Us</h1><br>
            <p class="paragraph">
                At Scheduluxe, we're passionate about making travel planning effortless and personalized.
                Simply choose your destination, dates, group size, and preferences, and our 
                platform will create a unique itinerary tailored just for you. Our team of dedicated undergraduate 
                students is building this innovative tool to help travelers discover the best experiences, no matter where they go. 
                Whether you're seeking adventure, relaxation, or something in between, we’ve got your next trip covered!
            </p>
        </div>
        <div class="carousel">
            <img src="<%=request.getContextPath()%>/images/mad.jpg" class="active"alt="Image 1">
            <img src="<%=request.getContextPath()%>/images/London.png" alt="Image 2">
            <img src="<%=request.getContextPath()%>/images/Santorini.png" alt="Image 2">
            <img src="<%=request.getContextPath()%>/images/Statue of liberty.jpg" alt="Image 3">
            <img src="<%=request.getContextPath()%>/images/Akropoli.jpg" alt="Image 5">
            <img src="<%=request.getContextPath()%>/images/Snow.jpg" alt="Image 5">
        </div>
    </main>
    <script src="<%=request.getContextPath()%>/js/carousel.js"></script>
    <script src="<%=request.getContextPath()%>/js/menuToggle.js"></script>
</body>
</html>
