<%-- 
    Document   : home
    Created on : Mar 9, 2025, 1:15:16 AM
    Author     : Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="css/home.css">
    </head>
    <body>
        <c:if test="${requestScope.listCar==null}">
            <%
                response.sendRedirect("getVehicle");
            %>
        </c:if>
        <div class="content">
            <center>
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
                            <form action="checkAvailableCar" method="post">
                                <button type="submit" class="booking-button">Booking</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </center>
        </div>

    </body>
</html>
