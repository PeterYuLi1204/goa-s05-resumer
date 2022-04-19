package com.example.resume_app.resume_builder.resume_editor;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Award implements Serializable {
    String awardName;
    String issuer;
    String description;
    String dateAwarded;

    public Award(String awardName, String issuer, String description, String dateAwarded) {
        this.awardName = awardName;
        this.issuer = issuer;
        this.description = description;
        this.dateAwarded = dateAwarded;
    }

    @NonNull
    public String toString() {
        return "<p><b>" + awardName + "</b><br>" + description + "<br>" + issuer + " • " + dateAwarded + "</p>";
    }
}
