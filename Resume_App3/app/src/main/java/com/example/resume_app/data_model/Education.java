package com.example.resume_app.data_model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Education {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return schoolName.equals(education.schoolName) && description.equals(education.description) && startDate.equals(education.startDate) && endDate.equals(education.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolName, description, startDate, endDate);
    }

    @NonNull
    public String toString() {
        return "<p><b>" + schoolName + "</b><br>" + description + "<br>" + startDate + " - " + endDate + "</p>";
    }
}
