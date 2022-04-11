package com.example.resume_app.ui.resume_builder.resume_editor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.resume_app.R;
import com.example.resume_app.ui.resume_builder.ResumePreviewActivity;

import java.util.ArrayList;

/**
 * Takes user input for resume creation.
 */
public class ResumeEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_editor);

        connectXml();
    }

    void connectXml() {
        ArrayList<Experience> experiences = new ArrayList<>();
        experiences.add(new Experience("Cashier", "Starbucks","Worked from 9 to 5 doing stuff","May 4, 2012","Jan 30, 2020"));

        ArrayList<Education> education = new ArrayList<>();
        education.add(new Education("McMath", "No, we don't only learn math","2018","2023"));

        ArrayList<Certifications> certifications = new ArrayList<>();
        certifications.add(new Certifications("Certified awesome","me, myself, and I","everyday","never"));

        ArrayList<Awards> awards = new ArrayList<>();
        awards.add(new Awards("Honour roll","McMath","I get da good grades","every year"));

        ArrayList<Skills> skills = new ArrayList<>();
        skills.add(new Skills("determination"));

        RecyclerView experienceRecyclerView = findViewById(R.id.experience_recyclerview);
        experienceRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView educationRecyclerView = findViewById(R.id.education_recyclerview);
        educationRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView certificationsRecyclerView = findViewById(R.id.certifications_recyclerview);
        certificationsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView awardsRecyclerView = findViewById(R.id.awards_recyclerview);
        awardsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView skillsRecyclerView = findViewById(R.id.skills_recyclerview);
        skillsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ExperienceRecyclerAdapter experienceRecyclerAdapter = new ExperienceRecyclerAdapter(experiences);
        experienceRecyclerView.setAdapter(experienceRecyclerAdapter);

        EducationRecyclerAdapter educationRecyclerAdapter = new EducationRecyclerAdapter(education);
        educationRecyclerView.setAdapter(educationRecyclerAdapter);

        CertificationsRecyclerAdapter certificationsRecyclerAdapter = new CertificationsRecyclerAdapter(certifications);
        certificationsRecyclerView.setAdapter(certificationsRecyclerAdapter);

        AwardsRecyclerAdapter awardsRecyclerAdapter = new AwardsRecyclerAdapter(awards);
        awardsRecyclerView.setAdapter(awardsRecyclerAdapter);

        SkillsRecyclerViewAdapter skillsRecyclerViewAdapter = new SkillsRecyclerViewAdapter(skills);
        skillsRecyclerView.setAdapter(skillsRecyclerViewAdapter);

        Button buttonPreview = findViewById(R.id.button_preview);
        buttonPreview.setOnClickListener(view -> {
            // TODO: pass user input for render
            startActivity(new Intent(this, ResumePreviewActivity.class));
        });
    }
}



