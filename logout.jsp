<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--<%@ page errorPage="error_ex2_8220134.jsp"%>-->

<%
session.invalidate();
%>

<!DOCTYPE html>
<html lang="en">
	<head>
	
	<%@ include file="header.jsp" %>

	<meta http-equiv="refresh" content="2;url=WelcomePage.jsp" />	
	<title>Logout</title>
	
	</head>
<body>	

	<div class="container theme-showcase" role="main">

		<div class="alert alert-success text-center" role="alert">You’ve logged out. Safe travels and see you soon!</div>		

	</div>
	<!-- /container -->
</body>
</html>
