package com.example.resume_app.data_model;

import java.util.ArrayList;

public class UserData {
    public String username;
    public String email;
    public String phone;
    public String currentJob;
    public ArrayList<Award> awards;
    public ArrayList<Certification> certifications;
    public ArrayList<Education> education;
    public ArrayList<Experience> experience;
    public ArrayList<Skill> skills;

    public UserData(
            String username,
            String email,
            String phone,
            String currentJob,
            ArrayList<Award> awards,
            ArrayList<Certification> certifications,
            ArrayList<Education> education,
            ArrayList<Experience> experience,
            ArrayList<Skill> skills) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.currentJob = currentJob;
        this.awards = awards;
        this.certifications = certifications;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
    }
}
