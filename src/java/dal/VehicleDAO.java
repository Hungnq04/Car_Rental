/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import model.Vehicles;

/**
 *
 * @author chang
 */
public class VehicleDAO extends DBContext{
    
    public Map<Integer, Vehicles> getAllVehicles() {
        Map<Integer, Vehicles> list = new HashMap<>();
        try {
            String sql = "select * from Vehicles";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Vehicles car = new Vehicles();
                car.setVehicleID(rs.getInt("VehicleID"));
                car.setBrand(rs.getString("Brand"));
                car.setModel(rs.getString("Model"));
                car.setSeats(rs.getInt("Seats"));
                car.setPrice(rs.getFloat("Price"));
                car.setImageLink(rs.getString("ImageLink"));
                list.put(car.getVehicleID(), car);

            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public Vehicles getVehicleById(int id) {
        try {
            String sql = "Select * from Vehicles where VehicleID = " + id;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Vehicles car = new Vehicles();
                car.setVehicleID(rs.getInt("VehicleID"));
                car.setBrand(rs.getString("Brand"));
                car.setModel(rs.getString("Model"));
                car.setSeats(rs.getInt("Seats"));
                car.setPrice(rs.getFloat("Price"));
                car.setImageLink(rs.getString("ImageLink"));
                return car;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
