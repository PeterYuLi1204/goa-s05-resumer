package com.example.resume_app.resume_builder.resume_editor;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Experience implements Serializable {
    String jobPosition;
    String companyName;
    String description;
    String startDate;
    String endDate;
    Boolean selected;

    public Experience(String jobPosition, String companyName, String description, String startDate, String endDate) {
        this.jobPosition = jobPosition;
        this.companyName = companyName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        selected = false;
    }

    @NonNull
    public String toString() {
        return "<p><b>" + jobPosition + " • " + companyName + "</b><br>" + description + "<br>" + startDate + " - " + endDate + "</p>";
    }
}
