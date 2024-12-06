<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Scheduluxe.*" %>
<%@ page import="java.util.List" %>
<%@ page errorPage="ErrorPage.jsp" %>


<%@ include file="AuthenticationGuard.jsp" %>
<%
    CreationSchedule creationSchedule = new CreationSchedule();
    // Fetch data from the database
    List<String> destinations = creationSchedule.fetchDestinations();
    List<String> types = creationSchedule.fetchTypes();
    List<String> budget = creationSchedule.fetchBudgets();

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Preferences</title>
    <%@ include file="header.jsp" %>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/SelectionPage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-light fixed-top bg-light">
            <div class="container-fluid">
              <img src="<%= request.getContextPath()%>/images/logo.png" alt="Icon">
              <a class="navbar-brand" href="#">Scheduluxe</a>
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
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
                  }
                  %>
                  <li class="nav-item">
                    <a class="nav-link" href="EditProfile.jsp"><span class="material-symbols-outlined user-icon">account_circle</span></a>
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
    <br>
    <main>
        <h2>
            <span class="first-text">Unlock Unforgettable Experiences</span><br>
            <span class="second-text">by simply sharing your preferences</span>
        </h2>
        <div class="search-container">   
            <form action="<%= request.getContextPath()%>/servlet/CreationScheduleServlet" method="post">
                <!-- Destination Dropdown -->
                <div class="form-group">
                    <label for="destination">Destination</label>
                    <select id="destination" name="destination" required>
                        <option value="" disabled selected hidden>Select destination</option>
                        <% for (String dest : destinations) { %>
                            <option value="<%= dest %>"><%= dest %></option>
                        <% } %>
                    </select>
                </div>

                <!-- Days Input -->
                <div class="form-group">
                    <label for="days">Days</label>
                    <input type="number" min="1" max="3" id="days" name="totalDays" placeholder="Select days" required>
                </div>

                <!-- Type Selection -->
                <div class="form-group">
                    <label for="type">Type</label>
                    <div class="dropdown">
                        <button type="button" onclick="toggleDropdown()">Select Categories</button>
                        <div class="dropdown-content">
                            <% for (String type : types) { %>
                                <div>
                                    <input type="checkbox" id="<%= type %>" name="type" value="<%= type %>">
                                    <label for="<%= type %>"><%= type %></label>
                                </div>
                            <% } %>
                        </div>
                    </div>
                </div>

                <script>
                    // Function to toggle dropdown visibility
                    function toggleDropdown() {
                        var content = document.querySelector('.dropdown-content');
                        content.style.display = content.style.display === "block" ? "none" : "block";
                    }
                
                    // Close the dropdown if the user clicks anywhere outside of it
                    window.onclick = function(event) {
                        var dropdownContent = document.querySelector('.dropdown-content');
                        var dropdownButton = document.querySelector('.dropdown button');
                        if (!dropdownButton.contains(event.target) && !dropdownContent.contains(event.target)) {
                            dropdownContent.style.display = "none";
                        }
                    };
                </script>
            
                <!-- Budget Dropdown -->
                <div class="form-group">
                    <label for="budget">Budget</label>
                    <select id="budget" name="budget" required>
                        <option value="" disabled selected hidden>Select budget</option>
                        <% for (String bdg : budget) { %>
                            <option value="<%= bdg %>"><%= bdg %></option>
                        <% } %>
                    </select>
                </div>
                <button type="submit" class="search-button"><i class="fa fa-search"></i></button>
            </form>
        </div>

        <!-- Image container -->
        <div class="image-container">
            <img src="<%= request.getContextPath()%>/images/Rome.png" alt="Image 1">
            <img src="<%= request.getContextPath()%>/images/Paris.png" alt="Image 2">
            <img src="<%= request.getContextPath()%>/images/Athens.jpg" alt="Image 3">
            <img src="<%= request.getContextPath()%>/images/London2.jpg" alt="Image 4">
        </div>
    </main>
</body>
</html>
