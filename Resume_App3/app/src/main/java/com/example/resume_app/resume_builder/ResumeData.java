package com.example.resume_app.resume_builder;

import com.example.resume_app.resume_builder.resume_editor.Award;
import com.example.resume_app.resume_builder.resume_editor.Certification;
import com.example.resume_app.resume_builder.resume_editor.Education;
import com.example.resume_app.resume_builder.resume_editor.Experience;
import com.example.resume_app.resume_builder.resume_editor.Skill;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ResumeData implements Serializable {
    String name;
    String introduction;
    String email;
    String phone;
    String website;

    // THESE NEED TO BE MADE SERIALIZABLE
    ArrayList<Award> awards;
    ArrayList<Certification> certifications;
    ArrayList<Education> education;
    ArrayList<Experience> experience;
    ArrayList<Skill> skills;

    public ResumeData
    (
        String name,
        String introduction,
        String email,
        String phone,
        String website,
        ArrayList<Award> awards,
        ArrayList<Certification> certifications,
        ArrayList<Education> education,
        ArrayList<Experience> experience,
        ArrayList<Skill> skills
    ) {
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

    // UNTESTED AND PROBABLY BROKEN --arthur

//    void saveAs(String fileName) {
//        try {
//            FileOutputStream fileOut = new FileOutputStream("/resume_data/" + fileName + ".ser");
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(this);
//            out.close();
//            fileOut.close();
//        } catch (Exception e) {
//            // do nothing
//        }
//    }
//
//    static ResumeData loadFrom(String fileName) throws IOException, ClassNotFoundException {
//        FileInputStream fileIn = new FileInputStream("/resume_data/" + fileName + ".ser");
//        ObjectInputStream in = new ObjectInputStream(fileIn);
//        ResumeData loaded = (ResumeData) in.readObject();
//        in.close();
//        fileIn.close();
//        return loaded;
//    }

    void save() {
        File ohGodWhatAmIDoing = new File("FileyThings");
        try {
            FileOutputStream fileOut = new FileOutputStream(ohGodWhatAmIDoing);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (Exception e) {
            // do nothing
        }
    }

    static ResumeData loadFrom(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("/resume_data/" + fileName + ".ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        ResumeData loaded = (ResumeData) in.readObject();
        in.close();
        fileIn.close();
        return loaded;
    }
}
