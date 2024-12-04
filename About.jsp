<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
    <header>   
        <nav class="navbar navbar-expand-md navbar-light fixed-top bg-light">
            <div class="container-fluid">
              <a class="navbar-brand" href="#">Scheduluxe</a>
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                  <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="WelcomePage.jsp">Home</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="About.jsp">About Us</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="Register.jsp">Register</a>
                  </li>
                </ul>
                <!--<form class="d-flex" role="search">
                  <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                  <button class="btn btn-outline-success" type="submit">Search</button>
                </form>-->
              </div>
            </div>
          </nav>
    </header>
    <br>
    <br>
    
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
