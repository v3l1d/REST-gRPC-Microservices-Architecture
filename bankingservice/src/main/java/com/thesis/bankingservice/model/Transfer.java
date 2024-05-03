package com.thesis.bankingservice.model;

public class Transfer {
    String Owner;
    String BankId;

    public Transfer(String owner, String bankId) {
        Owner = owner;
        BankId = bankId;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getBankId() {
        return BankId;
    }

    public void setBankId(String bankId) {
        BankId = bankId;
    }

    @Override
    public String toString() {
        return "{" +
                "\"Owner\": \"" + Owner + "\"," +
                "\"BankId\": \"" + BankId + "\"" +
                "}";
    }

}
