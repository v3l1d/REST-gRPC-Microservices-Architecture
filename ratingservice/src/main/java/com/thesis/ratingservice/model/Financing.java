package com.thesis.ratingservice.model;


public class Financing {

    private String financingId;

    private String vehicleId;

    private double loanAmount;

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
                "\"financingId\":\"" + financingId + "\"," +
                "\"vehicleId\":\"" + vehicleId + "\"," +
                "\"loanAmount\":" + loanAmount + "," +
                "\"loanTerm\":" + loanTerm +
                "}";
    }


}