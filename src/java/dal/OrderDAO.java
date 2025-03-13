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
 * @author chang
 */
public class OrderDAO extends DBContext{
    
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
    
    public void AddOrder(String date, String status, int UserID) {
        try {
            java.sql.Date sqlDate = java.sql.Date.valueOf(date);
            String sql = "insert into Orders (Date,Status,UserID) values (?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, sqlDate);
            st.setString(2, status);
            st.setInt(3, UserID);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
