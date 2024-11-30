<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Scheduluxe.*" %>

<%
String username = request.getParameter("username");
String email = request.getParameter("email");
String password = request.getParameter("password");

TravelerService travService = new TravelerService();
try {
    Traveler traveler = travService.createTraveler(username, email, password);
    session.setAttribute("travelerObj", traveler);
    response.sendRedirect("SelectionPage.jsp");

}catch (Exception e){
    request.setAttribute("message", e.getMessage());
%>
    <jsp:forward page="Register.jsp"/>
<%
}
%>