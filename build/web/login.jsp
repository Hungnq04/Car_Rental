<%-- 
    Document   : login
    Created on : Mar 9, 2025, 10:27:24 AM
    Author     : Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
        <div class="container">
            <h1>Login</h1>
            <form action="login" method="post">
                <label>Username</label>
                <input type="text" name="user" value="${requestScope.user}" required/>

                <label>Password</label>
                <input type="password" name="pass" required/>

                <c:if test="${requestScope.ms != null}">
                    <p class="error-message">${requestScope.ms}</p>
                </c:if>

                <input type="submit" name="login" value="Login"/>
                <input type="submit" name="register" value="Register"/>
            </form>
        </div>
    </body>
</html>
