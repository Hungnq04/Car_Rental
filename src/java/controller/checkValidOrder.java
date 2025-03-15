/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.OrderDetailDAO;
import dal.OrderDAO;
import java.util.ArrayList;
import java.util.Date;
import model.Orders;
/**
 *
 * @author Hung
 */
public class checkValidOrder extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet checkValidOrder</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet checkValidOrder at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //processRequest(request, response);
        int orderID = Integer.parseInt("orderID");
        OrderDAO oDAO = new OrderDAO();
        Orders o = oDAO.getOrderByOrderID(orderID);
        OrderDetailDAO odDAO = new OrderDetailDAO();
        ArrayList<Integer> listVehicleID1 = odDAO.VehicleIDHaveCheckedDate(o.getDate().toString());
        ArrayList<Integer> listVehicleID2 = odDAO.VehicleIDByOrderID(orderID);
        for(int id1 : listVehicleID1){
            for(int id2 : listVehicleID2){
                if(id1 == id2){
                    request.setAttribute("ms","The car you want to rent has been rented by someone else!");
                    request.getRequestDispatcher("myOrder").forward(request, response);
                }
                else {
                    request.setAttribute("orderID", orderID);
                    request.getRequestDispatcher("pay").forward(request, response);
                }
            }
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
