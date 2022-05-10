package com.example.resume_app.data_model;

import androidx.annotation.NonNull;

public class Experience {
    public String jobPosition;
    public String companyName;
    public String description;
    public String startDate;
    public String endDate;
    public boolean selected;

    public Experience(String jobPosition, String companyName, String description, String startDate, String endDate) {
        this.jobPosition = jobPosition;
        this.companyName = companyName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        selected = false;
    }

    public Experience() {
        this.jobPosition = "";
        this.companyName = "";
        this.description = "";
        this.startDate = "";
        this.endDate = "";
        selected = false;
    }

    @NonNull
    public String toString() {
        return "<p><b>" + jobPosition + " • " + companyName + "</b><br>" + description + "<br>" + startDate + " - " + endDate + "</p>";
    }
}
