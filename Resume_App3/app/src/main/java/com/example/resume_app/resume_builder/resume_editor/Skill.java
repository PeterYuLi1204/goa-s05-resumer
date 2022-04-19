package com.example.resume_app.resume_builder.resume_editor;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Skill implements Serializable {
    String skillName;

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    @NonNull
    public String toString() {
        return "<p>" + skillName + "</p>";
    }
}
