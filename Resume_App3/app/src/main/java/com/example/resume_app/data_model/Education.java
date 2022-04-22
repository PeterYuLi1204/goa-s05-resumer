package com.example.resume_app.data_model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Education implements Serializable {
    public String schoolName;
    public String description;
    public String startDate;
    public String endDate;

    public Education(String schoolName, String description, String startDate, String endDate) {
        this.schoolName = schoolName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @NonNull
    public String toString() {
        return "<p><b>" + schoolName + "</b><br>" + description + "<br>" + startDate + " - " + endDate + "</p>";
    }
}
