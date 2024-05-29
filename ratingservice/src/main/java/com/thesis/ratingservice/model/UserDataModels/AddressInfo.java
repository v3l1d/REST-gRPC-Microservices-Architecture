
package com.thesis.ratingservice.model.UserDataModels;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "resAddress",
    "resZipCode",
    "resCountry",
    "resProvince",
    "resTown",
    "isDomEqualsRes"
})
@Generated("jsonschema2pojo")
public class AddressInfo {

    @JsonProperty("resAddress")
    private String resAddress;
    @JsonProperty("resZipCode")
    private String resZipCode;
    @JsonProperty("resCountry")
    private ResCountry resCountry;
    @JsonProperty("resProvince")
    private ResProvince resProvince;
    @JsonProperty("resTown")
    private ResTown resTown;
    @JsonProperty("isDomEqualsRes")
    private boolean isDomEqualsRes;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("resAddress")
    public String getResAddress() {
        return resAddress;
    }

    @JsonProperty("resAddress")
    public void setResAddress(String resAddress) {
        this.resAddress = resAddress;
    }

    public AddressInfo withResAddress(String resAddress) {
        this.resAddress = resAddress;
        return this;
    }

    @JsonProperty("resZipCode")
    public String getResZipCode() {
        return resZipCode;
    }

    @JsonProperty("resZipCode")
    public void setResZipCode(String resZipCode) {
        this.resZipCode = resZipCode;
    }

    public AddressInfo withResZipCode(String resZipCode) {
        this.resZipCode = resZipCode;
        return this;
    }

    @JsonProperty("resCountry")
    public ResCountry getResCountry() {
        return resCountry;
    }

    @JsonProperty("resCountry")
    public void setResCountry(ResCountry resCountry) {
        this.resCountry = resCountry;
    }

    public AddressInfo withResCountry(ResCountry resCountry) {
        this.resCountry = resCountry;
        return this;
    }

    @JsonProperty("resProvince")
    public ResProvince getResProvince() {
        return resProvince;
    }

    @JsonProperty("resProvince")
    public void setResProvince(ResProvince resProvince) {
        this.resProvince = resProvince;
    }

    public AddressInfo withResProvince(ResProvince resProvince) {
        this.resProvince = resProvince;
        return this;
    }

    @JsonProperty("resTown")
    public ResTown getResTown() {
        return resTown;
    }

    @JsonProperty("resTown")
    public void setResTown(ResTown resTown) {
        this.resTown = resTown;
    }

    public AddressInfo withResTown(ResTown resTown) {
        this.resTown = resTown;
        return this;
    }

    @JsonProperty("isDomEqualsRes")
    public boolean isIsDomEqualsRes() {
        return isDomEqualsRes;
    }

    @JsonProperty("isDomEqualsRes")
    public void setIsDomEqualsRes(boolean isDomEqualsRes) {
        this.isDomEqualsRes = isDomEqualsRes;
    }

    public AddressInfo withIsDomEqualsRes(boolean isDomEqualsRes) {
        this.isDomEqualsRes = isDomEqualsRes;
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

    public AddressInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
