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
    public ArrayList<String> resumeFiles;

    public UserData(
            String username,
            String email,
            String phone,
            String currentJob,
            ArrayList<Award> awards,
            ArrayList<Certification> certifications,
            ArrayList<Education> education,
            ArrayList<Experience> experience,
            ArrayList<Skill> skills,
            ArrayList<String> resumeFiles) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.currentJob = currentJob;
        this.awards = awards;
        this.certifications = certifications;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
        this.resumeFiles = resumeFiles;
    }

    public UserData() {
        this.username = "";
        this.email = "";
        this.phone = "";
        this.currentJob = "";
        this.awards = new ArrayList<>();
        this.certifications = new ArrayList<>();
        this.education = new ArrayList<>();
        this.experience = new ArrayList<>();
        this.skills = new ArrayList<>();
        this.resumeFiles = new ArrayList<>();
    }
}
