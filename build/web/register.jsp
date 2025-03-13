<%-- 
    Document   : register
    Created on : Mar 9, 2025, 9:42:27 AM
    Author     : Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <link rel="stylesheet" href="css/register.css">
    </head>
    <body>
        <div class="container">
            <h1>Register</h1>
            <form action="addAccount" method="post">
                <label>Phone</label>
                <input type="text" name="phone" required/><br/>

                <label>Username</label>
                <input type="text" name="username" required/><br/>

                <label>Password</label>
                <input type="password" name="password" required/><br/>

                <label>Email</label>
                <input type="text" name="email" required/><br/>

                <input type="hidden" name="roleId" value="1"/>
                <input type="submit" value="Register"/>
            </form>
        </div>
    </body>
</html>
