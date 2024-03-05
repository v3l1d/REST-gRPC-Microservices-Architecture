package com.thesis.bankingservice.model;

public class Card {
    private String Owner;
    private String CardNumber;
    private String Code;

    public Card(String owner, String cardNumber, String code) {
        Owner = owner;
        CardNumber = cardNumber;
        Code = code;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        this.Owner = owner;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.CardNumber = cardNumber;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        this.Code = code;
    }

    @Override
    public String toString() {
        return "Card{" +
                "Owner='" + Owner + '\'' +
                ", CardNumber='" + CardNumber + '\'' +
                ", Code='" + Code + '\'' +
                '}';
    }
}
