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
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content="Lab exercises 2024-2025">
	<meta name="author" content="t8220134@aueb.gr">
	<link rel="icon" href="<%=request.getContextPath()%>/images/favicon.ico">

	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">	
	<!-- Bootstrap Optional theme -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
	<!-- Custom styles for this template -->
	<link href="<%=request.getContextPath()%>/css/theme_8220134.css" rel="stylesheet">
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->

	<meta http-equiv="refresh" content="10;url=WelcomePage.jsp" />	
	<title>Logout</title>
	
	</head>
<body>	

	<div class="container theme-showcase" role="main">
        
		<div class="alert alert-success text-center" role="alert">You’ve logged out. Safe travels and see you soon!</div>		

	</div>
</body>
</html>

