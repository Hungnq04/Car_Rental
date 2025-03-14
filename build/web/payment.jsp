<%-- 
    Document   : paid
    Created on : Mar 14, 2025, 9:59:57 PM
    Author     : Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Page</title>
        <link rel="stylesheet" href="css/payment.css"/>
    </head>
    <body>
        <h2 class="total-amount">Total amount payable: ${requestScope.totalAmount}</h2>
        <form action="payment" method="post">
            <input type="hidden" name="orderID" value="${requestScope.OrderID}"/>
            <input type="submit" value="Pay" class="submit-btn"/>
        </form>
        <h3 class="message">${requestScope.ms}</h3>
        
    </body>
</html>
