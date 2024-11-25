<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ScheduluxeClasses.*" %>

<%
String username = request.getParameter("username");
String email = request.getParameter("email");
String password = request.getParameter("password");

Traveler trv = new Traveler();
trv.signupcheck(username,email)
trv.createTraveler(username,email,password)

%>