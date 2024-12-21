<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
if (session.getAttribute("travelerObj") == null) { 
    session.setAttribute("targetPage", request.getRequestURI());
    request.setAttribute("message", "You are not authorized to access this resource. Please login!");

%>
    <jsp:forward page="Connect.jsp" />

<%
    return;
}    
%>