package com.example.resume_app.resume_builder.resume_editor;

public class Certification {
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
}
