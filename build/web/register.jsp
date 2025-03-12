<%-- 
    Document   : register
    Created on : Mar 9, 2025, 9:42:27 AM
    Author     : chang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Register</h1>
        <form action="addAccount">
            Phone<br/>
            <input type="text" name="phone"/><br/>
            Username<br/>
            <input type="text" name="username"/><br/>
            Password<br/>
            <input type="text" name="password"/><br/>
            Email<br/>
            <input type="text" name="email"/><br/>
            <input type="hidden" name="roleId" value="1"/>
            <input type="submit" value="Register"/>
        </form>

    </body>
</html>
