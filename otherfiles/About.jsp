<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="ErrorPage.jsp" %>
<%@ page import="Scheduluxe.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="header.jsp"%>
    <title>About Us</title>
    <!-- Link to Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/About.css">    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">  
</head>
<body>
<style>
.nav-link {
  font-size: 16px;
}
.navbar-brand {
    font-family: 'Platypi', sans-serif;
    font-weight: bolder;
    font-size: 35px;
    display: flex;
    align-items: center; /* Aligns logo and text vertically */
  }
  .navbar-brand img {
    margin-right: 10px; /* Space between logo and text */
    height: 40px; /* Ensures consistent size */
  }
  .navbar-nav .nav-link {
    font-family: 'Poppins', sans-serif;
    margin-right: 15px; /* Add spacing between links */
    transition: color 0.3s ease, border-bottom 0.3s ease;
  }
  .navbar-nav .nav-link:hover {
    color: #d042a5; /* Pink hover effect */
  }
  .navbar-nav .nav-link.active {
    color: #c8349c; 
    text-decoration: underline; 
  }

  </style>

</head>

<body>
  <header>
      <nav class="navbar navbar-expand-md navbar-light bg-light">
          <div class="container-fluid">
              <!-- Logo με εικόνα -->
              <a class="navbar-brand d-flex align-items-center">
                  <img src="<%=request.getContextPath()%>/images/logo.png" alt="Scheduluxe Logo" width="40" height="40" class="d-inline-block align-text-top">
                  <span class="ms-2">Scheduluxe</span>
              </a>
  
              <!-- Burger menu για κινητά -->
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
              </button>
  
              <!-- Links στα δεξιά -->
              <div class="collapse navbar-collapse justify-content-end" id="navbarCollapse">
                  <ul class="navbar-nav mb-2 mb-md-0">
                      <li class="nav-item">
                          <a class="nav-link" aria-current="page" href="WelcomePage.jsp">Home</a>
                      </li>
                      <li class="nav-item">
                          <a class="nav-link active" href="About.jsp">About Us</a>
                      </li>
                      
                      <%
                      if (session.getAttribute("travelerObj") == null) {
                      %>
                      <li class="nav-item">
                        <a class="nav-link" href="Register.jsp">Register</a>
                      </li>
                      <%
                      } // Properly close the if statement
                      %>
              </div>
          </div>
      </nav>
  </header>
    <main>
            <!-- Left-aligned content -->
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

            <!-- Carousel on the right side -->
            <div class="carousel">
                <img src="<%=request.getContextPath()%>/images/Berlin.avif" class="active"alt="Image 1">
                <img src="<%=request.getContextPath()%>/images/Ellada.jpg" alt="Image 2">
                <img src="<%=request.getContextPath()%>/images/Statue of liberty.jpg" alt="Image 3">
                <img src="<%=request.getContextPath()%>/images/Brussel.jpg" alt="Image 4">
                <img src="<%=request.getContextPath()%>/images/Akropoli.jpg" alt="Image 5">
                <img src="<%=request.getContextPath()%>/images/Snow.jpg" alt="Image 5">
            </div>
    </main>

    <!-- Link to the external JavaScript file that handles the carousel of photos -->
    <script src="<%=request.getContextPath()%>/js/carousel.js"></script>

    <!-- Link to Bootstrap JS (Popper.js is required for dropdowns, tooltips, etc.) -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>
