package com.thesis.collectorservice.model;


import java.time.LocalDate;


public class AdditionalInfo {

    private String job;

    private String gender;

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    private LocalDate dateOfBirth;

    private String province;

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

    public boolean isValid(){
        return !job.isEmpty() && !gender.isEmpty() && dateOfBirth!=null && !province.isEmpty();
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
