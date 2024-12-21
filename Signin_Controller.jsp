<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Scheduluxe.*" %>
<%@ page errorPage="ErrorPage.jsp" %>

<%
String username = request.getParameter("username");
String password = request.getParameter("password");
String targetPage = request.getParameter("targetPage");

TravelerService travService = new TravelerService();
try {
    Traveler traveler = travService.getTraveler(username,password);
    session.setAttribute("travelerObj", traveler);
    
    if (targetPage != null) {
        session.removeAttribute("targetPage"); 
        response.sendRedirect(targetPage);
    } else {
        response.sendRedirect("Selection.jsp");
    }   
    

} catch (Exception e) {
    request.setAttribute("message", e.getMessage());       
    //normally it will show "Wrong username or password, please try again!"
  
%>
    <jsp:forward page="Connect.jsp"/>
<%
}
%>