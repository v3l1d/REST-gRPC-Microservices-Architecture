package com.thesis.bankingservice.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.google.protobuf.Timestamp;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdditionalInfo {

    private String job;

    private String gender;


    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String province;

    public AdditionalInfo(){
    }

     public AdditionalInfo(String job, String gender,LocalDate dateOfBirth, String province) {
        this.job = job;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.province = province;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    /*public boolean isValid(){
    return !job.isEmpty() && !gender.isEmpty() && dateOfBirth!=null && !province.isEmpty();
    }*/

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    @Override
    public String toString() {
        return "{" +
                "\"job\": \"" + job + "\"," +
                "\"gender\": \"" + gender + "\"," +
                "\"dateOfBirth\": \"" + dateOfBirth + "\"," +
                "\"province\": \"" + province + "\"" +
                "}";
    }
}
