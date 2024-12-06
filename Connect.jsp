<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="head.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login & Sign Up</title>
    <!-- Dynamic path to the CSS file -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/Connect.css">
</head>
<body>
    <header class="header">   
        <!-- Navigation bar -->  
        <nav class="nav-menu">  
            <div class="logo">  
                <img src="<%=request.getContextPath()%>/images/logo.png" alt="Icon">
                <h1>Scheduluxe</h1>
            </div>
        </nav>
    </header>  
    
    <div class="row">
        <div class="col-md-6 mx-auto p-0">
            <div class="card">
                <div class="login-box">
                    <div class="login-snip">
                        <% if (request.getAttribute("message") != null) { %>
                            <div class="alert alert-danger text-center" role="alert">
                                <%= (String) request.getAttribute("message") %>
                            </div>
                        <% } %>
                        
                        <br>
                        <br>

                        <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
                        <label for="tab-1" class="tab">Login</label>
                        <input id="tab-2" type="radio" name="tab" class="sign-up">
                        <label for="tab-2" class="tab">Sign Up</label>

                        <div class="login-space">
                            <!-- Login Form -->
                            <form class="login-form" method="post" action="Signin_Controller.jsp">
                                <div class="login">
                                    <div class="group">
                                        <label for="user" class="label">Username</label>
                                        <input id="user" type="text" class="input" name="username" placeholder="Enter your username">
                                    </div>
                                    <div class="group">
                                        <label for="pass" class="label">Password</label>
                                        <input id="pass" type="password" class="input" data-type="password" name="password" placeholder="Enter your password">
                                    </div>
                                    <div class="group">
                                        <input type="submit" class="button" value="Sign In">
                                    </div>
                                    <div class="hr"></div>
                                </div>
                            </form>

                            <!-- Sign-Up Form -->
                            <form class="signup-form" method="post" action="Signup_Controller.jsp">
                                <div class="sign-up-form">
                                    <div class="group">
                                        <label for="user" class="label">Username</label>
                                        <input id="user" type="text" class="input" name="username" placeholder="Create your Username">
                                    </div>
                                    <div class="group">
                                        <label for="pass" class="label">Email Address</label>
                                        <input id="pass" type="text" class="input" name="email" placeholder="Enter your email address">
                                    </div>
                                    <div class="group">
                                        <label for="pass" class="label">Password</label>
                                        <input id="pass" type="password" class="input" data-type="password" name="password" placeholder="Create your password">
                                    </div>
                                    <div class="group">
                                        <input type="submit" class="button" value="Sign Up">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>   
            </div>
        </div>
    </div>
</body>
</html>
