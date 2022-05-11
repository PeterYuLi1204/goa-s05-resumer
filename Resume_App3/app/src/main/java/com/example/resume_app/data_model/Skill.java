package com.example.resume_app.data_model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Skill {
    public String skillName;
    public boolean selected;

    public Skill() {
        this.skillName = "";
        selected = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return skillName.equals(skill.skillName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillName);
    }

    @NonNull
    public String toString() {
        return "<p>" + skillName + "</p>";
    }
}
