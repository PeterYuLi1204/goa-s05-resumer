package com.example.resume_app.data_model;

import java.util.ArrayList;

public class ResumeData {
    public String fileName;
    public String username;
    public String introduction;
    public String email;
    public String phone;
    public String currentJob;
    public ArrayList<Award> awards;
    public ArrayList<Certification> certifications;
    public ArrayList<Education> education;
    public ArrayList<Experience> experience;
    public ArrayList<Skill> skills;

    public ResumeData(
            String fileName,
            String name,
            String introduction,
            String email,
            String phone,
            String currentJob,
            ArrayList<Award> awards,
            ArrayList<Certification> certifications,
            ArrayList<Education> education,
            ArrayList<Experience> experience,
            ArrayList<Skill> skills) {
        this.fileName = fileName;
        this.username = name;
        this.introduction = introduction;
        this.email = email;
        this.phone = phone;
        this.currentJob = currentJob;
        this.awards = awards;
        this.certifications = certifications;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
    }

    public ResumeData(
            String fileName,
            String introduction,
            UserData userData) {
        this.fileName = fileName;
        this.username = userData.username;
        this.introduction = introduction;
        this.email = userData.email;
        this.phone = userData.phone;
        this.currentJob = userData.currentJob;
        this.awards = userData.awards;
        this.certifications = userData.certifications;
        this.education = userData.education;
        this.experience = userData.experience;
        this.skills = userData.skills;
    }

    public ResumeData() {
        this.awards = new ArrayList<>();
        this.certifications = new ArrayList<>();
        this.education = new ArrayList<>();
        this.experience = new ArrayList<>();
        this.skills = new ArrayList<>();
    }
}
