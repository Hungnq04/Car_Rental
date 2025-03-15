<%-- 
    Document   : paid
    Created on : Mar 14, 2025, 9:59:57 PM
    Author     : Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Page</title>
        <link rel="stylesheet" href="css/payment.css"/>
    </head>
    <body>
        <div class="container">
            <h2 class="total-amount">Total amount payable: ${requestScope.totalAmount}</h2>
            <form action="pay" method="post">
                <input type="hidden" name="orderID" value="${requestScope.OrderID}"/>
                <input type="hidden" name="totalAmount" value="${requestScope.totalAmount}"/>
                <c:if test="${empty requestScope.ms}">
                    <input type="submit" value="Pay" class="submit-btn"/>
                </c:if>          
            </form>
            <h3 class="message">${requestScope.ms}</h3>
        </div>
    </body>
</html>
