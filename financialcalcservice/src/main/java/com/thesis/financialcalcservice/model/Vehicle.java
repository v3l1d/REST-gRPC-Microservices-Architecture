package com.thesis.financialcalcservice.model;

public class Vehicle {
    private String brand;
    private String name;
    private int model;

 public Vehicle(String brand, String name, int model) {
        this.brand = brand;
        this.name = name;
        this.model = model;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getModel() {
        return this.model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "{" +
            " brand='" + getBrand() + "'" +
            ", name='" + getName() + "'" +
            ", model='" + getModel() + "'" +
            "}";
    }
    
}
   