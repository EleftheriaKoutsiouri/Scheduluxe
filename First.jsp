<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Scheduluxe.*" %>
<!DOCTYPE html>  
<html lang="en">  
<head>  
    <meta charset="UTF-8">  
    <meta name="viewport" content="width=device-width, initial-scale=1.0">  
    <title>Welcome to Scheduluxe</title>  
    <link rel="icon" href="<%= request.getContextPath() %>/images/logo.png" type="image/x-icon">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/First.css">  
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <%@ include file="head.jsp" %>
</head>  
<body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <header class="header">   
        <!-- Navigation bar -->  
        <nav class="nav-menu">  
            <div class="logo">  
                <img src="<%= request.getContextPath() %>/images/logo.png" alt="Icon">
                <h1>Scheduluxe</h1>
            </div>
            <style>
               body {
                position: relative; 
                margin: 0; 
                height: 100vh; 
                background: linear-gradient(45deg, #f4f4f4 60%, #f5d9f3fe 80%);
              }
            </style>
            <!-- Hamburger Menu Button -->
            <button class="menu-toggle">
              <span class="hamburger-icon">&#9776;</span>
            </button>
            <div class="dropdown">
                <ul class="nav-links">  
                    <li><a href="<%=request.getContextPath()%>/Scheduluxe/First.jsp" class="active">Home</a></li> 
                    <li><a href="<%=request.getContextPath()%>/Scheduluxe/Aboutus.jsp">About us</a></li>
                    <%
                    if (session.getAttribute("travelerObj") == null) {
                    %>
                    <li><a href="<%=request.getContextPath()%>/Scheduluxe/Connect.jsp">Get Started</a></li> 
                    <%
                    }
                    %>
                    <%
                    if (session.getAttribute("travelerObj") != null) {
                    %>
                    <li><a href="<%=request.getContextPath()%>/Scheduluxe/logout.jsp"><span class="material-symbols-outlined">logout</span></a></li>
                    <%
                    }
                    %>
                    <li><a href="EditProfile.jsp"><span class="material-symbols-outlined">account_circle</span></a></li>  
                </ul> 
            </div>
        </nav>
    </header>  
    
    <!-- Main content section -->
    <br>
    <main>
        <div class="container">
            <div class="info-container">
                <h2>
                    <span class="discover-text">DISCOVER</span> 
                    <span class="world-text">The World</span><br>
                    <span class="step-text">One Step At A Time!</span>
                </h2>
                <p>If you're looking for experiences that match your unique interests and travel style, you've come to the right place!<br> Choose your destination, and let us create a personalized travel plan just for you.<br> From historical landmarks and gourmet delights to hidden trails and unique activities we’ve got everything  you need <br>to make unforgettable memories.</p>
                <%
                if (session.getAttribute("travelerObj") == null) {
                %>
                <a href="Connect.jsp">
                  <button type="button" class="start-button">
                      Start the experience <span class="glyphicon glyphicon-plane"></span> 
                  </button>
                </a>
                <% } else { %>
                <a href="Selection.jsp">
                <button type="button" class="start-button">
                      Start the experience <span class="glyphicon glyphicon-plane"></span> 
                </button>
                </a>
                <% } %>
            </div>
            
            <!-- Carousel -->
            <div id="carouselExampleFade" class="carousel slide carousel-fade carousel-container" data-bs-ride="carousel">
                <div class="carousel-inner">
                  <div class="carousel-item active">
                    <img src="<%= request.getContextPath() %>/images/Berlin.avif" class="d-block w-100" alt="...">
                  </div>
                  <div class="carousel-item">
                    <img src="<%= request.getContextPath() %>/images/Paris.png" class="d-block w-100" alt="...">
                  </div>
                  <div class="carousel-item">
                    <img src="<%= request.getContextPath() %>/images/Brussel.jpg" class="d-block w-100" alt="...">
                  </div>
                  <div class="carousel-item">
                    <img src="<%= request.getContextPath() %>/images/Rome.png" class="d-block w-100" alt="...">
                  </div>
                  <div class="carousel-item">
                    <img src="<%=request.getContextPath()%>/images/Praga.jfif" class="d-block w-100" alt="...">
                  </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
    </main> 
    <!-- Link to the external JavaScript file that handles the hamburger menu -->
    <script src="<%= request.getContextPath() %>/js/menuToggle.js"></script>
</body>  
</html>
