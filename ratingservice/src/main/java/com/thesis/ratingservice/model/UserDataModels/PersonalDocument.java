package com.thesis.ratingservice.model.UserDataModels;

import java.time.LocalDate;

public class PersonalDocument {
    private String documentId;
    private String documentType;

    LocalDate expireDate;

    public PersonalDocument(String documentId, String documentType, LocalDate expireDate) {
        this.documentId = documentId;
        this.documentType = documentType;
        this.expireDate = expireDate;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "{" +
                "\"documentId\": \"" + documentId + "\"," +
                "\"documentType\": \"" + documentType + "\"," +
                "\"expireDate\": \"" + expireDate + "\"" +
                "}";
    }

}
