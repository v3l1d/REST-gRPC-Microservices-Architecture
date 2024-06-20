package com.thesis.ratingservice.model;

import com.thesis.ratingservice.model.UserDataModels.UserData;


public class PracticeEntity {
    private String practiceId;
    private String status;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String paymentMethod;
    private AdditionalInfo additionalInfo;

    private Financing financingInfo;

    private Vehicle vehicleInfo;

    private String paymentInfo;

    private String personalDocument;

    private String creditDocument;
    private UserData userData;

    private String evaluationResult;

    public String getEvaluationResult() {
        return evaluationResult;
    }

    public void setEvaluationResult(String evaluationResult) {
        this.evaluationResult = evaluationResult;
    }
    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Financing getFinancingInfo() {
        return financingInfo;
    }

    public void setFinancingInfo(Financing financingInfo) {
        this.financingInfo = financingInfo;
    }

    public Vehicle getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(Vehicle vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public String getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(String practiceId) {
        this.practiceId = practiceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getPersonalDocument() {
        return personalDocument;
    }

    public void setPersonalDocument(String personalDocument) {
        this.personalDocument = personalDocument;
    }

    public String getCreditDocument() {
        return creditDocument;
    }

    public void setCreditDocument(String creditDocument) {
        this.creditDocument = creditDocument;
    }

    @Override
    public String toString() {
        return "{" +
                "\"practiceId\": \"" + practiceId + "\"," +
                "\"status\": \"" + status + "\"," +
                "\"name\": \"" + name + "\"," +
                "\"surname\": \"" + surname + "\"," +
                "\"email\": \"" + email + "\"," +
                "\"phone\": \"" + phone + "\"," +
                "\"paymentMethod\": \"" + paymentMethod + "\"," +
                "\"additionalInfo\": " + additionalInfo + "," +
                "\"financingInfo\": " + financingInfo + "," +
                "\"vehicleInfo\": " + vehicleInfo + "," +
                "\"paymentInfo\": " + paymentInfo + "," +
                "\"personalDocument\":" + personalDocument + "," +
                "\"creditDocument\":" + creditDocument +
                "}";
    }


}
