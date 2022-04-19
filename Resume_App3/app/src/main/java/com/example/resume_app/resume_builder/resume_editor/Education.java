package com.example.resume_app.resume_builder.resume_editor;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Education implements Serializable {
    String schoolName;
    String description;
    String startDate;
    String endDate;

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
