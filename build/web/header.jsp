<%-- 
    Document   : header
    Created on : Mar 9, 2025, 1:16:04 AM
    Author     : chang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <style>
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

                /* Cố định header trên cùng */
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                z-index: 1000; /* Đảm bảo header hiển thị trên các phần khác */
            }
            .nav-bar {
                display: flex;
                align-items: center;
                gap: 20px;
                width: 100%;
            }
            .nav-left {
                margin-right: 560px;
            }
            .nav-right {
                margin-left: 560px;
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

            /* Tạo khoảng trống cho nội dung không bị che */
            .content {
                margin-top: 80px; /* Căn chỉnh theo chiều cao của header */
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
                    <a href="login.jsp">Register</a>
                    <a href="register.jsp">Login</a>
                </div>
            </div>
        </div>
    </body>
</html>
