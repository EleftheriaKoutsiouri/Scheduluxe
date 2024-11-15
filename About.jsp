<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
     <%@ include file="header.jsp" %>
    <title>About Us</title>">
    <link rel="stylesheet" href="About.css">    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">  
</head>
<body>
    <header>   
        <!-- Navigation bar -->  
        <nav class="nav-menu">  
            <div class="logo">  
                <img src="images/logo.png" alt="Icon">
                <h1>Scheduluxe</h1>
            </div>  
        
            <!-- Hamburger Menu Button -->
            <button class="menu-toggle">
                <span class="hamburger-icon">&#9776;</span>
            </button>
            <div class="dropdown">
                <ul class="nav-links">  
                    <li><a href="WelcomePage.html">Home</a></li>  
                    <li><a href="About.jsp" class="active">About us</a></li>
                    <li><a href="SignIn.jsp">Get Started</a></li>  
                    <li><a href="Profile.jsp"><span class="material-symbols-outlined">account_circle</span></a></li>  
                </ul> 
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
                <img src="images/Berlin.avif" class="active"alt="Image 1">
                <img src="images/Ellada.jpg" alt="Image 2">
                <img src="images/Statue of liberty.jpg" alt="Image 3">
                <img src="images/Brussel.jpg" alt="Image 4">
                <img src="images/Akropoli.jpg" alt="Image 5">
                <img src="images/Snow.jpg" alt="Image 5">
            </div>
    </main>

    <!-- Link to the external JavaScript file that handles the carousel of photos -->
    <script src="js/carousel.js"></script>

    <!-- Link to the external JavaScript file that handles the hamburger of the menu -->
    <script src="js/menuToggle.js"></script>
</body>
</html>
