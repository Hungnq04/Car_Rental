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
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.Orders;
import model.VehicleOrders;

/**
 *
 * @author Hung
 */
public class OrderDetailDAO extends DBContext {

    public Map<Integer, VehicleOrders> getAllOrderDetailsByOrderID(int orderID) {
        Map<Integer, VehicleOrders> list = new HashMap<>();
        try {
            String sql = "SELECT * \n"
                    + "FROM VehicleOrders vo\n"
                    + "JOIN Orders o ON vo.OrderID = o.OrderID\n"
                    + "WHERE vo.OrderID = "+orderID;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                VehicleOrders vo = new VehicleOrders();
                vo.setVehicleOrderID(rs.getInt("VehicleOrderID"));
                vo.setVehicleID(rs.getInt("VehicleID"));
                vo.setWithDriver(rs.getString("WithDriver"));
                vo.setOrderID(rs.getInt("OrderID"));
                list.put(vo.getVehicleOrderID(), vo);
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
                    + "WHERE o.Status = 'Completed' and o.Date = CAST(GETDATE() AS DATE)";
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

    public String getWithDriver(int orderID, int vehicleID) {
        String withDriver = null;
        try {
            String sql = "select WithDriver from VehicleOrders where OrderID = " + orderID + " and VehicleID = " + vehicleID;
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                withDriver = rs.getString("WithDriver");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return withDriver;
    }
    
    public Map<Integer, List<VehicleOrders>> getVehiclesForOrders(Set<Integer> orderIDs) {
        Map<Integer, List<VehicleOrders>> map = new HashMap<>();
        if (orderIDs == null || orderIDs.isEmpty()) return map;

        // Xây dựng câu SQL với tham số
        StringBuilder sql = new StringBuilder(
            "SELECT vo.OrderID, v.Brand, v.Model, v.Seats, vo.WithDriver " +
            "FROM VehicleOrders vo " +
            "JOIN Vehicles v ON vo.VehicleID = v.VehicleID " +
            "WHERE vo.OrderID IN ("
        );

        // Tạo danh sách dấu hỏi chấm `?` theo số lượng orderID
        sql.append("?,".repeat(orderIDs.size()));
        sql.setLength(sql.length() - 1); // Xóa dấu `,` cuối cùng
        sql.append(")");

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            int index = 1;
            for (int orderID : orderIDs) {
                ps.setInt(index++, orderID);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    VehicleOrders vo = new VehicleOrders();
                    vo.setBrand(rs.getString("Brand"));
                    vo.setModel(rs.getString("Model"));
                    vo.setSeats(rs.getInt("Seats"));
                    vo.setWithDriver(rs.getString("WithDriver"));

                    map.computeIfAbsent(orderID, k -> new ArrayList<>()).add(vo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Nên ghi log thay vì in ra console trong môi trường thực tế
        }
        return map;
    }
}
