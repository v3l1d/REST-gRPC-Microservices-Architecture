package com.thesis.bankingservice.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="practice")
public class Vehicle {
    @Id
    @Column(name="vehicle_id")
    private String vehicleId;
    @Column(name="brand")
    private String brand;
    @Column(name="model")
    private String model;
    @Column(name="year")
    private int year;

    public Vehicle(){

    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Vehicle(String vehicleId, String brand, String model, int year) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    @Override
    public String toString() {
        return "{" +
                "\"vehicleId\":\"" + getVehicleId() + "\"," +
                "\"brand\":\"" + getBrand() + "\"," +
                "\"model\":\"" + getModel() + "\"," +
                "\"year\":" + getYear() +
                "}";
    }



}
   