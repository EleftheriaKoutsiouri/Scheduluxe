<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="ErrorPage.jsp" %>

<!doctype html>
<html lang="en" data-bs-theme="auto">
  <head><script src="<%=request.getContextPath()%>/css/assets/js/color-modes.js"></script>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Scheduluxe team">
    <title>Welcome Page</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/carousel/">

    

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
    <link href="<%=request.getContextPath()%>/css/assets/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        width: 100%;
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }

      .btn-bd-primary {
        --bd-violet-bg: rgb(218, 178, 174);
        --bd-violet-rgb: 112.520718, 44.062154, 249.437846;

        --bs-btn-font-weight: 600;
        --bs-btn-color: var(--bs-white);
        --bs-btn-bg: var(--bd-violet-bg);
        --bs-btn-border-color: var(--bd-violet-bg);
        --bs-btn-hover-color: var(--bs-white);
        --bs-btn-hover-bg: #884d4d;
        --bs-btn-hover-border-color: #884d4d;
        --bs-btn-focus-shadow-rgb: var(--bd-violet-rgb);
        --bs-btn-active-color: var(--bs-btn-hover-color);
        --bs-btn-active-bg: #884d4d;
        --bs-btn-active-border-color: #884d4d;
      }

      .bd-mode-toggle {
        z-index: 1500;
      }

      .bd-mode-toggle .dropdown-menu .active .bi {
        display: block !important;
      }

    /* Column styling */
    .col-lg-4 {
      padding: 30px; /* Padding inside the column */
      background-color: #fdf1f1; /* Light background */
      border-radius: 10px; /* Rounded corners */
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Soft shadow */
      width: 600px; /* Increased width */
      height: auto; /* Auto height to adjust to content */
      margin: 0 auto; /* Center the column */
    }

    /* Heading styling */
    .fw-normal h2 {
      font-size: 2rem; /* Font size for the heading */
      color: #333; /* Dark text */
      margin-bottom: 20px; /* Space below the heading */
    }

    /* Paragraph styling */
    .col-lg-4 p {
      font-size: 1.125rem; /* Font size for the paragraph */
      color: #000000; /* Slightly lighter text */
      line-height: 1.6; /* Spacing between lines */
      margin-bottom: 20px; /* Space below the paragraph */
    }

    /* Button styling */
    .btn-secondary {
      font-size: 1rem; /* Font size for the button */
      padding: 12px 30px; /* Button padding */
      background-color: rgb(218, 178, 174); /* Blue background */
      color: white; /* White text */
      border-radius: 25px; /* Rounded corners */
      text-decoration: none; /* Remove underline */
      transition: background-color 0.3s; /* Smooth transition */
    }

    /* Button hover effect */
    .btn-secondary:hover {
      background-color: #884d4d; /* Darker blue on hover */
    }

    @media (max-width: 768px) {
    .col-lg-4 {
      width: 90%; /* Reduce width for smaller screens */
    }
  }

  @media (max-width: 768px) {
  #myCarousel {
    display: none; /* Hide the carousel on screens smaller than 768px */
  }
}
  </style>
    

    
    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/css/carousel.css" rel="stylesheet">
  </head>
  <body>
  
<header data-bs-theme="light">
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

