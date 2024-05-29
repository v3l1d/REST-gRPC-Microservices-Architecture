
package com.thesis.collectorservice.model.UserDataModels;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "idDocumentType",
    "idDocumentNumber",
    "idDocumentReleaseDate",
    "idDocumentExpireDate",
    "idDocumentReleaseEntity",
    "idDocumentReleaseProvince",
    "idDocumentReleaseCity",
    "healthInsuranceNumber"
})
@Generated("jsonschema2pojo")
public class DocumentInfo {

    @JsonProperty("idDocumentType")
    private String idDocumentType;
    @JsonProperty("idDocumentNumber")
    private String idDocumentNumber;
    @JsonProperty("idDocumentReleaseDate")
    private String idDocumentReleaseDate;
    @JsonProperty("idDocumentExpireDate")
    private String idDocumentExpireDate;
    @JsonProperty("idDocumentReleaseEntity")
    private IdDocumentReleaseEntity idDocumentReleaseEntity;
    @JsonProperty("idDocumentReleaseProvince")
    private IdDocumentReleaseProvince idDocumentReleaseProvince;
    @JsonProperty("idDocumentReleaseCity")
    private IdDocumentReleaseCity idDocumentReleaseCity;
    @JsonProperty("healthInsuranceNumber")
    private String healthInsuranceNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("idDocumentType")
    public String getIdDocumentType() {
        return idDocumentType;
    }

    @JsonProperty("idDocumentType")
    public void setIdDocumentType(String idDocumentType) {
        this.idDocumentType = idDocumentType;
    }

    public DocumentInfo withIdDocumentType(String idDocumentType) {
        this.idDocumentType = idDocumentType;
        return this;
    }

    @JsonProperty("idDocumentNumber")
    public String getIdDocumentNumber() {
        return idDocumentNumber;
    }

    @JsonProperty("idDocumentNumber")
    public void setIdDocumentNumber(String idDocumentNumber) {
        this.idDocumentNumber = idDocumentNumber;
    }

    public DocumentInfo withIdDocumentNumber(String idDocumentNumber) {
        this.idDocumentNumber = idDocumentNumber;
        return this;
    }

    @JsonProperty("idDocumentReleaseDate")
    public String getIdDocumentReleaseDate() {
        return idDocumentReleaseDate;
    }

    @JsonProperty("idDocumentReleaseDate")
    public void setIdDocumentReleaseDate(String idDocumentReleaseDate) {
        this.idDocumentReleaseDate = idDocumentReleaseDate;
    }

    public DocumentInfo withIdDocumentReleaseDate(String idDocumentReleaseDate) {
        this.idDocumentReleaseDate = idDocumentReleaseDate;
        return this;
    }

    @JsonProperty("idDocumentExpireDate")
    public String getIdDocumentExpireDate() {
        return idDocumentExpireDate;
    }

    @JsonProperty("idDocumentExpireDate")
    public void setIdDocumentExpireDate(String idDocumentExpireDate) {
        this.idDocumentExpireDate = idDocumentExpireDate;
    }

    public DocumentInfo withIdDocumentExpireDate(String idDocumentExpireDate) {
        this.idDocumentExpireDate = idDocumentExpireDate;
        return this;
    }

    @JsonProperty("idDocumentReleaseEntity")
    public IdDocumentReleaseEntity getIdDocumentReleaseEntity() {
        return idDocumentReleaseEntity;
    }

    @JsonProperty("idDocumentReleaseEntity")
    public void setIdDocumentReleaseEntity(IdDocumentReleaseEntity idDocumentReleaseEntity) {
        this.idDocumentReleaseEntity = idDocumentReleaseEntity;
    }

    public DocumentInfo withIdDocumentReleaseEntity(IdDocumentReleaseEntity idDocumentReleaseEntity) {
        this.idDocumentReleaseEntity = idDocumentReleaseEntity;
        return this;
    }

    @JsonProperty("idDocumentReleaseProvince")
    public IdDocumentReleaseProvince getIdDocumentReleaseProvince() {
        return idDocumentReleaseProvince;
    }

    @JsonProperty("idDocumentReleaseProvince")
    public void setIdDocumentReleaseProvince(IdDocumentReleaseProvince idDocumentReleaseProvince) {
        this.idDocumentReleaseProvince = idDocumentReleaseProvince;
    }

    public DocumentInfo withIdDocumentReleaseProvince(IdDocumentReleaseProvince idDocumentReleaseProvince) {
        this.idDocumentReleaseProvince = idDocumentReleaseProvince;
        return this;
    }

    @JsonProperty("idDocumentReleaseCity")
    public IdDocumentReleaseCity getIdDocumentReleaseCity() {
        return idDocumentReleaseCity;
    }

    @JsonProperty("idDocumentReleaseCity")
    public void setIdDocumentReleaseCity(IdDocumentReleaseCity idDocumentReleaseCity) {
        this.idDocumentReleaseCity = idDocumentReleaseCity;
    }

    public DocumentInfo withIdDocumentReleaseCity(IdDocumentReleaseCity idDocumentReleaseCity) {
        this.idDocumentReleaseCity = idDocumentReleaseCity;
        return this;
    }

    @JsonProperty("healthInsuranceNumber")
    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    @JsonProperty("healthInsuranceNumber")
    public void setHealthInsuranceNumber(String healthInsuranceNumber) {
        this.healthInsuranceNumber = healthInsuranceNumber;
    }

    public DocumentInfo withHealthInsuranceNumber(String healthInsuranceNumber) {
        this.healthInsuranceNumber = healthInsuranceNumber;
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

    public DocumentInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
