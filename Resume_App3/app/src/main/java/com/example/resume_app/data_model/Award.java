package com.example.resume_app.data_model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Award {
    public String awardName;
    public String issuer;
    public String description;
    public String dateAwarded;
    public boolean selected;

    public Award() {
        this.awardName = "";
        this.issuer = "";
        this.description = "";
        this.dateAwarded = "";
        selected = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Award award = (Award) o;
        return awardName.equals(award.awardName) && issuer.equals(award.issuer) && description.equals(award.description) && dateAwarded.equals(award.dateAwarded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(awardName, issuer, description, dateAwarded);
    }

    @NonNull
    public String toString() {
        return "<p><b>" + awardName + "</b><br>" + description + "<br>" + issuer + " • " + dateAwarded + "</p>";
    }
}
