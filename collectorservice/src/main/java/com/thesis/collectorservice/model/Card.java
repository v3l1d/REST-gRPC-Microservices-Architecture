package com.thesis.collectorservice.model;

import java.time.LocalDate;

public class Card {
    private String Owner;
    private String CardNumber;
    private String Code;

    private LocalDate expireDate;

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public Card(String owner, String cardNumber, String code, LocalDate expireDate) {
        this.Owner = owner;
        this.CardNumber = cardNumber;
        this.Code = code;
        this.expireDate=expireDate;


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
        return "{" +
                "\"Owner\": \"" + Owner + "\"," +
                "\"CardNumber\": \"" + CardNumber + "\"," +
                "\"Code\": \"" + Code + "\"," +
                "\"expireDate\": \"" + expireDate + "\"" +
                "}";
    }

}
