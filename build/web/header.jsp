<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: Arial, sans-serif;
            }

            .header {
                background-color: lightskyblue;
                color: white;
                padding: 15px 20px;
                display: flex;
                align-items: center;
                justify-content: space-between;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
                font-size: 24px;
                font-weight: bold;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                z-index: 1000;
            }

            .nav-bar {
                display: flex;
                align-items: center;
                justify-content: space-between;
                width: 100%;
                max-width: 1200px;
                margin: 0 auto;
            }

            .nav-left {
                flex-grow: 1;
            }

            .nav-right {
                display: flex;
                gap: 15px;
            }

            .nav-bar a {
                color: white;
                text-decoration: none;
                font-size: 18px;
                padding: 10px 15px;
                border-radius: 5px;
                transition: background-color 0.3s;
                font-size: 22px;
            }

            .nav-bar a:hover {
                background-color: rgba(255, 255, 255, 0.2);
            }

            /* Tạo khoảng trống để không bị che */
            .content {
                margin-top: 80px;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <div class="nav-bar">
                <!-- Home bên trái -->
                <div class="nav-left">
                    <a href="home.jsp">Rental Car</a>
                </div>
                <!-- Login & Register bên phải -->
                <div class="nav-right">
                    <c:choose>
                        <c:when test="${sessionScope.account == null}">
                            <a href="register.jsp">Register</a>
                            <a href="login.jsp">Login</a>
                        </c:when>
                        <c:otherwise>
                            <a href="myOrder">My Order</a>
                            <a href="viewMyAccount.jsp">My Account</a>
                            <a href="logout">Logout</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </body>
</html>
