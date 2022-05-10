package com.example.resume_app.data_model;

import androidx.annotation.NonNull;

public class Award {
    public String awardName;
    public String issuer;
    public String description;
    public String dateAwarded;
    public boolean selected;

    public Award(String awardName, String issuer, String description, String dateAwarded) {
        this.awardName = awardName;
        this.issuer = issuer;
        this.description = description;
        this.dateAwarded = dateAwarded;
        this.selected = false;
    }

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
