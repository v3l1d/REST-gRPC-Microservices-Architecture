package com.thesis.financialcalcservice.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="vehicles")
public class Vehicle {
    @Id
    @Column(name="id")
    private String id;
    @Column(name="brand")
    private String brand;
    @Column(name="model")
    private String name;
    @Column(name="year")
    private int model;

    public Vehicle(){

    }

 public Vehicle(String id,String brand, String name, int model) {
        this.id=id;
        this.brand = brand;
        this.name = name;
        this.model = model;
    }
    public String getId(){
        return this.id;
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

    public void setId(String id){
        this.id=id;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", brand='" + getBrand() + "'" +
            ", name='" + getName() + "'" +
            ", model='" + getModel() + "'" +
            "}";
    }
    
    
}
   