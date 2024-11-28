<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Scheduluxe.*" %>


<%
String username = request.getParameter("username");
String password = request.getParameter("password");

TravelerService travService = new TravelerService();
try {
    Traveler traveler = travService.getTraveler(username,password);
    session.setAttribute("travelerObj", traveler);
    response.sendRedirect("SelectionPage.jsp");
    
} catch (Exception e) {
    request.setAttribute("message", e.getMessage());       
    //normally it will show "Wrong username or password, please try again!"
  
%>
    <jsp:forward page="Register.jsp"/>
<%
}
%>