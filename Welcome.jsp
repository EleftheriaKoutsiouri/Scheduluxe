

<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="ErrorPage.jsp" %>

<!DOCTYPE html>  
<html lang="en">  
<head>  

    <title>Welcome to Scheduluxe</title>  
    <!--Link to fonts and icon from google-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/WelcomePage.css">  
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">  
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Praise&display=swap"> 
</head>  
<body>
    <header class="header">   
        <%
    request.setAttribute("activePage", "Home"); 
%>
<%@ include file="navbar.jsp" %>
    </header>  
    
    <!-- Main content section -->
    <main>
        <div class="info-container">
            <h2>
                <span class="discover-text">DISCOVER</span> 
                <span class="world-text">The World</span><br>
                <span class="step-text">One Step At A Time!</span>
            </h2>
                <p>If you're looking for experiences that match your unique interests <br>and travel style, you've come to the right place!<br> Choose your destination, and let us create a personalized travel plan just for you.<br> From historical landmarks and gourmet delights to hidden trails<br> and unique activities we’ve got everything  you need <br>to make unforgettable memories.</p>
                <a href="Register.jsp">
                    <button type="button" class="start-button">Start the experience →</button>
                </a>
        </div>
            <!-- Carousel on the right side -->
        <div class="carousel">
            <img src="<%=request.getContextPath()%>/images/Aghia-Sophia.webp" class="active"alt="Image 1">
            <img src="<%=request.getContextPath()%>/images/Aerostata.png" alt="Image 2">
            <img src="<%=request.getContextPath()%>/images/Nisi.png" alt="Image 3">
            <img src="<%=request.getContextPath()%>/images/Praga.jfif" alt="Image 4">
            <img src="<%=request.getContextPath()%>/images/Thallasa.png" alt="Image 5">
        </div>
    </main> 
    
    <!-- Link to the external JavaScript file that handles the carousel of photos -->
    <script src="<%=request.getContextPath()%>/js/carousel.js"></script>

    <!-- Link to the external JavaScript file that handles the hamburger of the menu -->
    <script src="<%=request.getContextPath()%>/js/menuToggle.js"></script>
</body>  
</html>
