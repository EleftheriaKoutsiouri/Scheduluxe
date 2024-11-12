<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--
<%@ page import="Scheduluxe.*,java.util.*" %>
util για την λιστα, η κλαση scheduluxe και καλα εχει ολα τα αρχεια class μεσα στον web-inf/classes

<%
Εδω θα φτιαχνω ενα αντικειμενο της κλασης schedule με το οποιο θα καλω μεθοδους για να παρω το destinations, preferences κλπ σε μορφη λιστας.

Schedule sch = new Schedule();
List<Destination> destinations = sch.getDestinations();
List<Preferences> preferences = sch.getPreferences();
List<Budget> budget = sch.getBudget();
%>
-->


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Preferences</title>
    <link rel="icon" href="images/logo.png" type="image/x-icon">

    <!-- Link to CSS and fonts -->
    <link rel="stylesheet" href="SelectionPage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Platypi&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Nunito&display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins&display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Praise&display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined">
    <link rel="stylesheet" href="header.css">
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
                    <li><a href="About.html">About us</a></li>
                    <li><a href="SignIn.html">Get Started</a></li>
                    <li><a href="Profile.html"><span class="material-symbols-outlined">account_circle</span></a></li>
                </ul>
            </div>
        </nav>
    </header>

    <!-- Main content section -->
    <main>
        <h2>
            <span class="first-text">Unlock Unforgettable Experiences</span><br>
            <span class="second-text">by simply sharing your preferences</span>
        </h2>

        <!-- Search form -->
        <div class="search-container">
            <form action="PreferenceServlet.java" method="post">
                <div class="form-group">
                    <label for="destination">Destination</label>
                    <select id="destination" name="destination" required>
                        <option value="" disabled selected hidden>Select destination</option>
<!--
<%
for (Destination dest : destinations) {
%>
                    <option value="<%=dest.getId() %>"><%=dest.getName() %></option>
                    
<%
}
%>
                    </select>
-->
                        <option value="france">France</option>  <!-- ΑΥΤΑ ΕΔΩ ΘΑ ΦΥΓΟΥΝ -->
                        <option value="england">England</option> <!-- ΑΥΤΑ ΕΔΩ ΘΑ ΦΥΓΟΥΝ -->
                        <option value="new-york">New York</option><!-- ΑΥΤΑ ΕΔΩ ΘΑ ΦΥΓΟΥΝ -->
                        <option value="italy">Italy</option><!-- ΑΥΤΑ ΕΔΩ ΘΑ ΦΥΓΟΥΝ -->
                        <option value="germany">Germany</option><!-- ΑΥΤΑ ΕΔΩ ΘΑ ΦΥΓΟΥΝ -->
                        <option value="greece">Greece</option><!-- ΑΥΤΑ ΕΔΩ ΘΑ ΦΥΓΟΥΝ -->
                    </select><!-- ΑΥΤΑ ΕΔΩ ΘΑ ΦΥΓΟΥΝ -->
                </div>

                <div class="form-group">
                    <label for="date">Days</label>
                    <input type="number" min="1" max="4" id="date" name="date" placeholder="Select days" required>
                </div>

                <div class="form-group">
                    <label for="type">Type</label>
                    <div class="dropdown" onclick="event.stopPropagation()">
                        <button type="button" onclick="toggleDropdown(event)">Select Categories</button>
                        <div class="dropdown-content">
<!--
<%
for (Preferences prf : preferences) {
%>
                            <div>
                                <input type="checkbox" id="<%=prf.getId() %>" name="type" value="<%=prf.getId()%>">
                                <label for="<%=prf.getId() %>"> <%=prf.getName() %> </label>
                            </div>
<%
}
%>
-->
                            <div>                                                                           <!--ΑΥΤΑ ΘΑ ΦΥΓΟΥΝ-->
                                <input type="checkbox" id="educational" name="type" value="educational">
                                <label for="educational">Educational</label>
                            </div>
                            <div>
                                <input type="checkbox" id="culture" name="type" value="culture">
                                <label for="culture">Culture</label>
                            </div>
                            <div>
                                <input type="checkbox" id="adventure" name="type" value="adventure">
                                <label for="adventure">Adventure</label>
                            </div>
                            <div>
                                <input type="checkbox" id="relaxation" name="type" value="relaxation">
                                <label for="relaxation">Relaxation</label>
                            </div>
                            <div>
                                <input type="checkbox" id="nature" name="type" value="nature">
                                <label for="nature">Nature</label>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="budget">Budget</label>
                    <select id="budget" name="budget" required>
                        <option value="" disabled selected hidden>Select budget</option>

<!--
<%
for (Budget bdg : budget) {
%>
                        <option value="<%= bdg.getid() %>"> <%= bdg.getName()%> </option>
                        
<%
}
%>
                        </select>
-->
                        <option value="" disabled selected hidden>Select budget</option> <!--ΑΥΤΑ ΘΑ ΦΥΓΟΥΝ-->
                        <option value="low">Low Budget</option><!--ΑΥΤΑ ΘΑ ΦΥΓΟΥΝ-->
                        <option value="medium">Budget</option><!--ΑΥΤΑ ΘΑ ΦΥΓΟΥΝ-->
                        <option value="high">High Budget</option><!--ΑΥΤΑ ΘΑ ΦΥΓΟΥΝ-->
                    </select><!--ΑΥΤΑ ΘΑ ΦΥΓΟΥΝ-->
                </div>

                <button type="submit" class="search-button"><i class="fa fa-search"></i></button>
            </form>
        </div>

        <!-- Image container -->
        <div class="image-container">
            <img src="images/Rome.png" alt="Image 1">
            <img src="images/Paris.png" alt="Image 2">
            <img src="images/Athens.jpg" alt="Image 3">
            <img src="images/London2.jpg" alt="Image 4">
        </div>
    </main>

    <!-- Link to the external JavaScript file that handles the hamburger of the menu -->
    <script src="js/menuToggle.js"></script>

    <!-- Link to the external JavaScript file that handles dropdown in the selection of types -->
    <script src="js/selectionTypes.js"></script>
</body>
</html>
