package com.example.resume_app.data_model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Skill implements Serializable {
    public String skillName;

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    @NonNull
    public String toString() {
        return "<p>" + skillName + "</p>";
    }
}