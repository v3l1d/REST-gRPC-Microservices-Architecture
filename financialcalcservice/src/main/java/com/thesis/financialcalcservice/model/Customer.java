package com.thesis.financialcalcservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="email")
    private String email;
    @Column(name="phone")
    private String phone;
    @Column(name="sms_verified")
    private boolean SMSverified;
    @Column (name="mail_verified")
    private boolean mailVerified;
    public Customer(){}


    public Customer(String name, String surname, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.mailVerified=false;
        this.SMSverified=false;
    }

  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setMailVerified(boolean mailVerified) {
        this.mailVerified = mailVerified;
    }

    public void setSMSverified(boolean SMSverified) {
        this.SMSverified = SMSverified;
    }
}


