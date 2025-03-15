/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function doPayment(orderID) {
    window.location = "pay?orderID=" + orderID;
}
function doDelete(orderID) {
    if (confirm("Sure to delete this Order?")) {
        window.location = "deleteOrder?orderID=" + orderID;
    }
}