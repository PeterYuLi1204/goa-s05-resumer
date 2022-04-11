package com.example.resume_app.resume_builder.resume_editor;

public class Award {
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
}
