<%-- 
    Document   : ViewMyAccount
    Created on : Mar 13, 2025, 10:25:35 PM
    Author     : Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View My Account</title>
        <link rel="stylesheet" href="css/viewAccount.css">
    </head>
    <body>
        <div class="container">
            <h2>My Account</h2>
            <form action="updateAccount" method="post">
                <table>
                    <tr>
                        <td><label>Username:</label></td>
                        <td><input type="text" name="user" value="${sessionScope.account.username}" readonly/></td>
                    </tr>
                    <tr>
                        <td><label>Password:</label></td>
                        <td><input type="password" name="pass" placeholder="••••••••"/></td>
                    </tr>
                    <tr>
                        <td><label>Phone:</label></td>
                        <td><input type="text" name="phone" value="${sessionScope.account.phone}" /></td>
                    </tr>
                    <tr>
                        <td><label>Email:</label></td>
                        <td><input type="text" name="email" value="${sessionScope.account.email}" /></td>
                    </tr>
                </table>
                <input type="submit" value="Update" class="btn"/>
            </form>
        </div>
    </body>
</html>

