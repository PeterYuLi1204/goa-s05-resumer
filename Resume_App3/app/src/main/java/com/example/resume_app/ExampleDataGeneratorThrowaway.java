package com.example.resume_app;

import com.example.resume_app.data_model.Award;
import com.example.resume_app.data_model.Certification;
import com.example.resume_app.data_model.Education;
import com.example.resume_app.data_model.Experience;
import com.example.resume_app.data_model.ResumeData;
import com.example.resume_app.data_model.Skill;
import com.example.resume_app.data_model.UserData;

import java.util.ArrayList;

/**
 * DELETE THIS CLASS AS SOON AS POSSIBLE.
 */
public class ExampleDataGeneratorThrowaway {

    public static UserData exampleUserData() {
        ArrayList<Award> awards = new ArrayList<>();
        awards.add(new Award("Honour roll", "McMath", "I get da good grades", "every year"));
        awards.add(new Award("Honour roll1", "McMath", "I get da good grades", "every year"));
        awards.add(new Award("Honour roll2", "McMath", "I get da good grades", "every year"));

        ArrayList<Certification> certifications = new ArrayList<>();
        certifications.add(new Certification("Certified awesome", "me, myself, and I", "everyday", "never"));
        certifications.add(new Certification("Certified awesome1", "me, myself, and I", "everyday", "never"));
        certifications.add(new Certification("Certified awesome2", "me, myself, and I", "everyday", "never"));
        certifications.add(new Certification("Certified awesome3", "me, myself, and I", "everyday", "never"));
        certifications.add(new Certification("Certified awesome4", "me, myself, and I", "everyday", "never"));

        ArrayList<Education> education = new ArrayList<>();
        education.add(new Education("McMath", "No, we don't only learn math", "2018", "2023"));
        education.add(new Education("McMath1", "No, we don't only learn math", "2018", "2023"));
        education.add(new Education("McMath2", "No, we don't only learn math", "2018", "2023"));

        ArrayList<Experience> experience = new ArrayList<>();
        experience.add(new Experience("Cashier", "Starbucks", "Worked from 9 to 5 doing stuff", "May 4, 2012", "Jan 30, 2020"));
        experience.add(new Experience("Cashier1", "Starbucks", "Worked from 9 to 5 doing stuff", "May 4, 2012", "Jan 30, 2020"));
        experience.add(new Experience("Cashier2", "Starbucks", "Worked from 9 to 5 doing stuff", "May 4, 2012", "Jan 30, 2020"));

        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(new Skill("determination"));
        skills.add(new Skill("determination1"));
        skills.add(new Skill("determination2"));
        skills.add(new Skill("determination3"));
        skills.add(new Skill("determination4"));

        return new UserData("Arthur", awards, certifications, education, experience, skills);
    }

    public static ResumeData exampleResumeData(UserData userData) {
        if (userData == null) {
            userData = exampleUserData();
        }
        return new ResumeData("test_file", "User Name", "Full stack developer at Telus. Full stack developer at Telus. Full stack developer at Telus. Full stack developer at Telus. Full stack developer at Telus. Full stack developer at Telus. Full stack developer at Telus. Full stack developer at Telus.", "user@name.com", "123-456-7890", "www.username.com", userData);
    }
}
