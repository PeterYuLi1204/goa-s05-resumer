package com.example.resume_app.data_model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Skill {
    public String skillName;
    public boolean selected;

    public Skill(String skillName) {
        this.skillName = skillName;
        this.selected = false;
    }

    public Skill() {}

    @NonNull
    public String toString() {
        return "<p>" + skillName + "</p>";
    }
}
