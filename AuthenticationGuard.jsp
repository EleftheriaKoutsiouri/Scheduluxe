<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays, java.util.List" %>


<%
if (session.getAttribute("travelerObj") == null) {

    List<String> redirectPages = Arrays.asList("/ismgroup38/Scheduluxe/Selection.jsp", "/ismgroup38/Scheduluxe/EditProfile.jsp");
    
    if (redirectPages.contains(request.getRequestURI())) {
        session.setAttribute("targetPage", request.getRequestURI());
    }

    request.setAttribute("message", "You are not authorized to access this resource. Please login!");

%>
    <jsp:forward page="Connect.jsp" />

<%
    return;
}    
%>