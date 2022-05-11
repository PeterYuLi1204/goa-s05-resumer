package com.example.resume_app.data_model;

import androidx.annotation.NonNull;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certification that = (Certification) o;
        return certificationTitle.equals(that.certificationTitle) && issuer.equals(that.issuer) && issuedOn.equals(that.issuedOn) && expiryDate.equals(that.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(certificationTitle, issuer, issuedOn, expiryDate);
    }

    @NonNull
    public String toString() {
        return "<p>" + certificationTitle + " • " + issuer + "<br>" + issuedOn + " - " + expiryDate + "</p>";
    }
}
