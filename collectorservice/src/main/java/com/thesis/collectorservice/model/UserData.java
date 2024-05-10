
package com.thesis.collectorservice.model;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "personalDetails",
    "addressInfo",
    "documentInfo",
    "householdInfo",
    "incomeDocumentInfo",
    "accountCheck",
    "step"
})
@Generated("jsonschema2pojo")
public class UserData {

    @JsonProperty("personalDetails")
    private PersonalDetails personalDetails;
    @JsonProperty("addressInfo")
    private AddressInfo addressInfo;
    @JsonProperty("documentInfo")
    private DocumentInfo documentInfo;
    @JsonProperty("householdInfo")
    private HouseholdInfo householdInfo;
    @JsonProperty("incomeDocumentInfo")
    private IncomeDocumentInfo incomeDocumentInfo;
    @JsonProperty("accountCheck")
    private boolean accountCheck;
    @JsonProperty("step")
    private String step;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("personalDetails")
    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    @JsonProperty("personalDetails")
    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public UserData withPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
        return this;
    }

    @JsonProperty("addressInfo")
    public AddressInfo getAddressInfo() {
        return addressInfo;
    }

    @JsonProperty("addressInfo")
    public void setAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }

    public UserData withAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
        return this;
    }

    @JsonProperty("documentInfo")
    public DocumentInfo getDocumentInfo() {
        return documentInfo;
    }

    @JsonProperty("documentInfo")
    public void setDocumentInfo(DocumentInfo documentInfo) {
        this.documentInfo = documentInfo;
    }

    public UserData withDocumentInfo(DocumentInfo documentInfo) {
        this.documentInfo = documentInfo;
        return this;
    }

    @JsonProperty("householdInfo")
    public HouseholdInfo getHouseholdInfo() {
        return householdInfo;
    }

    @JsonProperty("householdInfo")
    public void setHouseholdInfo(HouseholdInfo householdInfo) {
        this.householdInfo = householdInfo;
    }

    public UserData withHouseholdInfo(HouseholdInfo householdInfo) {
        this.householdInfo = householdInfo;
        return this;
    }

    @JsonProperty("incomeDocumentInfo")
    public IncomeDocumentInfo getIncomeDocumentInfo() {
        return incomeDocumentInfo;
    }

    @JsonProperty("incomeDocumentInfo")
    public void setIncomeDocumentInfo(IncomeDocumentInfo incomeDocumentInfo) {
        this.incomeDocumentInfo = incomeDocumentInfo;
    }

    public UserData withIncomeDocumentInfo(IncomeDocumentInfo incomeDocumentInfo) {
        this.incomeDocumentInfo = incomeDocumentInfo;
        return this;
    }

    @JsonProperty("accountCheck")
    public boolean isAccountCheck() {
        return accountCheck;
    }

    @JsonProperty("accountCheck")
    public void setAccountCheck(boolean accountCheck) {
        this.accountCheck = accountCheck;
    }

    public UserData withAccountCheck(boolean accountCheck) {
        this.accountCheck = accountCheck;
        return this;
    }

    @JsonProperty("step")
    public String getStep() {
        return step;
    }

    @JsonProperty("step")
    public void setStep(String step) {
        this.step = step;
    }

    public UserData withStep(String step) {
        this.step = step;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public UserData withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
