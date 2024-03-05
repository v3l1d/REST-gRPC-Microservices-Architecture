package com.thesis.financialcalcservice.model;

public class Otp {
    private String MailOtp;
    private String SMSOtp;

    public String getMailOtp() {
        return MailOtp;
    }

    public void setMailOtp(String mailOtp) {
        MailOtp = mailOtp;
    }

    public String getSMSOtp() {
        return SMSOtp;
    }

    public void setSMSOtp(String SMSOtp) {
        this.SMSOtp = SMSOtp;
    }
}
