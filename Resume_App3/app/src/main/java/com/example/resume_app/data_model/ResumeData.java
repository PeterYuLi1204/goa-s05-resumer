package com.example.resume_app.data_model;

import com.example.resume_app.R;

import java.util.ArrayList;

public class ResumeData {
    public String fileName;
    public String introduction;
    public ArrayList<Award> awards;
    public ArrayList<Certification> certifications;
    public ArrayList<Education> education;
    public ArrayList<Experience> experience;
    public ArrayList<Skill> skills;

    public ResumeData(
            String fileName,
            String introduction,
            ArrayList<Award> awards,
            ArrayList<Certification> certifications,
            ArrayList<Education> education,
            ArrayList<Experience> experience,
            ArrayList<Skill> skills) {
        this.fileName = fileName;
        this.introduction = introduction;
        this.awards = awards;
        this.certifications = certifications;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
    }

    public ResumeData(String fileName, String introduction) {
        this.fileName = fileName;
        this.introduction = introduction;
        this.awards = new ArrayList<>();
        this.certifications = new ArrayList<>();
        this.education = new ArrayList<>();
        this.experience = new ArrayList<>();
        this.skills = new ArrayList<>();
    }
}
