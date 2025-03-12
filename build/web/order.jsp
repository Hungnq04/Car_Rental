<%-- 
    Document   : Order
    Created on : Mar 10, 2025, 11:41:08 PM
    Author     : chang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
        <style>
            .car-list {
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); /* Hiển thị 2-3 xe/hàng */
                gap: 20px;
                justify-content: center;
                padding: 20px;
            }

            /* Hộp chứa mỗi xe */
            .car-container {
                display: flex;
                flex-direction: column;
                align-items: center;
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
                padding: 15px;
                max-width: 350px; /* Giới hạn chiều rộng */
                text-align: center;
            }

            /* Hình ảnh xe */
            .car-image img {
                width: 100%;
                height: 180px; /* Cố định chiều cao */
                object-fit: cover;
                border-radius: 10px;
            }

            /* Phần thông tin xe */
            .car-info {
                width: 100%;
                background-color: #f9f9f9;
                padding: 15px;
                border-radius: 10px;
                box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
            }

            /* Bảng thông tin */
            .car-info table {
                width: 100%;
            }

            .car-info td {
                padding: 5px;
            }

            /* Nút booking */
            .booking-button {
                margin-top: 10px;
                padding: 8px 15px;
                background-color: #007BFF;
                color: white;
                border: none;
                border-radius: 5px;
                font-size: 14px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .booking-button:hover {
                background-color: #0056b3;
            }

            /* Nút Fully Rented */
            .fully-rented {
                margin-top: 10px;
                padding: 8px;
                background-color: #ff4d4d;
                color: white;
                border-radius: 5px;
                font-size: 14px;
            }
            .date-container {
                display: flex;
                align-items: center;
                justify-content: center;
                gap: 15px; /* Khoảng cách giữa các phần tử */
                padding: 20px;
                background: #f8f9fa;
                border-radius: 10px;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
                width: fit-content;
                margin: 20px auto;
            }

            /* Label: Chữ đẹp hơn */
            .date-container label {
                font-size: 18px;
                font-weight: bold;
                color: #333;
            }

            /* Ô chọn ngày */
            .date-container input[type="date"] {
                padding: 10px;
                font-size: 16px;
                border: 2px solid #007bff;
                border-radius: 8px;
                outline: none;
                transition: all 0.3s ease-in-out;
                background-color: #fff;
                color: #333;
                cursor: pointer;
            }

            .date-container input[type="date"]:hover {
                border-color: #0056b3;
                background-color: #e9ecef;
            }

            .date-container input[type="date"]:focus {
                border-color: #ff5722;
                background-color: #fff;
                box-shadow: 0 0 5px rgba(255, 87, 34, 0.5);
            }

            /* Nút xác nhận */
            .date-container input[type="submit"] {
                padding: 10px 15px;
                font-size: 16px;
                font-weight: bold;
                color: #fff;
                background-color: #28a745;
                border: none;
                border-radius: 8px;
                cursor: pointer;
                transition: all 0.3s ease-in-out;
            }

            .date-container input[type="submit"]:hover {
                background-color: #218838;
                box-shadow: 0px 3px 6px rgba(0, 128, 0, 0.3);
            }

            .date-container input[type="submit"]:active {
                background-color: #1e7e34;
                transform: scale(0.98);
            }
            .booked {
                margin-top: 10px;
                padding: 8px;
                background-color: #ff4d4d;
                color: white;
                border-radius: 5px;
                font-size: 14px;
            }
            .car-details {
                width: 80%;
                background: #fff;
                border-radius: 10px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
                padding: 15px;
                text-align: center;
            }

            /* Thiết lập bảng */
            .car-table {
                width: 100%;
                border-collapse: collapse;
                font-size: 16px;
            }

            /* Header bảng */
            .car-table th {
                background-color: #007bff;
                color: white;
                padding: 10px;
                text-align: left;
                border-bottom: 2px solid #ddd;
            }

            /* Dòng dữ liệu */
            .car-table td {
                padding: 10px;
                border-bottom: 1px solid #ddd;
            }

            /* Hiệu ứng hover */
            .car-table tr:hover {
                background-color: #f1f1f1;
            }

            /* Viền bảng */
            .car-table {
                border: 2px solid #007bff;
                border-radius: 8px;
                overflow: hidden;
            }

        </style>
    </head>
    <body>

        <form action="checkAvailableCar">
            <div class="date-container">
                <label for="date">Select day:</label>
                <input type="date" id="date" name="date" value="${requestScope.date}"/>
                <input type="submit" value="Confirm"/>
            </div>
        </form>

    <center>
        <div class="car-list">
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
                        <c:set var="isFullyRented" value="false"/>
                        <c:set var="isBooked" value="false"/>
                        <c:forEach var="id" items="${requestScope.VehicleIDHaveCHeckedDate}">
                            <c:if test="${id eq car.VehicleID}">
                                <c:set var="isFullyRented" value="true"/>
                            </c:if>
                        </c:forEach>
                        <c:forEach var="bookedCar" items="${sessionScope.listBookedCar}">
                            <c:if test="${bookedCar.getVehicleID() eq car.getVehicleID()}">
                                <c:set var="isBooked" value="true"/>
                            </c:if>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${isFullyRented}">
                                <div class="fully-rented"><a>Fully Rented</a></div>
                            </c:when>
                            <c:when test="${isBooked}">
                                <div class="booked"><a>Booked</a></div>
                            </c:when>
                            <c:otherwise>
                                <form action="orderTmp">
                                    <input type="hidden" name="date" value="${requestScope.date}"/>
                                    <input type="hidden" name="vehicleId" value="${car.getVehicleID()}"/>
                                    <button type="submit" class="booking-button">Booking</button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>
        </div>
        <c:if test="${sessionScope.listBookedCar!=null}">
            <div class="car-details">
                <table class="car-table">
                    <tr>
                        <th>Brand</th>
                        <th>Model</th>
                        <th>Seats</th>
                        <th>Price</th>
                    </tr>
                    <c:forEach var="car" items="${sessionScope.listBookedCar}">
                        <tr>
                            <td>${car.getBrand()}</td>
                            <td>${car.getModel()}</td>
                            <td>${car.getSeats()}</td>
                            <td>${car.getPrice()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>
    </center>
</body>
</html>
