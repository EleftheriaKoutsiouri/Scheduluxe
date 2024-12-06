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

  
        .btn {
  display: block;
  width: 100%;
  padding: 10px;
  background-color: rgb(218, 178, 174);
  color: #ffffff;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.btn:hover {
  background-color: #884d4d;
}



@media (max-width: 768px) {
  .sign-in-form,
  .sign-up-form {
    width: 90%;
    margin: 0 auto;
    padding: 20px;
  }
}

@media (max-width: 480px) {
  .sign-in-form,
  .sign-up-form {
    width: 95%;
    padding: 15px;
  }
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
              </div>
          </div>
      </nav>
  </header>
  <main>
    <div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
        <!-- Sign In Form -->
        <div class="card shadow p-4 sign-in-form" style="width: 100%; max-width: 400px; font-family: 'Poppins', sans-serif;">
            <h2 class="text-center mb-4">Sign In</h2>
            <% if (request.getAttribute("message") != null) { %>
                <div class="alert alert-danger text-center" role="alert">
                    <%= (String) request.getAttribute("message") %>
                </div>
            <% } %>
            <form method="post" action="Signin_Controller.jsp">
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Enter your username" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
                </div>
                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">Sign In</button>
                </div>
                <div class="text-center mt-3">
                    <span>Don't have an account? </span>
                    <a href="javascript:void(0);" onclick="toggleForms('sign-up')" style="color: black; text-decoration: underline; cursor: pointer;">Sign up now</a>
                </div>
            </form>
        </div>
    
        <!-- Sign Up Form (Initially hidden) -->
        <div class="card shadow p-4 sign-up-form" style="width: 100%; max-width: 400px; font-family: 'Poppins', sans-serif; display: none;">
            <h2 class="text-center mb-4">Sign Up</h2>
            <form method="post" action="Signup_Controller.jsp">
                <div class="mb-3">
                    <label for="newUsername" class="form-label">Username</label>
                    <input type="text" class="form-control" id="newUsername" name="newUsername" placeholder="Enter your username" required>
                </div>
                <div class="mb-3">
                    <label for="newPassword" class="form-label">Password</label>
                    <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="Enter your password" required>
                </div>
                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">Sign Up</button>
                </div>
                <div class="text-center mt-3">
                    <span>Already have an account? </span>
                    <a href="javascript:void(0);" onclick="toggleForms('sign-in')" style="color: black; text-decoration: underline; cursor: pointer;">Sign in now</a>
                </div>
            </form>
        </div>
    </div>
    
</main>

    <script>
        function toggleForms(formType) {
            const signInForm = document.querySelector('.sign-in-form');
            const signUpForm = document.querySelector('.sign-up-form');

            if (formType === 'sign-up') {
                signInForm.style.display = 'none';
                signUpForm.style.display = 'block';
            } else {
                signInForm.style.display = 'block';
                signUpForm.style.display = 'none';
            }
        }
    </script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>
