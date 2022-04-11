package com.example.resume_app.ui.resume_builder.resume_editor;

public class Certifications {
    String certificationTitle;
    String issuer;
    String issuedOn;
    String expiryDate;

    public Certifications(String certificationTitle, String issuer, String issuedOn, String expiryDate) {
        this.certificationTitle = certificationTitle;
        this.issuer = issuer;
        this.issuedOn = issuedOn;
        this.expiryDate = expiryDate;
    }
}
