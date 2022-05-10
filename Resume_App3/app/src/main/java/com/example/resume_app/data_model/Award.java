package com.example.resume_app.data_model;

import androidx.annotation.NonNull;

public class Award {
    public String awardName;
    public String issuer;
    public String description;
    public String dateAwarded;
    public boolean selected;

    public Award() {
        this.awardName = "";
        this.issuer = "";
        this.description = "";
        this.dateAwarded = "";
        selected = false;
    }

    @NonNull
    public String toString() {
        return "<p><b>" + awardName + "</b><br>" + description + "<br>" + issuer + " • " + dateAwarded + "</p>";
    }
}
