<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="ErrorPage.jsp" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <title>Sign In / Sign Up</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Register.css">
    <style>
        /* Centering container */
        .auth-container {
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            background-color: #f8f9fa;
            padding: 20px;
        }
        .auth-card {
            width: 100%;
            max-width: 400px;
            padding: 20px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .btn-primary {
            background-color: #c8349c;
            border: none;
            transition: background-color 0.3s;
        }
        .btn-primary:hover {
            background-color: #a52b7c;
        }
        a {
            color: #c8349c;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand d-flex align-items-center" href="WelcomePage.jsp">
                    <img src="<%=request.getContextPath()%>/images/logo.png" alt="Scheduluxe Logo" width="40" height="40">
                    <span class="ms-2">Scheduluxe</span>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="navbarCollapse">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="WelcomePage.jsp">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="About.jsp">About Us</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>

    <main>
        <div class="auth-container">
            <div class="auth-card">
                <% if(request.getAttribute("message") != null) { %>		
                    <div class="alert alert-danger text-center" role="alert"><%=(String)request.getAttribute("message") %></div>
                <% } %>
                <div class="sign-in-form active">
                    <h2 class="text-center">Sign In</h2>
                    <form method="post" action="Signin_Controller.jsp">
                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Sign In</button>
                        <div class="text-center mt-3">
                            <span>Don't have an account? </span>
                            <a href="javascript:void(0);" onclick="toggleForms('sign-up')">Sign up now</a>
                        </div>
                    </form>
                </div>

                <div class="sign-up-form" style="display: none;">
                    <h2 class="text-center">Sign Up</h2>
                    <form method="post" action="Signup_Controller.jsp">
                        <div class="mb-3">
                            <label for="signUpUsername" class="form-label">Username</label>
                            <input type="text" class="form-control" id="signUpUsername" name="username" required>
                        </div>
                        <div class="mb-3">
                            <label for="signUpEmail" class="form-label">Email address</label>
                            <input type="email" class="form-control" id="signUpEmail" name="email" required>
                        </div>
                        <div class="mb-3">
                            <label for="signUpPassword" class="form-label">Password</label>
                            <input type="password" class="form-control" id="signUpPassword" name="password" required>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Sign Up</button>
                        <div class="text-center mt-3">
                            <span>Already have an account? </span>
                            <a href="javascript:void(0);" onclick="toggleForms('sign-in')">Sign in now</a>
                        </div>
                    </form>
                </div>
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
</body>
</html>
