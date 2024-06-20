package com.thesis.bankingservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thesis.bankingservice.model.UserDataModels.model.UserData;
import com.thesis.bankingservice.utility.AdditionalInfoJsonAttributeConverter;
import com.thesis.bankingservice.utility.FinancingJsonAttributeConverter;
import com.thesis.bankingservice.utility.UserDataJsonAttributeConverter;
import com.thesis.bankingservice.utility.UserDataJsonAttributeConverter;
import com.thesis.bankingservice.utility.VehicleJsonAttributeConverter;
import jakarta.persistence.*;


@Entity
@Table(name = "practice")
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

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "additional_info", columnDefinition = "JSON")
    @Convert(converter = AdditionalInfoJsonAttributeConverter.class)
    @JsonProperty
    private AdditionalInfo additionalInfo;

    @Column(name = "financing_info", columnDefinition = "JSON")
    @Convert(converter = FinancingJsonAttributeConverter.class)
    private Financing financingInfo;

    @Column(name = "vehicle_info", columnDefinition = "JSON")
    @Convert(converter = VehicleJsonAttributeConverter.class)
    private Vehicle vehicleInfo;

    @Column(name = "payment_info")
    private String paymentInfo;

    @Column(name = "personal_document")
    private String personalDocument;

    @Column(name = "credit_document")
    private String creditDocument;

    @Column(name = "user_data", columnDefinition = "JSON")
    @Convert(converter = UserDataJsonAttributeConverter.class)
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

    public void setUserData(UserData userData) {
        this.userData = userData;
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
                "\"userData\":" + userData + ", " +
                "}";
    }


}
