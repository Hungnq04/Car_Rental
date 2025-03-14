/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import model.Orders;

/**
 *
 * @author chang
 */
public class OrderDetailDAO extends DBContext {

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

    public ArrayList<Integer> VehicleIDHaveCHeckedDate(String date) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            java.sql.Date sqlDate = java.sql.Date.valueOf(date);
            String sql = "SELECT vo.VehicleID \n"
                    + "FROM VehicleOrders vo\n"
                    + "JOIN Orders o ON vo.OrderID = o.OrderID\n"
                    + "WHERE o.Status = 'Completed' and o.Date = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, sqlDate);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int VehicleID = rs.getInt("VehicleID");
                list.add(VehicleID);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public ArrayList<Integer> VehicleIDHaveCurDate() {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            String sql = "SELECT vo.VehicleID \n"
                    + "FROM VehicleOrders vo\n"
                    + "JOIN Orders o ON vo.OrderID = o.OrderID\n"
                    + "WHERE o.Date = CAST(GETDATE() AS DATE)";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int VehicleID = rs.getInt("VehicleID");
                list.add(VehicleID);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public void AddOrderDetail(int vehicleID, String withDriver, int orderID) {
        try {
            String sql = "insert into VehicleOrders (VehicleID,WithDriver,OrderID) values (?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, vehicleID);
            st.setString(2, withDriver);
            st.setInt(3, orderID);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
