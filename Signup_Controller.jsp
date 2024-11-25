<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ScheduluxeClasses.*" %>

<%
String username = request.getParameter("username");
String email = request.getParameter("email");
String password = request.getParameter("password");

Traveler trv = new Traveler();
boolean exists;
exists = trv.signupcheck(username,email);

if(!exists){
trv.createTraveler(username,email,password);
}else{
    request.setAttribute("message", "Wrong username or password.");
%>
    <jsp:forward page="SignIn.jsp"/>
<%
}
%>