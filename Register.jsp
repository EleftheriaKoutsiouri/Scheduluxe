<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="ErrorPage.jsp" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <%@ include file="header.jsp" %>
    <title>Register</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Register.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <script src="https://kit.fontawesome.com/bdd8380312.js" crossorigin="anonymous"></script>
</head>

<body>
    <header>
        <nav class="nav-menu">  
            <div class="logo">  
                <img src="<%= request.getContextPath()%>/images/logo.png" alt="Icon">
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
                    <li><a href="SignIn.jsp" class="active">Get Started</a></li>
                    <li><a href="EditProfile.jsp"><span class="material-symbols-outlined">account_circle</span></a></li>  
                </ul>  
            </div>
        </nav>
    </header>

    <main>
        <% if(request.getAttribute("message") != null) { %>		
            <div class="alert alert-danger text-center" role="alert"><%=(String)request.getAttribute("message") %></div>
        <% } %>
        <div class="container">
            <input type="checkbox" id="flip">
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
        </div>
        <div class="phone-only">
            <div class="sign-in-form">
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
                    <div class="mb-3">
                        <label for="signUpPassword" class="form-label">Confirm Password</label>
                        <input type="password" class="form-control" id="signUpPassword" name="password" placeholder="Confirm your password" required>
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
    <script src="<%= request.getContextPath()%>/js/menuToggle.js"></script>
    <script src="<%=request.getContextPath()%>/js/toggleForms.js"></script>

</body>
</html>
