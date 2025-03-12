/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author chang
 */
public class Vehicles {
    private int VehicleID;
    private String Brand;
    private String Model;
    private int Seats;
    private float Price;
    private String ImageLink;

    public Vehicles() {
    }

    public Vehicles(int VehicleID, String Brand, String Model, int Seats, float Price, String ImageLink) {
        this.VehicleID = VehicleID;
        this.Brand = Brand;
        this.Model = Model;
        this.Seats = Seats;
        this.Price = Price;
        this.ImageLink = ImageLink;
    }

    public int getVehicleID() {
        return VehicleID;
    }

    public void setVehicleID(int VehicleID) {
        this.VehicleID = VehicleID;
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

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public String getImageLink() {
        return ImageLink;
    }

    public void setImageLink(String ImageLink) {
        this.ImageLink = ImageLink;
    }
    
    
}
