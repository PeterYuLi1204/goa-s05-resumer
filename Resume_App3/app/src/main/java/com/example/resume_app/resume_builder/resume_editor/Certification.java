package com.example.resume_app.resume_builder.resume_editor;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Certification implements Serializable {
    String certificationTitle;
    String issuer;
    String issuedOn;
    String expiryDate;

    public Certification(String certificationTitle, String issuer, String issuedOn, String expiryDate) {
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
