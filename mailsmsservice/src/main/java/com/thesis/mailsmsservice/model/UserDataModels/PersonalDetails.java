
package com.thesis.mailsmsservice.model.UserDataModels;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "surname",
    "firstName",
    "gender",
    "birthCountry",
    "birthProvince",
    "birthCity",
    "birthDate",
    "taxCode",
    "email",
    "phone",
    "citizenship",
    "pep",
    "fcaEmployee"
})
@Generated("jsonschema2pojo")
public class PersonalDetails {

    @JsonProperty("surname")
    private String surname;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("birthCountry")
    private BirthCountry birthCountry;
    @JsonProperty("birthProvince")
    private BirthProvince birthProvince;
    @JsonProperty("birthCity")
    private BirthCity birthCity;
    @JsonProperty("birthDate")
    private String birthDate;
    @JsonProperty("taxCode")
    private String taxCode;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("citizenship")
    private Citizenship citizenship;
    @JsonProperty("pep")
    private boolean pep;
    @JsonProperty("fcaEmployee")
    private boolean fcaEmployee;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("surname")
    public String getSurname() {
        return surname;
    }

    @JsonProperty("surname")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public PersonalDetails withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public PersonalDetails withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    public PersonalDetails withGender(String gender) {
        this.gender = gender;
        return this;
    }

    @JsonProperty("birthCountry")
    public BirthCountry getBirthCountry() {
        return birthCountry;
    }

    @JsonProperty("birthCountry")
    public void setBirthCountry(BirthCountry birthCountry) {
        this.birthCountry = birthCountry;
    }

    public PersonalDetails withBirthCountry(BirthCountry birthCountry) {
        this.birthCountry = birthCountry;
        return this;
    }

    @JsonProperty("birthProvince")
    public BirthProvince getBirthProvince() {
        return birthProvince;
    }

    @JsonProperty("birthProvince")
    public void setBirthProvince(BirthProvince birthProvince) {
        this.birthProvince = birthProvince;
    }

    public PersonalDetails withBirthProvince(BirthProvince birthProvince) {
        this.birthProvince = birthProvince;
        return this;
    }

    @JsonProperty("birthCity")
    public BirthCity getBirthCity() {
        return birthCity;
    }

    @JsonProperty("birthCity")
    public void setBirthCity(BirthCity birthCity) {
        this.birthCity = birthCity;
    }

    public PersonalDetails withBirthCity(BirthCity birthCity) {
        this.birthCity = birthCity;
        return this;
    }

    @JsonProperty("birthDate")
    public String getBirthDate() {
        return birthDate;
    }

    @JsonProperty("birthDate")
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public PersonalDetails withBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    @JsonProperty("taxCode")
    public String getTaxCode() {
        return taxCode;
    }

    @JsonProperty("taxCode")
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public PersonalDetails withTaxCode(String taxCode) {
        this.taxCode = taxCode;
        return this;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    public PersonalDetails withEmail(String email) {
        this.email = email;
        return this;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PersonalDetails withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @JsonProperty("citizenship")
    public Citizenship getCitizenship() {
        return citizenship;
    }

    @JsonProperty("citizenship")
    public void setCitizenship(Citizenship citizenship) {
        this.citizenship = citizenship;
    }

    public PersonalDetails withCitizenship(Citizenship citizenship) {
        this.citizenship = citizenship;
        return this;
    }

    @JsonProperty("pep")
    public boolean isPep() {
        return pep;
    }

    @JsonProperty("pep")
    public void setPep(boolean pep) {
        this.pep = pep;
    }

    public PersonalDetails withPep(boolean pep) {
        this.pep = pep;
        return this;
    }

    @JsonProperty("fcaEmployee")
    public boolean isFcaEmployee() {
        return fcaEmployee;
    }

    @JsonProperty("fcaEmployee")
    public void setFcaEmployee(boolean fcaEmployee) {
        this.fcaEmployee = fcaEmployee;
    }

    public PersonalDetails withFcaEmployee(boolean fcaEmployee) {
        this.fcaEmployee = fcaEmployee;
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

    public PersonalDetails withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
