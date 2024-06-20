
package com.thesis.mailsmsservice.model.UserDataModels;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code",
    "label",
    "value",
    "belfioreCode",
    "provinceCode",
    "bancaItaliaCode"
})
@Generated("jsonschema2pojo")
public class BirthCity {

    @JsonProperty("code")
    private String code;
    @JsonProperty("label")
    private String label;
    @JsonProperty("value")
    private boolean value;
    @JsonProperty("belfioreCode")
    private String belfioreCode;
    @JsonProperty("provinceCode")
    private String provinceCode;
    @JsonProperty("bancaItaliaCode")
    private Object bancaItaliaCode;
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

    public BirthCity withCode(String code) {
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

    public BirthCity withLabel(String label) {
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

    public BirthCity withValue(boolean value) {
        this.value = value;
        return this;
    }

    @JsonProperty("belfioreCode")
    public String getBelfioreCode() {
        return belfioreCode;
    }

    @JsonProperty("belfioreCode")
    public void setBelfioreCode(String belfioreCode) {
        this.belfioreCode = belfioreCode;
    }

    public BirthCity withBelfioreCode(String belfioreCode) {
        this.belfioreCode = belfioreCode;
        return this;
    }

    @JsonProperty("provinceCode")
    public String getProvinceCode() {
        return provinceCode;
    }

    @JsonProperty("provinceCode")
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public BirthCity withProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
        return this;
    }

    @JsonProperty("bancaItaliaCode")
    public Object getBancaItaliaCode() {
        return bancaItaliaCode;
    }

    @JsonProperty("bancaItaliaCode")
    public void setBancaItaliaCode(Object bancaItaliaCode) {
        this.bancaItaliaCode = bancaItaliaCode;
    }

    public BirthCity withBancaItaliaCode(Object bancaItaliaCode) {
        this.bancaItaliaCode = bancaItaliaCode;
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

    public BirthCity withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
