/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

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
}
