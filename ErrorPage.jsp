<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Σφάλμα</title>
</head>
<body>
    <h1>Προέκυψε σφάλμα!</h1>
    <p>
        <% 
            String errorMessage = (String) request.getAttribute("error"); 
            if (errorMessage != null) {
                out.print(errorMessage); 
            } else {
                out.print("Δεν υπάρχει διαθέσιμο μήνυμα σφάλματος.");
            }
        %>
    </p>
</body>
</html>



