<%-- 
    Document   : myOrder
    Created on : Mar 14, 2025, 11:21:44 PM
    Author     : Hung
--%>

<%@page import="java.util.ArrayList, java.util.List, java.util.HashMap, java.util.Map" %>
<%@page import="dal.OrderDAO, dal.OrderDetailDAO, dal.VehicleDAO" %>
<%@page import="model.Orders, model.Vehicles, model.VehicleOrders, model.Accounts" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="js/javascript.js"></script>
        <title>My Orders</title>
        <link rel="stylesheet" href="css/myOrder.css">
    </head>
    <body>
        <h2>My Orders</h2>
        <table>
            <tr>
                <th>Order ID</th>
                <th>Date</th>
                <th>Vehicles</th>
                <th>With Driver</th>
                <th>Payment</th>
            </tr>

            <%
                Map<Integer, Orders> listOrders = (Map<Integer, Orders>) request.getAttribute("listOrders");
                Map<Integer, List<VehicleOrders>> vehicleOrdersMap = (Map<Integer, List<VehicleOrders>>) request.getAttribute("vehicleOrdersMap");

                for (Integer orderID : listOrders.keySet()) {
                    Orders order = listOrders.get(orderID);
                    List<VehicleOrders> vehicles = vehicleOrdersMap.getOrDefault(orderID, new ArrayList<>());

                    boolean firstRow = true;
                    for (VehicleOrders vo : vehicles) {
            %>
            <tr>
                <% if (firstRow) { %>
                <td rowspan="<%= vehicles.size() %>"><%= order.getOrderID() %></td>
                <td rowspan="<%= vehicles.size() %>"><%= order.getDate() %></td>
                <% } %>

                <td><%= vo.getBrand() %> <%= vo.getModel() %> (<%= vo.getSeats() %> seats)</td>
                <td><%= vo.getWithDriver() %></td>

                <% if (firstRow) { %>
                <% if (order.getOrderStatus().equals("Completed")) { %>
                <td rowspan="<%= vehicles.size() %>">Paid</td>
                <% } else { %>
                <td rowspan="<%= vehicles.size() %>">
                    <a href="#" onclick="doPayment('<%= order.getOrderID() %>')">
                        <input type="button" value="Pay"/>
                    </a>
                    <a href="#" onclick="doDelete('<%= order.getOrderID() %>')">
                        <input type="button" value="Delete"/>
                    </a>
                </td>
                <% } %>
                <% } %>
            </tr>
            <%
                        firstRow = false;
                    }
                }
            %>
        </table>

    </body>
</html>
