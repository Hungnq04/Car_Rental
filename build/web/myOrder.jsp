<%-- 
    Document   : myOrder
    Created on : Mar 14, 2025, 11:21:44 PM
    Author     : Hung
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="javax.faces.bean.SessionScoped"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dal.OrderDAO" %>
<%@page import="dal.OrderDetailDAO" %>
<%@page import="dal.VehicleDAO" %>
<%@page import="model.Orders" %>
<%@page import="model.Vehicles" %>
<%@page import="model.VehicleOrders" %>
<%@page import="java.util.HashMap;" %>
<%@page import="java.util.Map;" %>
<%@page import="model.Accounts" %>
<%@include file="js/payment.js" %>
<!DOCTYPE html>
<html>
    <head>
        <title>My Orders</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid black;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
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
                <% if (firstRow) {%>
                <td rowspan="<%= vehicles.size()%>"><%= order.getOrderID()%></td>
                <td rowspan="<%= vehicles.size()%>"><%= order.getDate()%></td>
                <% }%>
                <td><%= vo.getBrand()%> <%= vo.getModel()%> (<%= vo.getSeats()%> seats)</td>
                <td><%= vo.getWithDriver()%></td>
                <%  if (firstRow) {
                        if (order.getOrderStatus().equals("Completed"))
                %>
                <td rowspan="<%= vehicles.size()%>">Paid</td>
                <%
                        else
                %>
                <td rowspan="<%= vehicles.size()%>"><a href="#" onclick="doPayment('<%=order.getOrderID()%>')"><input type="button" value="Pay"/></a></td>
                <%
                    }
                %>
            </tr>
            <%
                        firstRow = false;
                    }
                }
            %>
        </table>
    </body>
</html>
