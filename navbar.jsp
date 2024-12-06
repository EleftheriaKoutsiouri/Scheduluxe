<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Responsive Navbar</title>
  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css2?family=Platypi&family=Poppins:wght@400;600&display=swap" rel="stylesheet">
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    /* Custom Fonts */
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
  </style>
</head>
<body>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <!-- Logo Section -->
      <a class="navbar-brand" href="#">
        <img src="images/logo.png" alt="Logo">
        Scheduluxe
      </a>
      <!-- Burger Button -->
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <!-- Navigation Links -->
      <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">About</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Get Started</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

