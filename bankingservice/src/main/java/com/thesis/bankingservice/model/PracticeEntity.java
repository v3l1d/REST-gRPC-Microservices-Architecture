package com.thesis.bankingservice.model;

import com.thesis.bankingservice.model.UserDataModels.model.UserData;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name="practice")
public class PracticeEntity {
    @Column(name = "practice_id")
    @Id
    private String practiceId;
    @Column(name = "status")
    private String status;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;

    @Column(name="payment_method")
    private String paymentMethod;

    @Column(name="additional_info")
    private String additionalInfo;

    @Column(name="financing_info")
    private String financingInfo;

    @Column(name="vehicle_info")
    private String vehicleInfo;

    @Column(name="payment_info")
    private String paymentInfo;

    @Column(name="personal_document")
    private String personalDocument;

    @Column(name="credit_document")
    private String creditDocument;
    
   @Transient
    private UserData userData;

   @Transient
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

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getFinancingInfo() {
        return financingInfo;
    }

    public void setFinancingInfo(String financingInfo) {
        this.financingInfo = financingInfo;
    }

    public String getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(String vehicleInfo) {
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
                "\"creditDocument\":" + creditDocument + "," +
                "\"userData\":" + userData + ", "+
                "}";
    }


}
