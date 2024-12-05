<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="ErrorPage.jsp" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <%@ include file="header.jsp" %>
    <title>Register</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Register.css">

    <style>
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
        <nav class="navbar navbar-expand-md navbar-light fixed-top bg-light">
            <div class="container-fluid">
              <a class="navbar-brand" href="">Scheduluxe</a>
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                  <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="WelcomePage.jsp">Home</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="About.jsp">About Us</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link active" href="Register.jsp">Register</a>
                  </li>
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

    <main>
        <div class="container">
            <% if(request.getAttribute("message") != null) { %>		
                <div class="alert alert-danger text-center" role="alert"><%=(String)request.getAttribute("message") %></div>
            <% } %>
            <div class="sign-in-form active">
                <h2 class="text-center">Sign In</h2>
                <form method="post" action="Signin_Controller.jsp">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="username" class="form-control" id="username" name="username" placeholder="Enter your username" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
                    </div>
                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">Sign In</button>
                    </div>
                    <div class="text-center mt-3">
                        <span>Don't have an account?  </span>
                        <a href="javascript:void(0);" onclick="toggleForms('sign-up')" style="color: black; text-decoration: underline; cursor: pointer;">Sign up now</a>
                    </div>
                </form>
            </div>
            <div class="sign-up-form" style="display: none;">
                <h2 class="text-center">Sign Up</h2>
                <form method="post" action="Signup_Controller.jsp">
                    <div class="mb-3">
                        <label for="signUpUsername" class="form-label">Username</label>
                        <input type="text" class="form-control" id="signUpUsername" name="username" placeholder="Enter your username" required>
                    </div>
                    <div class="mb-3">
                        <label for="signUpEmail" class="form-label">Email address</label>
                        <input type="email" class="form-control" id="signUpEmail" name="email" placeholder="Enter your email" required>
                    </div>
                    <div class="mb-3">
                        <label for="signUpPassword" class="form-label">Password</label>
                        <input type="password" class="form-control" id="signUpPassword" name="password" placeholder="Enter your password" required>
                    </div>
                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">Sign Up</button>
                    </div>
                </form>
                <div class="text-center mt-3">
                    <span>Already have an account?</span>
                    <a href="javascript:void(0);" onclick="toggleForms('sign-in')" style="color: #000000; text-decoration: underline; cursor: pointer;">Sign in now</a>
                </div>
            </div>        
        </div>
    </main>

    <!-- Link to the external JavaScript file that handles the hamburger of the menu -->
    <script src="<%=request.getContextPath()%>/js/toggleForms.js"></script>
    <!-- Link to Bootstrap JS (Popper.js is required for dropdowns, tooltips, etc.) -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
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

</body>
</html>





<!-- <input type="checkbox" id="flip">
            <div class="cover">
                <div class="front">
                    <img src="<%= request.getContextPath()%>/images/Sign in.avif" alt="Sign in">
                </div>
                <div class="back">
                    <img class="backImg" src="<%= request.getContextPath()%>/images/Sign up.jpg" alt="Sign up">
                </div>
            </div>
            <div class="forms">
                <div class="form-content">
                    <form class="login-form" method="post" action="Signin_Controller.jsp">
                        <div class="title">Sign in</div>
                            <div class="input-boxes">
                                <div class="input-box">
                                    <i class="fas fa-user"></i>
                                    <input type="text" name="username"placeholder="Enter your username" required>
                                </div>
                                <div class="input-box">
                                    <i class="fas fa-lock"></i>
                                    <input type="password" name="password" placeholder="Enter your password" required>
                                </div>
                                <div class="button input-box">
                                    <input type="submit" value="Sign In" id="submit-button">
                                </div>
                                <div class="text sign-up-text">Don't have an account? 
                                    <label for="flip" style="text-decoration: underline" >Sign up now</label>
                                </div>
                            </div>
                    </form>
                    <form class="signup-form" method="post" action="Signup_Controller.jsp">
                        <div class="title">Sign up</div>
                            <div class="input-boxes">
                                <div class="input-box">
                                    <i class="fas fa-user"></i>
                                    <input type="text" name="username" placeholder="Enter your username" required>
                                </div>
                                <div class="input-box">
                                    <i class="fas fa-envelope"></i>
                                    <input type="text" name="email" placeholder=" Enter your email" required>
                                </div>
                                <div class="input-box">
                                    <i class="fas fa-lock"></i>
                                    <input type="password" name="password" placeholder="Enter your password" required>
                                </div>
                                <div class="button input-box">
                                    <input type="submit" value="Sign Up" id="submit-button">
                                </div>
                                <div class="text sign-up-text">Already have an account? 
                                    <label for="flip" style="text-decoration: underline">Sign in now</label>
                                </div>
                            </div>
                    </form>
                </div>
            </div>
        </div>-->