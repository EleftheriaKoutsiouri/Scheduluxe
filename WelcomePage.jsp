<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>  
<html lang="en">  
<head>  
    <meta charset="UTF-8">  
    <meta name="viewport" content="width=device-width, initial-scale=1.0">  
    <title>Welcome to Scheduluxe</title>  
    <%@ include file="header.jsp"%>
    <!--Link to fonts and icon from google-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/WelcomePage.css">  
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">  
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Praise&display=swap"> 
</head>  
<body>
    <header class="header">   
        <!-- Navigation bar -->  
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
                    <li><a href="WelcomePage.jsp" class="active">Home</a></li> 
                    <li><a href="About.jsp">About us</a></li>
                    <li><a href="SignIn.jsp">Get Started</a></li>  
                    <li><a href="EditProfile.jsp"><span class="material-symbols-outlined">account_circle</span></a></li>  
                </ul> 
            </div>
        </nav>
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
                <a href="SelectionPage.jsp">
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
