<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Scheduluxe.*" %>
<%@ page errorPage="ErrorPage.jsp" %>

<%@ include file="AuthenticationGuard.jsp" %>

<!--<%
if (session.getAttribute("travelerObj") == null) {
    response.sendRedirect("Connect.jsp?redirect=EditProfile.jsp");
    return;
}
%>-->

<%
    // Λήψη του username από το session

    Traveler traveler = (Traveler) session.getAttribute("travelerObj");

    // Αν ο χρήστης υπάρχει, φορτώνονται τα δεδομένα του
    String firstname = traveler != null ? traveler.getFirstname() : "";
    String lastname = traveler != null ? traveler.getLastname() : "";
    String username = traveler != null ? traveler.getUsername() : "";
    String email = traveler != null ? traveler.getEmail() : "";
    String country = traveler != null ? traveler.getCountry() : "";
    String password = traveler != null ? traveler.getPassword() : "";
%>

<!DOCTYPE html>
<html lang="en">
    <head> 
        <%@ include file="head.jsp"%>
        <title>Profile</title>
        <!--link to css, fonts and icon from google-->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Profile.css">
        <!--bootstrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <!--icons-->
        <script src="https://kit.fontawesome.com/bdd8380312.js" crossorigin="anonymous"></script>
    </head>
    <body class="body1">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <header>
          <nav class="nav-menu">
              <div class="logo">  
                <img src="<%=request.getContextPath()%>/images/logo.png" alt="Icon">
                <h1><span class="white-title">Scheduluxe</span></h1>
            </div>
            <!-- Hamburger Menu Button -->
            <button class="menu-toggle">
                <span class="hamburger-icon">&#9776;</span>
            </button>
            <div class="dropdown">
                <ul class="nav-links">  
                    <li><a href="<%=request.getContextPath()%>/Scheduluxe/First.jsp">Home</a></li>
                    <li><a href="<%=request.getContextPath()%>/Scheduluxe/Aboutus.jsp">About us</a></li>
		    <li><a href="<%=request.getContextPath()%>/Scheduluxe/logout.jsp">Log out</a></li>
                    <li><a href="<%=request.getContextPath()%>/Scheduluxe/EditProfile.jsp" class="active"><span class="material-symbols-outlined">account_circle</span></a></li>
                    
                </ul>  
            </div>
          </nav>
      </header>
        <main>
            <div class="profile-schedules-container">
                <div class="edit-profile">
                    <h4 class="small-title"><strong>Edit your Profile</strong></h4>
                    <% if("Success".equals(request.getAttribute("message"))) { %>
                        <h4>Changes were saved</h4>
                    <% } else if ("Failure".equals(request.getAttribute("message"))) { %>
                        <h4>Failed to save changes</h4>
                    <% } %>
                    <form class="row g-3" action="<%=request.getContextPath()%>/servlet/EditProfileUserServlet" method="POST">
                        <div class="col-md-4">
                            <label for="firstname" class="form-label">First Name</label>
                            <input type="text" name="firstname" class="form-control" id="firstname" value="<%= firstname != null ? firstname : "" %>">
                        </div>
                        <div class="col-md-4">
                            <label for="lastname" class="form-label">Last Name</label>
                            <input type="text" name="lastname" class="form-control" id="lastname" value="<%= lastname != null ? lastname : "" %>">
                        </div>
                        <div class="col-4">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" name="username" class="form-control" id="username" value="<%= username %>" readonly>
                        </div>
                        <div class="col-12">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" name="email" class="form-control" id="email" value="<%= email != null ? email : "" %>">
                        </div>
                        <div class="col-md-6">
                            <label for="country" class="form-label">Country of Origin</label>
                            <input type="text" name="country" class="form-control" id="country" value="<%= country != null ? country : "" %>">
                        </div>
                        <div class="col-md-6">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" name="password" class="form-control" id="password">
                        </div>
                        <div class="col-12">
                            <button type="submit" class="start-button">Save Changes</button>
                        </div>
                    </form>
                </div>
                
                <div class="past-schedules">
                    <h4 class="small-title"><strong>See your Past Schedules</strong></h4>
                    <div class="card-container">
                        <div class="card" style="width: 19rem;">
                            <img src="<%=request.getContextPath()%>/images/Germany.png" class="card-img-top" alt="...">
                            <div class="card-body">
                                
                                <button type="button" class="start-button">Press here</button><!--dispatcher ή link για overall του past schedule -->
                            </div>
                        </div>
                        <div class="card" style="width: 19rem;">
                            <img src="<%=request.getContextPath()%>/images/London.png" class="card-img-top" alt="...">
                            <div class="card-body">
                                
                                <button type="button" class="start-button">Press here</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <script src="<%=request.getContextPath()%>/js/menuToggle.js"></script>
    </body>
</html>

