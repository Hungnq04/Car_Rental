/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OrderDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.VehicleDAO;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Vehicles;

/**
 *
 * @author chang
 */
public class OrderTmp extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            out.println("<title>Servlet OrderTMP</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderTMP at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        int vehicleID = Integer.parseInt(request.getParameter("vehicleId"));
        VehicleDAO vDAO = new VehicleDAO();
        Vehicles car = vDAO.getVehicleById(vehicleID);

        HttpSession session = request.getSession();
        ArrayList<Vehicles> listBookedCar = (ArrayList<Vehicles>) session.getAttribute("listBookedCar");

        if (listBookedCar == null) {
            listBookedCar = new ArrayList<>();
            session.setAttribute("listBookedCar", listBookedCar);
        }
        listBookedCar.add(car);
        session.setAttribute("listBookedCar", listBookedCar);

        String date = request.getParameter("date");
        OrderDetailDAO odDAO = new OrderDetailDAO();
        ArrayList<Integer> VehicleIDHaveCHeckedDate = odDAO.VehicleIDHaveCHeckedDate(date);
        request.setAttribute("VehicleIDHaveCHeckedDate", VehicleIDHaveCHeckedDate);
        request.setAttribute("date", date);

        VehicleDAO cDAO = new VehicleDAO();
        Map<Integer, Vehicles> listCar = cDAO.getAllVehicles();
        request.setAttribute("listCar", listCar);
        request.getRequestDispatcher("order.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
