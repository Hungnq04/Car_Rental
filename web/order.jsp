<%-- 
    Document   : Order
    Created on : Mar 10, 2025, 11:41:08 PM
    Author     : Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<%@include file="js/javascript.js" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
        <link rel="stylesheet" href="css/order.css">
    </head>
    <body>

        <form action="checkAvailableCar" method="get">
            <div class="date-container">
                <label for="date">Select day:</label>
                <input type="date" id="date" name="date" value="${sessionScope.date}" onfocus="this.value='';"/>
                <input type="submit" value="Confirm"/>
            </div>
        </form>

    <center>
        <div class="car-list">
            <c:forEach var="entry" items="${requestScope.listCar}">
                <c:set var="car" value="${entry.value}"/>
                <div class="car-container">

                    <div class="car-image">
                        <img src="${car.imageLink}" alt="Car Image"/>
                    </div>

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

                        <c:set var="isFullyRented" value="false"/>
                        <c:set var="isBooked" value="false"/>

                        <c:forEach var="id" items="${sessionScope.VehicleIDHaveCheckedDate}">
                            <c:if test="${id.toString() eq String.valueOf(car.getVehicleID())}">
                                <c:set var="isFullyRented" value="true"/>
                            </c:if>
                        </c:forEach>

                        <c:forEach var="bookedCar" items="${sessionScope.listBookedCar}">
                            <c:if test="${bookedCar.vehicleID eq car.vehicleID}">
                                <c:set var="isBooked" value="true"/>
                            </c:if>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${isFullyRented}">
                                <div class="fully-rented"><a>Fully Rented</a></div>
                            </c:when>
                            <c:when test="${isBooked}">
                                <form action="orderTmp" method="get">
                                    <input type="hidden" name="date" value="${sessionScope.date}"/>
                                    <input type="hidden" name="vehicleId" value="${car.getVehicleID()}"/>
                                    <button type="submit" class="booked-button">Booked</button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="orderTmp" method="post">
                                    <input type="hidden" name="date" value="${sessionScope.date}"/>
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
                            <th>Date</th>
                            <th>Brand</th>
                            <th>Model</th>
                            <th>Seats</th>
                            <th>Price</th>
                            <th>With Driver</th>
                        </tr>
                        <c:forEach var="car" items="${sessionScope.listBookedCar}">
                            <tr>
                                <td>${sessionScope.date}</td>
                                <td>${car.brand}</td>
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
                <input type="hidden" name="date" value="${sessionScope.date}"/>
                <input class="" type="submit" value="Confirm"/>
            </form>
        </c:if>
    </center>
</body>
</html>
