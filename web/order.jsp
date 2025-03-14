<%-- 
    Document   : Order
    Created on : Mar 10, 2025, 11:41:08 PM
    Author     : Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
        <link rel="stylesheet" href="css/order.css">
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
            <form action="addOrder">
                <div class="car-details">
                    <table class="car-table">
                        <tr>
                            <th>Brand</th>
                            <th>Model</th>
                            <th>Seats</th>
                            <th>Price</th>
                            <th>With Driver</th>
                        </tr>
                        <c:forEach var="car" items="${sessionScope.listBookedCar}">
                            <tr>
                                <td>${car.brance}</td>
                                <td>${car.model}</td>
                                <td>${car.seats}</td>
                                <td>${car.price}</td>
                                <td>
                                    <select name="withDriver">
                                        <option value="No">No</option>
                                        <option value="Yes">Yes</option>
                                    </select>
                                </td>
                            </tr>
                            <input type="hidden" name="vehicleId" value="${car.vehicleID}">
                        </c:forEach>
                    </table>
                </div>
                <a class="total-amount">Total amount payable: ${requestScope.totalAmount}</a>
                <input type="hidden" name="date" value="${requestScope.date}"/>
                <input type="hidden" name="userID" value="${sessionScope.account.getUserID()}"/>
                <input type="submit" value="Confirm"/>
            </form>
        </c:if>
    </center>
</body>
</html>
