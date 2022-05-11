package com.example.resume_app.data_model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Experience {
    public String jobPosition;
    public String companyName;
    public String description;
    public String startDate;
    public String endDate;
    public boolean selected;

    public Experience() {
        this.jobPosition = "";
        this.companyName = "";
        this.description = "";
        this.startDate = "";
        this.endDate = "";
        selected = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return jobPosition.equals(that.jobPosition) && companyName.equals(that.companyName) && description.equals(that.description) && startDate.equals(that.startDate) && endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobPosition, companyName, description, startDate, endDate);
    }

    @NonNull
    public String toString() {
        return "<p><b>" + jobPosition + " • " + companyName + "</b><br>" + description + "<br>" + startDate + " - " + endDate + "</p>";
    }
}
