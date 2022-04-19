package com.example.resume_app.resume_builder;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.resume_app.R;
import com.example.resume_app.resume_builder.resume_editor.Award;
import com.example.resume_app.resume_builder.resume_editor.Certification;
import com.example.resume_app.resume_builder.resume_editor.Education;
import com.example.resume_app.resume_builder.resume_editor.Experience;
import com.example.resume_app.resume_builder.resume_editor.Skill;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Displays a print preview of the resume given the appropriate data.
 * Currently NOT null-safe! Incomplete ResumeData will cause a CRASH!
 */
public class ResumePreviewActivity extends AppCompatActivity {

    DisplayMetrics display = Resources.getSystem().getDisplayMetrics();

    ResumeData resumeData;
    View template;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_preview);

        fudgeResumeData();
        // resumeData = ResumeData.loadFrom(getIntent().getStringExtra("FILE_NAME"));

        connectTemplate();
        connectXml();
    }

    void connectTemplate() {
        // Regrettable...
        template = findViewById(R.id.template);
        TextView textHeaderName = findViewById(R.id.resume_header_name);
        TextView textIntroduction = findViewById(R.id.resume_introduction);
        TextView textEmail = findViewById(R.id.resume_email);
        TextView textPhone = findViewById(R.id.resume_phone);
        TextView textWebsite = findViewById(R.id.resume_website);
        TextView textHeaderExperience = findViewById(R.id.resume_header_experience);
        TextView textExperience = findViewById(R.id.resume_experience);
        TextView textHeaderEducation = findViewById(R.id.resume_header_education);
        TextView textEducation = findViewById(R.id.resume_education);
        TextView textHeaderCertifications = findViewById(R.id.resume_header_certifications);
        TextView textCertifications = findViewById(R.id.resume_certifications);
        TextView textHeaderAwards = findViewById(R.id.resume_header_awards);
        TextView textAwards = findViewById(R.id.resume_awards);
        TextView textHeaderSkills = findViewById(R.id.resume_header_skills);
        TextView textSkills = findViewById(R.id.resume_skills);

        textHeaderName.setText(resumeData.name);
        textIntroduction.setText(resumeData.introduction);
        textEmail.setText(resumeData.email);
        textPhone.setText(resumeData.phone);
        textWebsite.setText(resumeData.website);

        if (resumeData.experience.size() > 0) {
            StringBuilder s = new StringBuilder();
            for (Experience e : resumeData.experience) {
                s.append(e.toString());
            }
            textExperience.setText(Html.fromHtml(s.toString()));
        } else {
            textHeaderExperience.setVisibility(View.GONE);
            textExperience.setVisibility(View.GONE);
        }

        if (resumeData.education.size() > 0) {
            StringBuilder s = new StringBuilder();
            for (Education e : resumeData.education) {
                s.append(e.toString());
            }
            textEducation.setText(Html.fromHtml(s.toString()));
        } else {
            textHeaderEducation.setVisibility(View.GONE);
            textEducation.setVisibility(View.GONE);
        }

        if (resumeData.certifications.size() > 0) {
            StringBuilder s = new StringBuilder();
            for (Certification e : resumeData.certifications) {
                s.append(e.toString());
            }
            textCertifications.setText(Html.fromHtml(s.toString()));
        } else {
            textHeaderCertifications.setVisibility(View.GONE);
            textCertifications.setVisibility(View.GONE);
        }

        if (resumeData.awards.size() > 0) {
            StringBuilder s = new StringBuilder();
            for (Award e : resumeData.awards) {
                s.append(e.toString());
            }
            textAwards.setText(Html.fromHtml(s.toString()));
        } else {
            textHeaderAwards.setVisibility(View.GONE);
            textAwards.setVisibility(View.GONE);
        }

        if (resumeData.skills.size() > 0) {
            StringBuilder s = new StringBuilder();
            for (Skill e : resumeData.skills) {
                s.append(e.toString());
            }
            textSkills.setText(Html.fromHtml(s.toString()));
        } else {
            textHeaderSkills.setVisibility(View.GONE);
            textSkills.setVisibility(View.GONE);
        }

        float scaleFactor = display.widthPixels / 612f;
        template.setScaleX(scaleFactor);
        template.setScaleY(scaleFactor);
    }

    void connectXml() {
        ExtendedFloatingActionButton buttonDownload = findViewById(R.id.button_download);
        buttonDownload.setOnClickListener(view -> {
            renderToPdf();
        });
    }

    void renderToPdf() {
        PdfDocument pdfDoc = new PdfDocument();
        PdfDocument.PageInfo pdfInfo = new PdfDocument.PageInfo.Builder(612, 792, 1).create();
        PdfDocument.Page pdf = pdfDoc.startPage(pdfInfo);
        template.draw(pdf.getCanvas());
        pdfDoc.finishPage(pdf);

        try {
            File file = new File(getExternalFilesDir(null), "testfile.pdf");
            pdfDoc.writeTo(new FileOutputStream(file));
            pdfDoc.close();
            System.out.println(file);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FAILED");
        }
    }

    // 7 years bad luck for this
    void fudgeResumeData() {
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

        resumeData = new ResumeData(
                "User Name",
                "Full stack developer at Telus. Full stack developer at Telus. Full stack developer at Telus. Full stack developer at Telus. Full stack developer at Telus. Full stack developer at Telus. Full stack developer at Telus. Full stack developer at Telus.",
                "user@name.com",
                "123-456-7890",
                "www.username.com",
                awards, certifications, education, experience, skills);
    }
}