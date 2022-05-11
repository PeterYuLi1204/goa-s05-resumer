package com.example.resume_app.data_model;

public class Certification implements DataObject {

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

    public String toHtmlString() {
        return "<p>" + certificationTitle + " • " + issuer + "<br>" + issuedOn + " - " + expiryDate + "</p>";
    }
}