<main>

  <div id="myCarousel" class="carousel slide mb-6" data-bs-ride="carousel">
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
      <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="<%=request.getContextPath()%>/images/parthenon.jpg" class="d-block w-100" alt="A trip into the beauty of Madrid">
        <div class="container">
          <div class="carousel-caption text-start">
            <h1>Plan Your trip with Scheduluxe</h1>
            <p class="opacity-75">Effortlessly create personalized itineraries and discover destinations tailored just for you.</p>
          </div>
        </div>
      </div>
      <div class="carousel-item">
        <img src="<%=request.getContextPath()%>/images/Brussel.jpg" class="d-block w-100" alt="A trip into the beauty of Madrid">
        <div class="container">
          <div class="carousel-caption">
            <h1>Customize and Save Your Perfect Trip</h1>
            <p>With Scheduluxe, you can craft a personalized travel itinerary based on your preferences.</p>
          </div>
        </div>
      </div>
      <div class="carousel-item">
        <img src="<%=request.getContextPath()%>/images/London2.jpg" class="d-block w-100" alt="Paris">
        <div class="container">
          <div class="carousel-caption text-end">
            <h1>Start Planning Your Dream Trip Today!</h1>
            <p>Don't wait any longer, get ready to explore the world!</p>
          </div>
        </div>
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>


  <!-- Marketing messaging and featurettes
  ================================================== -->
  <!-- Wrap the rest of the page in another container to center all the content. -->

  <div class="container marketing">

    <!-- Three columns of text below the carousel -->
    <div class="row justify-content-center align-items-center">
      <div class="col-lg-4">
        <h2 class="fw-normal"><strong>Discover the world one step at a time</strong></h2>
        <p>If you're looking for experiences that match your unique interests and travel style, you've come to the right place! Choose your destination, and let us create a personalized travel plan just for you. From historical landmarks and gourmet delights to hidden trails and unique activities we’ve got everything  you need <br>to make unforgettable memories.</p>
        <p><a class="btn btn-secondary" href="Register.jsp">Start the experience→ &raquo;</a></p>
      </div><!-- /.col-lg-4 --><!-- /.col-lg-4 -->
    </div><!-- /.row -->


    <!-- START THE FEATURETTES -->

    <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-7">
        <h2 class="featurette-heading fw-normal lh-1">The Eternal City Awaits:<span class="text-body-secondary"> Rome, Italy</span></h2>
        <p class="lead">Rome, Italy’s timeless capital, offers a captivating journey through history, art, and architecture. From the grandeur of the Colosseum to the sacred beauty of the Vatican, the city is a living museum of ancient wonders. Whether you're strolling through cobbled streets or savoring authentic Italian cuisine, Rome is a destination where every moment feels like a step back in time, wrapped in modern vibrancy.</p>
      </div>
      <div class="col-md-5">
        <img src="<%=request.getContextPath()%>/images/Rome.png" class="featurette-image img-fluid mx-auto" width="500" height="500" alt="Description of the image">
      </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-7 order-md-2">
        <h2 class="featurette-heading fw-normal lh-1">A City of History and Innovation:<span class="text-body-secondary"> Berlin, Germany</span></h2>
        <p class="lead">Berlin, the vibrant capital of Germany, invites you to explore its rich history and dynamic culture.A city where history meets modernity. Explore iconic landmarks like the Brandenburg Gate, vibrant neighborhoods, and a rich cultural scene.</p>
      </div>
      <div class="col-md-5 order-md-1">
        <img src="<%=request.getContextPath()%>/images/Berlin.avif" class="featurette-image img-fluid mx-auto" width="500" height="500" alt="Description of the image">
      </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-7">
        <h2 class="featurette-heading fw-normal lh-1">A Timeless Marvel: <span class="text-body-secondary"> Hagia Sophia, Turkey</span></h2>
        <p class="lead">The Hagia Sophia, an architectural masterpiece and symbol of history, awaits your exploration in Istanbul. Its majestic dome and intricate mosaics tell tales of empires, blending the grandeur of Byzantine and Ottoman eras. A journey to this iconic landmark promises a blend of awe, culture, and timeless beauty—a must-see for any traveler seeking the extraordinary.</p>
      </div>
      <div class="col-md-5">
        <img src="<%=request.getContextPath()%>/images/Aghia-Sophia.webp" class="featurette-image img-fluid mx-auto" width="500" height="500" alt="Description of the image">
      </div>
    </div>

    <hr class="featurette-divider">

    <!-- /END THE FEATURETTES -->

  </div><!-- /.container -->


  <!-- FOOTER -->
  <footer class="container">
    <p>&copy;<strong>2024 Scheduluxe. All rights reserved. </strong>| Designed with care to make your travel planning effortless.</p>
  </footer>
</main>
<script src="<%=request.getContextPath()%>/css/assets/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
