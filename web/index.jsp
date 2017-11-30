<%-- 
    Document   : index
    Created on : Nov 29, 2017, 8:11:26 PM
    Author     : Yong Keong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            response.sendRedirect(request.getContextPath() + "/login");
        %>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
