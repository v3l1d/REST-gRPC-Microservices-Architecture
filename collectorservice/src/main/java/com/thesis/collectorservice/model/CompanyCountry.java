
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
    "code",
    "label",
    "value",
    "codeIso3",
    "optional"
})
@Generated("jsonschema2pojo")
public class CompanyCountry {

    @JsonProperty("code")
    private String code;
    @JsonProperty("label")
    private String label;
    @JsonProperty("value")
    private boolean value;
    @JsonProperty("codeIso3")
    private String codeIso3;
    @JsonProperty("optional")
    private Object optional;
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

    public CompanyCountry withCode(String code) {
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

    public CompanyCountry withLabel(String label) {
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

    public CompanyCountry withValue(boolean value) {
        this.value = value;
        return this;
    }

    @JsonProperty("codeIso3")
    public String getCodeIso3() {
        return codeIso3;
    }

    @JsonProperty("codeIso3")
    public void setCodeIso3(String codeIso3) {
        this.codeIso3 = codeIso3;
    }

    public CompanyCountry withCodeIso3(String codeIso3) {
        this.codeIso3 = codeIso3;
        return this;
    }

    @JsonProperty("optional")
    public Object getOptional() {
        return optional;
    }

    @JsonProperty("optional")
    public void setOptional(Object optional) {
        this.optional = optional;
    }

    public CompanyCountry withOptional(Object optional) {
        this.optional = optional;
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

    public CompanyCountry withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
