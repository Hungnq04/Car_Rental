<%-- 
    Document   : home
    Created on : Mar 9, 2025, 1:15:16 AM
    Author     : chang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <style>
            .car-container {
                display: flex;
                justify-content: center;
                align-items: center;
                width: 100%;
                margin-bottom: 20px;
            }
            .car-image, .car-info {
                width: 50%;
                padding: 10px;
                box-sizing: border-box;
            }
            .car-image img {
                width: 100%;
                height: auto;
                display: block;
                border-radius: 10px;
            }
            .car-info {
                display: flex;
                flex-direction: column;
                justify-content: center;
                background-color: #f9f9f9;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
                text-align: center;
            }
            .car-info table {
                width: 100%;
            }
            .car-info td {
                padding: 10px;
            }
            .booking-button {
                margin-top: 15px;
                padding: 10px 20px;
                background-color: #007BFF;
                color: white;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            .booking-button:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="content">
            <center>
                <c:forEach var="entry" items="${requestScope.listCar}">
                    <c:set var="car" value="${entry.value}"/>
                    <div class="car-container">
                        <!-- Phần hình ảnh xe -->
                        <div class="car-image">
                            <img src="${car.imageLink}" alt="Car Image"/>
                        </div>
                        <!-- Phần thông tin xe -->
                        <div class="car-info">
                            <table>
                                <tr>
                                    <td><strong>Brand:</strong></td>
                                    <td>${car.brand}</td>
                                </tr>
                                <tr>
                                    <td><strong>Model:</strong></td>
                                    <td>${car.model}</td>
                                </tr>
                                <tr>
                                    <td><strong>Seats:</strong></td>
                                    <td>${car.seats}</td>
                                </tr>
                                <tr>
                                    <td><strong>Price:</strong></td>
                                    <td>${car.price} VND / Day</td>
                                </tr>
                            </table>
                            <!-- Nút Booking -->
                            <form action="checkAvailableCar" method="post">
                                <input type="hidden" name="vehicleId" value="${entry.key}"/>
                                <button type="submit" class="booking-button">Booking</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </center>
        </div>

    </body>
</html>
