package dal;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Orders;

public class OrderDAO extends DBContext {

    public Map<Integer, Orders> getAllOrders() {
        Map<Integer, Orders> list = new HashMap<>();
        String sql = "SELECT * FROM Orders";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Orders order = new Orders();
                order.setOrderID(rs.getInt("OrderID"));
                order.setDate(rs.getDate("Date"));
                order.setOrderStatus(rs.getString("Status"));
                order.setUserID(rs.getInt("UserID"));
                list.put(order.getOrderID(), order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int AddOrder(String date, String status, int userID) {
        int orderID = -1;
        String sql = "INSERT INTO Orders (Date, Status, UserID) VALUES (?, ?, ?)";

        try (PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            java.sql.Date sqlDate = convertToSQLDate(date);
            if (sqlDate == null) {
                System.err.println("Invalid date format: " + date);
                return -1;
            }

            st.setDate(1, sqlDate);
            st.setString(2, status);
            st.setInt(3, userID);
            st.executeUpdate();

            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    orderID = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderID;
    }

    public void changeStatus(int orderID) {
        String status = "Completed";
        String sql = "UPDATE Orders SET Status = ? WHERE OrderID = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, status);
            st.setInt(2, orderID);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Orders> getAllMyOrders(int userID) {
        Map<Integer, Orders> list = new HashMap<>();
        String sql = "SELECT * FROM Orders WHERE UserID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Orders order = new Orders();
                    order.setOrderID(rs.getInt("OrderID"));
                    order.setDate(rs.getDate("Date"));
                    order.setOrderStatus(rs.getString("Status"));
                    list.put(order.getOrderID(), order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Orders getOrderByOrderID(int orderID){
        Orders order = null;
        String sql = "SELECT * FROM Orders WHERE OrderID = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                order = new Orders();
                order.setOrderID(rs.getInt("OrderID"));
                order.setUserID(rs.getInt("UserID"));
                order.setDate(rs.getDate("Date"));
                order.setOrderStatus(rs.getString("Status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    public int createOrderWithVehiclesAndBill(String date, int userID, List<Integer> vehicleIDs, List<String> withDriverList) {
        if (vehicleIDs == null || withDriverList == null || vehicleIDs.isEmpty() || vehicleIDs.size() != withDriverList.size()) {
            System.err.println("Invalid vehicle list or withDriver list");
            return -1;
        }

        java.sql.Date sqlDate = convertToSQLDate(date);
        if (sqlDate == null) {
            System.err.println("Invalid date format: " + date);
            return -1;
        }

        String insertOrderSQL = "INSERT INTO Orders (Date, Status, UserID) VALUES (?, 'Pending', ?)";
        String insertVehicleOrderSQL = "INSERT INTO VehicleOrders (VehicleID, WithDriver, OrderID) VALUES (?, ?, ?)";
        String calculateTotalSQL = """
            SELECT COALESCE(SUM(v.Price + CASE WHEN vo.WithDriver = 'Yes' THEN 200000 ELSE 0 END), 0)
            FROM Vehicles v 
            JOIN VehicleOrders vo ON v.VehicleID = vo.VehicleID 
            WHERE vo.OrderID = ?
        """;
        String insertBillSQL = "INSERT INTO Bills (OrderID, TotalAmount, Status) VALUES (?, ?, 'Unpaid')";

        int orderID = -1;

        try {
            connection.setAutoCommit(false);

            // Bước 1: Tạo đơn hàng
            try (PreparedStatement ps = connection.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDate(1, sqlDate);
                ps.setInt(2, userID);
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        orderID = rs.getInt(1);
                    }
                }
            }

            // Bước 2: Thêm xe vào đơn hàng
            try (PreparedStatement ps = connection.prepareStatement(insertVehicleOrderSQL)) {
                for (int i = 0; i < vehicleIDs.size(); i++) {
                    ps.setInt(1, vehicleIDs.get(i));
                    ps.setString(2, withDriverList.get(i)); // "Yes" hoặc "No"
                    ps.setInt(3, orderID);
                    ps.addBatch();
                }
                ps.executeBatch();
            }

            // Bước 3: Tính tổng tiền
            double totalAmount = 0;
            try (PreparedStatement ps = connection.prepareStatement(calculateTotalSQL)) {
                ps.setInt(1, orderID);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        totalAmount = rs.getDouble(1);
                    }
                }
            }

            // Bước 4: Tạo hóa đơn
            try (PreparedStatement ps = connection.prepareStatement(insertBillSQL)) {
                ps.setInt(1, orderID);
                ps.setDouble(2, totalAmount);
                ps.executeUpdate();
            }

            connection.commit();
            System.out.println("Order created successfully: OrderID=" + orderID + ", Total=" + totalAmount);

        } catch (Exception e) {
            try {
                connection.rollback();
                System.err.println("Transaction rolled back: " + e.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderID;
    }

    private java.sql.Date convertToSQLDate(String dateStr) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = format.parse(dateStr);
            return new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + dateStr);
            return null;
        }
    }
    
    public void deleteOrder(int orderID){
        try {
            String sql = "delete from Orders where OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderID);
            st.execute();
            st.close();          
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
