<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
session.invalidate();
%>

<!DOCTYPE html>
<html lang="en">
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Scheduluxe</title>
    <link rel="icon" href="<%=request.getContextPath()%>/images/logo.png" type="image/x-icon">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/logoutt.css">  

	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">	
	 
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">


	<meta http-equiv="refresh" content="2;url=Home.jsp" />	
	<title>Logout</title>
	</head>
<body>	

	<div class="center-screen-container">
        <div class="image-container">
            <img 
                src="<%=request.getContextPath()%>/images/parthenon.jpg" 
                alt="Logout photo" 
                class="responsive-image">
            <div class="overlay-text">
                Logged out. See you on your next adventure with Scheduluxe!
            </div>
        </div>
    </div>
</body>
</html>

