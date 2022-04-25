package com.example.resume_app.data_model;

import java.io.Serializable;
import java.util.ArrayList;

public class ResumeData {
    public String fileName;
    public String name;
    public String introduction;
    public String email;
    public String phone;
    public String website;
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
            String website,
            ArrayList<Award> awards,
            ArrayList<Certification> certifications,
            ArrayList<Education> education,
            ArrayList<Experience> experience,
            ArrayList<Skill> skills) {
        this.fileName = fileName;
        this.name = name;
        this.introduction = introduction;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.awards = awards;
        this.certifications = certifications;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
    }

    public ResumeData(
            String fileName,
            String name,
            String introduction,
            String email,
            String phone,
            String website,
            UserData userData) {
        this.fileName = fileName;
        this.name = name;
        this.introduction = introduction;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.awards = userData.awards;
        this.certifications = userData.certifications;
        this.education = userData.education;
        this.experience = userData.experience;
        this.skills = userData.skills;
    } //even even more test
}
