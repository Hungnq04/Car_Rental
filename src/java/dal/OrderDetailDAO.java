/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                    + "WHERE vo.OrderID = " + orderID;
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

    public ArrayList<Integer> VehicleIDHaveCheckedDate(String date) {
        ArrayList<Integer> list = new ArrayList<>();
        String sql = "SELECT vo.VehicleID \n"
                + "FROM VehicleOrders vo\n"
                + "JOIN Orders o ON vo.OrderID = o.OrderID\n"
                + "WHERE o.Status = 'Completed' AND o.Date = '"+date+"'";
        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getInt("VehicleID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Integer> VehicleIDHaveCurDate() {
        ArrayList<Integer> list = new ArrayList<>();
        String sql = "SELECT vo.VehicleID "
                + "FROM VehicleOrders vo "
                + "JOIN Orders o ON vo.OrderID = o.OrderID "
                + "WHERE o.Status = 'Completed' AND o.Date = CAST(GETDATE() AS DATE)";

        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getInt("VehicleID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public ArrayList<Integer> VehicleIDByOrderID(int orderID) {
        ArrayList<Integer> list = new ArrayList<>();
        String sql = "SELECT vo.VehicleID "
                + "FROM VehicleOrders vo "
                + "JOIN Orders o ON vo.OrderID = o.OrderID "
                + "WHERE o.OrderID="+orderID;

        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getInt("VehicleID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void addOrderDetail(int vehicleID, String withDriver, int orderID) {
        String sql = "INSERT INTO VehicleOrders (VehicleID, WithDriver, OrderID) VALUES (?, ?, ?)";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, vehicleID);
            if (!withDriver.equalsIgnoreCase("Yes") && !withDriver.equalsIgnoreCase("No")) {
                throw new IllegalArgumentException("withDriver must be 'Yes' or 'No'");
            }
            st.setString(2, withDriver.substring(0, 1).toUpperCase() + withDriver.substring(1).toLowerCase());

            st.setInt(3, orderID);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getWithDriver(int orderID, int vehicleID) {
        String withDriver = null;
        String sql = "SELECT WithDriver FROM VehicleOrders WHERE OrderID = ? AND VehicleID = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, orderID);
            st.setInt(2, vehicleID);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) { // Dùng if thay vì while vì chỉ lấy 1 giá trị
                    withDriver = rs.getString("WithDriver");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // In stack trace để dễ debug
        }
        return withDriver != null ? withDriver : "N/A"; // Tránh trả về null
    }

    public Map<Integer, List<VehicleOrders>> getVehiclesForOrders(Set<Integer> orderIDs) {
        Map<Integer, List<VehicleOrders>> map = new HashMap<>();
        if (orderIDs == null || orderIDs.isEmpty()) {
            return map;
        }

        // Xây dựng câu SQL với tham số
        StringBuilder sql = new StringBuilder(
                "SELECT vo.OrderID, v.Brand, v.Model, v.Seats, vo.WithDriver "
                + "FROM VehicleOrders vo "
                + "JOIN Vehicles v ON vo.VehicleID = v.VehicleID "
                + "WHERE vo.OrderID IN ("
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
