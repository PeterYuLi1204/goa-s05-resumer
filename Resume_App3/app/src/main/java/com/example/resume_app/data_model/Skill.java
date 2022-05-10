package com.example.resume_app.data_model;

import androidx.annotation.NonNull;

public class Skill {
    public String skillName;
    public boolean selected;

    public Skill(String skillName) {
        this.skillName = skillName;
        this.selected = false;
    }

    public Skill() {
        this.skillName = "";
        selected = false;
    }

    @NonNull
    public String toString() {
        return "<p>" + skillName + "</p>";
    }
}
