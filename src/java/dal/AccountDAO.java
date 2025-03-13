/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import jakarta.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import model.Accounts;
/**
 *
 * @author chang
 */
public class AccountDAO extends DBContext{
    
    public Map<Integer, Accounts> getAllCategory() {
        Map<Integer, Accounts> list = new HashMap<>();
        try {
            String sql = "SELECT *\n"
                    + "FROM Users u\n"
                    + "JOIN Roles r ON u.RoleID = r.RoleID;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Accounts acc = new Accounts();
                acc.setUserID(rs.getInt("UserID"));
                acc.setUsername(rs.getString("Username"));
                acc.setPassword(rs.getString("Password"));
                acc.setPhone(rs.getString("Phone"));
                acc.setEmail(rs.getString("Email"));
                acc.setRoleID(rs.getInt("RoleID"));
                acc.setRoleName(rs.getString("RoleName"));
                list.put(acc.getUserID(), acc);

            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public Accounts check(String username, String password) {
        try {
            String sql = "SELECT *\n"
                    + "FROM Users u\n"
                    + "JOIN Roles r ON u.RoleID = r.RoleID where Username like ? and Password like ?;";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Accounts acc = new Accounts();
                acc.setUserID(rs.getInt("UserID"));
                acc.setUsername(rs.getString("Username"));
                acc.setPassword(rs.getString("Password"));
                acc.setPhone(rs.getString("Phone"));
                acc.setEmail(rs.getString("Email"));
                acc.setRoleID(rs.getInt("RoleID"));
                acc.setRoleName(rs.getString("RoleName"));
            }
        } catch (Exception e) {
            
        }
        return null;
    }
    
    public void addAccount(int UserID, String Username, String Password, String Phone, String Email,int RoleID) {
        try {
            String sql = "insert into Categories (UserID,Username,Password,Phone,Email,RoleID) values (?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, UserID);
            st.setString(2, Username);
            st.setString(3, Password);
            st.setString(4, Phone);
            st.setString(5, Email);
            st.setInt(6, RoleID);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
}
