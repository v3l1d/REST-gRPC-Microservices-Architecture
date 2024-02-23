package com.thesis.financialcalcservice.model;

public class Financing {
    private String financingId;
    private String vehicleId;
    private String customerName;
    private double loanAmount;
    private double loanTerm;


    public Financing(String financingId, String vehicleId, String customerName, double loanAmount, double loanTerm) {
        this.financingId = financingId;
        this.vehicleId = vehicleId;
        this.customerName = customerName;
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

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
            ", customerName='" + getCustomerName() + "'" +
            ", loanAmount='" + getLoanAmount() + "'" +
            ", loanTerm='" + getLoanTerm() + "'" +
            "}";
    }

}