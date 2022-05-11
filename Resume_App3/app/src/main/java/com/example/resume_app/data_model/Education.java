package com.example.resume_app.data_model;

public class Education implements DataObject {

    public String schoolName;
    public String description;
    public String startDate;
    public String endDate;
    public boolean selected;

    public Education() {
        this.schoolName = "";
        this.description = "";
        this.startDate = "";
        this.endDate = "";
        selected = false;
    }

    public String toHtmlString() {
        return "<p><b>" + schoolName + "</b><br>" + description + "<br>" + startDate + " - " + endDate + "</p>";
    }
}
