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
import model.Bills;

/**
 *
 * @author Hung
 */
public class BillDAO extends DBContext{
    
    public Map<Integer, Bills> getAllBills() {
        Map<Integer, Bills> list = new HashMap<>();
        try {
            String sql = "select * from Bills";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Bills b = new Bills();
                b.setOrderID(rs.getInt("OrderID"));
                b.setBillID(rs.getInt("BillID"));
                b.setStatus(rs.getString("Status"));
                b.setTotalAmount(rs.getFloat("TotalAmount"));
                list.put(b.getBillID(), b);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public int AddBill(int orderID, float TotalAmount, String status) {
        int BillID = -1;
        try {
            String sql = "insert into Bills (OrderID,Status,TotalAmount) values (?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setFloat(3, TotalAmount);
            st.setString(2, status);
            st.setInt(1, orderID);
            st.executeUpdate();
            try(ResultSet rs = st.getGeneratedKeys()){
                if(rs.next()){
                    BillID = rs.getInt(1);
                }
            }
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return BillID;
    }
    
    public void updateBill(int orderID){
        String status = "Paid";
        try{
            String sql = "UPDATE Bills SET Status = ? WHERE OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(2, orderID);
            st.setString(1, status);
            st.executeUpdate();
            st.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public float getTotalAmountByOrderID(int orderID){
        float totalAmount=0;
        try{
            String sql = "select TotalAmount from Bills";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                totalAmount = rs.getFloat("TotalAmount");
            }
            rs.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return totalAmount;
    }
}
