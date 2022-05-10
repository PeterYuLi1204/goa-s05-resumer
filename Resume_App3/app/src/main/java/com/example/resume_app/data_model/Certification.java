package com.example.resume_app.data_model;

import androidx.annotation.NonNull;

public class Certification {
    public String certificationTitle;
    public String issuer;
    public String issuedOn;
    public String expiryDate;
    public boolean selected;

    public Certification() {
        this.certificationTitle = "";
        this.issuer = "";
        this.issuedOn = "";
        this.expiryDate = "";
        selected = false;
    }

    @NonNull
    public String toString() {
        return "<p>" + certificationTitle + " • " + issuer + "<br>" + issuedOn + " - " + expiryDate + "</p>";
    }
}
