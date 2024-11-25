<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ScheduluxeClasses.*" %>


<%
String username = request.getParameter("username");
String password = request.getParameter("password");

Traveler trv = new Traveler();
    <!---->
Traveler trvexists = trv.getTraveler(username,password);
if(trvexists != null) {
    session.setAttribute("travelerObj", trvexists);
    response.sendRedirect("SelectionPage.jsp");
}else{
    request.setAttribute("message", "Wrong username or password, please try again.");
%>
    <jsp:forward page="SignIn.jsp"/>
<%
}
%>