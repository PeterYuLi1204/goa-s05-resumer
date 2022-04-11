package com.example.resume_app.ui.resume_builder.resume_editor;

public class Experience {
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
}
