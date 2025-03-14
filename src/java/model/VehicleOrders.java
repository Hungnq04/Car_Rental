/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author chang
 */
public class VehicleOrders {
    private int VehicleOrderID;
    private int VehicleID;
    private String WithDriver;
    private int OrderID;
    private String Brand;
    private String Model;
    private int Seats;

    public VehicleOrders() {
    }

    public VehicleOrders(int VehicleOrderID, int VehicleID, String WithDriver, int OrderID, String Brand, String Model, int Seats) {
        this.VehicleOrderID = VehicleOrderID;
        this.VehicleID = VehicleID;
        this.WithDriver = WithDriver;
        this.OrderID = OrderID;
        this.Brand = Brand;
        this.Model = Model;
        this.Seats = Seats;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public int getSeats() {
        return Seats;
    }

    public void setSeats(int Seats) {
        this.Seats = Seats;
    }

    public int getVehicleOrderID() {
        return VehicleOrderID;
    }

    public void setVehicleOrderID(int VehicleOrderID) {
        this.VehicleOrderID = VehicleOrderID;
    }

    public int getVehicleID() {
        return VehicleID;
    }

    public void setVehicleID(int VehicleID) {
        this.VehicleID = VehicleID;
    }

    public String getWithDriver() {
        return WithDriver;
    }

    public void setWithDriver(String WithDriver) {
        this.WithDriver = WithDriver;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }
    
    
}
