package com.example.resume_app.data_model;

public class Award implements DataObject {

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

    public String toHtmlString() {
        return "<p><b>" + awardName + "</b><br>" + description + "<br>" + issuer + " • " + dateAwarded + "</p>";
    }
}
