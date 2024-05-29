
package com.thesis.bankingservice.model.UserDataModels.model;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code",
    "label",
    "value",
    "grossAnnualIncomeRequired"
})
@Generated("jsonschema2pojo")
public class JobOccupation {

    @JsonProperty("code")
    private String code;
    @JsonProperty("label")
    private String label;
    @JsonProperty("value")
    private boolean value;
    @JsonProperty("grossAnnualIncomeRequired")
    private boolean grossAnnualIncomeRequired;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    public JobOccupation withCode(String code) {
        this.code = code;
        return this;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    public JobOccupation withLabel(String label) {
        this.label = label;
        return this;
    }

    @JsonProperty("value")
    public boolean isValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(boolean value) {
        this.value = value;
    }

    public JobOccupation withValue(boolean value) {
        this.value = value;
        return this;
    }

    @JsonProperty("grossAnnualIncomeRequired")
    public boolean isGrossAnnualIncomeRequired() {
        return grossAnnualIncomeRequired;
    }

    @JsonProperty("grossAnnualIncomeRequired")
    public void setGrossAnnualIncomeRequired(boolean grossAnnualIncomeRequired) {
        this.grossAnnualIncomeRequired = grossAnnualIncomeRequired;
    }

    public JobOccupation withGrossAnnualIncomeRequired(boolean grossAnnualIncomeRequired) {
        this.grossAnnualIncomeRequired = grossAnnualIncomeRequired;
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

    public JobOccupation withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
