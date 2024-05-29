
package com.thesis.collectorservice.model.UserDataModels;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "jobOccupation",
    "incomeDocType",
    "workingStartDate",
    "taxYear",
    "netMonthlyIncome",
    "taxWitholdingFlag",
    "taxWitholdingCode",
    "inpsRegistrationFlag",
    "countryOfEmployment",
    "companyInfo"
})
@Generated("jsonschema2pojo")
public class IncomeDocumentInfo {

    @JsonProperty("jobOccupation")
    private JobOccupation jobOccupation;
    @JsonProperty("incomeDocType")
    private String incomeDocType;
    @JsonProperty("workingStartDate")
    private String workingStartDate;
    @JsonProperty("taxYear")
    private String taxYear;
    @JsonProperty("netMonthlyIncome")
    private double netMonthlyIncome;
    @JsonProperty("taxWitholdingFlag")
    private boolean taxWitholdingFlag;
    @JsonProperty("taxWitholdingCode")
    private String taxWitholdingCode;
    @JsonProperty("inpsRegistrationFlag")
    private boolean inpsRegistrationFlag;
    @JsonProperty("countryOfEmployment")
    private String countryOfEmployment;
    @JsonProperty("companyInfo")
    private CompanyInfo companyInfo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("jobOccupation")
    public JobOccupation getJobOccupation() {
        return jobOccupation;
    }

    @JsonProperty("jobOccupation")
    public void setJobOccupation(JobOccupation jobOccupation) {
        this.jobOccupation = jobOccupation;
    }

    public IncomeDocumentInfo withJobOccupation(JobOccupation jobOccupation) {
        this.jobOccupation = jobOccupation;
        return this;
    }

    @JsonProperty("incomeDocType")
    public String getIncomeDocType() {
        return incomeDocType;
    }

    @JsonProperty("incomeDocType")
    public void setIncomeDocType(String incomeDocType) {
        this.incomeDocType = incomeDocType;
    }

    public IncomeDocumentInfo withIncomeDocType(String incomeDocType) {
        this.incomeDocType = incomeDocType;
        return this;
    }

    @JsonProperty("workingStartDate")
    public String getWorkingStartDate() {
        return workingStartDate;
    }

    @JsonProperty("workingStartDate")
    public void setWorkingStartDate(String workingStartDate) {
        this.workingStartDate = workingStartDate;
    }

    public IncomeDocumentInfo withWorkingStartDate(String workingStartDate) {
        this.workingStartDate = workingStartDate;
        return this;
    }

    @JsonProperty("taxYear")
    public String getTaxYear() {
        return taxYear;
    }

    @JsonProperty("taxYear")
    public void setTaxYear(String taxYear) {
        this.taxYear = taxYear;
    }

    public IncomeDocumentInfo withTaxYear(String taxYear) {
        this.taxYear = taxYear;
        return this;
    }

    @JsonProperty("netMonthlyIncome")
    public double getNetMonthlyIncome() {
        return netMonthlyIncome;
    }

    @JsonProperty("netMonthlyIncome")
    public void setNetMonthlyIncome(double netMonthlyIncome) {
        this.netMonthlyIncome = netMonthlyIncome;
    }

    public IncomeDocumentInfo withNetMonthlyIncome(double netMonthlyIncome) {
        this.netMonthlyIncome = netMonthlyIncome;
        return this;
    }

    @JsonProperty("taxWitholdingFlag")
    public boolean isTaxWitholdingFlag() {
        return taxWitholdingFlag;
    }

    @JsonProperty("taxWitholdingFlag")
    public void setTaxWitholdingFlag(boolean taxWitholdingFlag) {
        this.taxWitholdingFlag = taxWitholdingFlag;
    }

    public IncomeDocumentInfo withTaxWitholdingFlag(boolean taxWitholdingFlag) {
        this.taxWitholdingFlag = taxWitholdingFlag;
        return this;
    }

    @JsonProperty("taxWitholdingCode")
    public String getTaxWitholdingCode() {
        return taxWitholdingCode;
    }

    @JsonProperty("taxWitholdingCode")
    public void setTaxWitholdingCode(String taxWitholdingCode) {
        this.taxWitholdingCode = taxWitholdingCode;
    }

    public IncomeDocumentInfo withTaxWitholdingCode(String taxWitholdingCode) {
        this.taxWitholdingCode = taxWitholdingCode;
        return this;
    }

    @JsonProperty("inpsRegistrationFlag")
    public boolean isInpsRegistrationFlag() {
        return inpsRegistrationFlag;
    }

    @JsonProperty("inpsRegistrationFlag")
    public void setInpsRegistrationFlag(boolean inpsRegistrationFlag) {
        this.inpsRegistrationFlag = inpsRegistrationFlag;
    }

    public IncomeDocumentInfo withInpsRegistrationFlag(boolean inpsRegistrationFlag) {
        this.inpsRegistrationFlag = inpsRegistrationFlag;
        return this;
    }

    @JsonProperty("countryOfEmployment")
    public String getCountryOfEmployment() {
        return countryOfEmployment;
    }

    @JsonProperty("countryOfEmployment")
    public void setCountryOfEmployment(String countryOfEmployment) {
        this.countryOfEmployment = countryOfEmployment;
    }

    public IncomeDocumentInfo withCountryOfEmployment(String countryOfEmployment) {
        this.countryOfEmployment = countryOfEmployment;
        return this;
    }

    @JsonProperty("companyInfo")
    public CompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    @JsonProperty("companyInfo")
    public void setCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    public IncomeDocumentInfo withCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
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

    public IncomeDocumentInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
