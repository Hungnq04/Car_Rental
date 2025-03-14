/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import model.Orders;

/**
 *
 * @author Hung
 */
public class OrderDAO extends DBContext {

    public Map<Integer, Orders> getAllOrders() {
        Map<Integer, Orders> list = new HashMap<>();
        try {
            String sql = "select * from Orders";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Orders order = new Orders();
                order.setOrderID(rs.getInt("OrderID"));
                order.setDate(rs.getDate("Date"));
                order.setOrderStatus(rs.getString("Status"));
                order.setUserID(rs.getInt("UserID"));
                list.put(order.getOrderID(), order);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public int AddOrder(String date, String status, int UserID) {
        int OrderID = -1;
        try {
            java.sql.Date sqlDate = java.sql.Date.valueOf(date);
            String sql = "insert into Orders (Date,Status,UserID) values (?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, sqlDate);
            st.setString(2, status);
            st.setInt(3, UserID);
            st.executeUpdate();
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    OrderID = rs.getInt(1);
                }
            }
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return OrderID;
    }

    public void changeStatus(int orderID) {
        String status = "Completed";
        try {
            String sql = "UPDATE Orders SET Status = ? WHERE OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(2, orderID);
            st.setString(1, status);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Map<Integer, Orders> getAllMyOrders(int userID) {
        Map<Integer, Orders> list = new HashMap<>();
        try {
            String sql = "SELECT * FROM Orders WHERE UserID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Orders order = new Orders();
                order.setOrderID(rs.getInt("OrderID"));
                order.setDate(rs.getDate("Date"));
                order.setOrderStatus(rs.getString("Status"));
                list.put(order.getOrderID(), order);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
