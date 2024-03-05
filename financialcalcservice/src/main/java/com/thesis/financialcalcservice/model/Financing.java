package com.thesis.financialcalcservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="financing")
public class Financing {
    @Id
    @Column(name = "financing_id")
    private String financingId;
    @Column(name = "vehicle_id")
    private String vehicleId;
    @Column(name = "loan_amount")
    private double loanAmount;
    @Column(name = "loan_term")
    private double loanTerm;

    public Financing() {

    }

    public Financing(String financingId, String vehicleId, double loanAmount, double loanTerm) {
        this.financingId = financingId;
        this.vehicleId = vehicleId;
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
    }


    public String getFinancingId() {
        return this.financingId;
    }

    public void setFinancingId(String financingId) {
        this.financingId = financingId;
    }

    public String getVehicleId() {
        return this.vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }


    public double getLoanAmount() {
        return this.loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getLoanTerm() {
        return this.loanTerm;
    }

    public void setLoanTerm(double loanTerm) {
        this.loanTerm = loanTerm;
    }


    @Override
    public String toString() {
        return "{" +
                " financingId='" + getFinancingId() + "'" +
                ", vehicleId='" + getVehicleId() + "'" +
                ", loanAmount='" + getLoanAmount() + "'" +
                ", loanTerm='" + getLoanTerm() + "'" +
                "}";
    }

}