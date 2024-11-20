<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <%@ include file="header.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/SignIn.css">
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
                    <div class="login-form">
                        <div class="title">Sign In</div>
                        <form action="#">
                            <div class="input-boxes">
                                <div class="input-box">
                                    <i class="fas fa-user"></i>
                                    <input type="text" placeholder="Enter your username" required>
                                </div>
                                <div class="input-box">
                                    <i class="fas fa-lock"></i>
                                    <input type="password" placeholder="Enter your password" required>
                                </div>
                                <div class="button input-box">
                                    <input type="submit" value="Submit" id="submit-button">
                                </div>
                                <div class="text sign-up-text">Don't have an account? 
                                    <label for="flip">Sign up now</label>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="signup-form">
                        <div class="title">Sign up</div>
                        <form action="#">
                            <div class="input-boxes">
                                <div class="input-box">
                                    <i class="fas fa-user"></i>
                                    <input type="text" placeholder="Enter your name" required>
                                </div>
                                <div class="input-box">
                                    <i class="fas fa-envelope"></i>
                                    <input type="text" placeholder=" Enter your email" required>
                                </div>
                                <div class="input-box">
                                    <i class="fas fa-lock"></i>
                                    <input type="password" placeholder="Enter your password" required>
                                </div>
                                <div class="button input-box">
                                    <input type="submit" value="Submit" id="submit-button">
                                </div>
                                <div class="text sign-up-text">Already have an account? 
                                    <label for="flip">Sign In now</label>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <!-- Link to the external JavaScript file that handles the hamburger of the menu -->
    <script src="<%= request.getContextPath()%>/js/menuToggle.js"></script>
</body>
</html>
