package com.example.resume_app.data_model;

import androidx.annotation.NonNull;

public class Education {
    public String schoolName;
    public String description;
    public String startDate;
    public String endDate;
    public boolean selected;

    public Education(String schoolName, String description, String startDate, String endDate) {
        this.schoolName = schoolName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.selected = false;
    }

    public Education() {
        this.schoolName = "";
        this.description = "";
        this.startDate = "";
        this.endDate = "";
        selected = false;
    }

    @NonNull
    public String toString() {
        return "<p><b>" + schoolName + "</b><br>" + description + "<br>" + startDate + " - " + endDate + "</p>";
    }
}
