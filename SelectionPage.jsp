<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ScheduluxeClasses.*" %>
<%@ page import="java.util.List" %>
<%
    // Creating an object of the Schedule class
    Schedule sch = new Schedule();

    // Fetching data from the database
    List<String> destinations = sch.fetchDestinations();
    List<String> types = sch.fetchTypes();
    List<String> budget = sch.fetchBudgets();

    Traveler trv = (Traveler) session.getAttribute("travelerObj");
        
    if (trv == null) {
        request.setAttribute("message","You are not authorized to access this resource. Please login.");
    %>
        <jsp:forward page="Signin.jsp"/>
    <% 
        return;
    }
    %>


    
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Preferences</title>
    <%@ include file="header.jsp" %>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/SelectionPage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>

<body>
    <header>
        <!-- Navigation bar -->
        <nav class="nav-menu">
            <div class="logo">
                <img src="%=request.getContextPath()%>/images/logo.png" alt="Icon">
                <h1>Scheduluxe</h1>
            </div>

            <!-- Hamburger Menu Button -->
            <button class="menu-toggle">
                <span class="hamburger-icon">&#9776;</span>
            </button>
            <div class="dropdown">
                <ul class="nav-links">
                    <li><a href="WelcomePage.jsp">Home</a></li>
                    <li><a href="About.jsp">About us</a></li>
                    <li><a href="SignIn.jsp">Get Started</a></li>
                    <li><a href="EditProfile.jsp"><span class="material-symbols-outlined">account_circle</span></a></li>
                </ul>
            </div>
        </nav>
    </header>
    <main>
        <h2>
            <span class="first-text">Unlock Unforgettable Experiences</span><br>
            <span class="second-text">by simply sharing your preferences</span>
        </h2>
        <div class="search-container">   
            <form action="PreferencesServlet" method="post">
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
                    <label for="date">Days</label>
                    <input type="number" min="1" max="4" id="date" name="date" placeholder="Select days" required>
                </div>

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
                        // Toggle the visibility of the dropdown content
                        content.style.display = content.style.display === "block" ? "none" : "block";
                    }
                
                    // Close the dropdown if the user clicks anywhere outside of it
                    window.onclick = function(event) {
                        var dropdownContent = document.querySelector('.dropdown-content');
                        var dropdownButton = document.querySelector('.dropdown button');
                        if (!dropdownButton.contains(event.target) && !dropdownContent.contains(event.target)) {
                            dropdownContent.style.display = "none";  // Close dropdown if clicked outside
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
