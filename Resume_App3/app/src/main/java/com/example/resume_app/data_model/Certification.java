package com.example.resume_app.data_model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Certification {
    public String certificationTitle;
    public String issuer;
    public String issuedOn;
    public String expiryDate;

    public Certification(String certificationTitle, String issuer, String issuedOn, String expiryDate) {
        this.certificationTitle = certificationTitle;
        this.issuer = issuer;
        this.issuedOn = issuedOn;
        this.expiryDate = expiryDate;
    }

    public Certification() {
        this.certificationTitle = certificationTitle;
        this.issuer = issuer;
        this.issuedOn = issuedOn;
        this.expiryDate = expiryDate;
    }


    @NonNull
    public String toString() {
        return "<p>" + certificationTitle + " • " + issuer + "<br>" + issuedOn + " - " + expiryDate + "</p>";
    }
}
