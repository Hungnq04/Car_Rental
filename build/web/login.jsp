<%-- 
    Document   : login
    Created on : Mar 9, 2025, 10:27:24 AM
    Author     : chang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="login">
            Username<br/>
            <input type="text" name="user"/>
            Password<br/>
            <input type="password" name="pass"/>
            <input type="submit" value="Login"/>
        </form>
    </body>
</html>
