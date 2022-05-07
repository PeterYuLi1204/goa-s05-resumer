package com.example.resume_app.data_model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Skill {
    public String skillName;

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    public Skill() {}

    @NonNull
    public String toString() {
        return "<p>" + skillName + "</p>";
    }
}
