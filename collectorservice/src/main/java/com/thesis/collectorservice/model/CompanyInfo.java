
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
    "companyName",
    "companyAddress",
    "companyCountry",
    "companyProvince",
    "companyCity",
    "companyZipCode"
})
@Generated("jsonschema2pojo")
public class CompanyInfo {

    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("companyAddress")
    private String companyAddress;
    @JsonProperty("companyCountry")
    private CompanyCountry companyCountry;
    @JsonProperty("companyProvince")
    private CompanyProvince companyProvince;
    @JsonProperty("companyCity")
    private CompanyCity companyCity;
    @JsonProperty("companyZipCode")
    private String companyZipCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("companyName")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public CompanyInfo withCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    @JsonProperty("companyAddress")
    public String getCompanyAddress() {
        return companyAddress;
    }

    @JsonProperty("companyAddress")
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public CompanyInfo withCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
        return this;
    }

    @JsonProperty("companyCountry")
    public CompanyCountry getCompanyCountry() {
        return companyCountry;
    }

    @JsonProperty("companyCountry")
    public void setCompanyCountry(CompanyCountry companyCountry) {
        this.companyCountry = companyCountry;
    }

    public CompanyInfo withCompanyCountry(CompanyCountry companyCountry) {
        this.companyCountry = companyCountry;
        return this;
    }

    @JsonProperty("companyProvince")
    public CompanyProvince getCompanyProvince() {
        return companyProvince;
    }

    @JsonProperty("companyProvince")
    public void setCompanyProvince(CompanyProvince companyProvince) {
        this.companyProvince = companyProvince;
    }

    public CompanyInfo withCompanyProvince(CompanyProvince companyProvince) {
        this.companyProvince = companyProvince;
        return this;
    }

    @JsonProperty("companyCity")
    public CompanyCity getCompanyCity() {
        return companyCity;
    }

    @JsonProperty("companyCity")
    public void setCompanyCity(CompanyCity companyCity) {
        this.companyCity = companyCity;
    }

    public CompanyInfo withCompanyCity(CompanyCity companyCity) {
        this.companyCity = companyCity;
        return this;
    }

    @JsonProperty("companyZipCode")
    public String getCompanyZipCode() {
        return companyZipCode;
    }

    @JsonProperty("companyZipCode")
    public void setCompanyZipCode(String companyZipCode) {
        this.companyZipCode = companyZipCode;
    }

    public CompanyInfo withCompanyZipCode(String companyZipCode) {
        this.companyZipCode = companyZipCode;
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

    public CompanyInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
