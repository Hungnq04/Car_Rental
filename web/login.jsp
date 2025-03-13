<%-- 
    Document   : login
    Created on : Mar 9, 2025, 10:27:24 AM
    Author     : Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="login" method="post">
            Username<br/>
            <input type="text" name="user"/><br/>
            Password<br/>
            <input type="password" name="pass"/><br/>
            <c:if test="${requestScope.ms!=null}">
                <h2 style="color: red">${ms}</h2>
            </c:if>
            <input type="submit" name="register" value="Register"/><br/>
            <input type="submit" name="login" value="Login"/>
        </form>
    </body>
</html>
