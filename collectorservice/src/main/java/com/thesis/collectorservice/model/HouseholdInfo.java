
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
    "maritalStatus",
    "houseProperty",
    "workStartingDate",
    "familyMembers",
    "workingMembers"
})
@Generated("jsonschema2pojo")
public class HouseholdInfo {

    @JsonProperty("maritalStatus")
    private MaritalStatus maritalStatus;
    @JsonProperty("houseProperty")
    private HouseProperty houseProperty;
    @JsonProperty("workStartingDate")
    private String workStartingDate;
    @JsonProperty("familyMembers")
    private String familyMembers;
    @JsonProperty("workingMembers")
    private String workingMembers;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("maritalStatus")
    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    @JsonProperty("maritalStatus")
    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public HouseholdInfo withMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    @JsonProperty("houseProperty")
    public HouseProperty getHouseProperty() {
        return houseProperty;
    }

    @JsonProperty("houseProperty")
    public void setHouseProperty(HouseProperty houseProperty) {
        this.houseProperty = houseProperty;
    }

    public HouseholdInfo withHouseProperty(HouseProperty houseProperty) {
        this.houseProperty = houseProperty;
        return this;
    }

    @JsonProperty("workStartingDate")
    public String getWorkStartingDate() {
        return workStartingDate;
    }

    @JsonProperty("workStartingDate")
    public void setWorkStartingDate(String workStartingDate) {
        this.workStartingDate = workStartingDate;
    }

    public HouseholdInfo withWorkStartingDate(String workStartingDate) {
        this.workStartingDate = workStartingDate;
        return this;
    }

    @JsonProperty("familyMembers")
    public String getFamilyMembers() {
        return familyMembers;
    }

    @JsonProperty("familyMembers")
    public void setFamilyMembers(String familyMembers) {
        this.familyMembers = familyMembers;
    }

    public HouseholdInfo withFamilyMembers(String familyMembers) {
        this.familyMembers = familyMembers;
        return this;
    }

    @JsonProperty("workingMembers")
    public String getWorkingMembers() {
        return workingMembers;
    }

    @JsonProperty("workingMembers")
    public void setWorkingMembers(String workingMembers) {
        this.workingMembers = workingMembers;
    }

    public HouseholdInfo withWorkingMembers(String workingMembers) {
        this.workingMembers = workingMembers;
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

    public HouseholdInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
