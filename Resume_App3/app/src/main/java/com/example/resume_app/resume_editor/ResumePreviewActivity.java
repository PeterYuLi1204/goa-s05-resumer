package com.example.resume_app.resume_editor;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.resume_app.ExampleDataGeneratorThrowaway;
import com.example.resume_app.R;
import com.example.resume_app.data_model.Award;
import com.example.resume_app.data_model.Certification;
import com.example.resume_app.data_model.Education;
import com.example.resume_app.data_model.Experience;
import com.example.resume_app.data_model.ResumeData;
import com.example.resume_app.data_model.Skill;
import com.example.resume_app.data_model.UserData;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

/**
 * Displays a print preview of the resume given the appropriate data via Intent:
 *
 * - An int corresponding to the XML template to use,
 * - A String corresponding to the JSON file to load resume data from.
 *
 * Currently NOT null-safe! Incomplete ResumeData will cause a CRASH!
 */
public class ResumePreviewActivity extends AppCompatActivity {

    ResumeData resumeData;
    View template;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        setContentView(intent.getIntExtra("TEMPLATE_ID", R.layout.activity_resume_preview));

        // an experiment
        resumeData = ExampleDataGeneratorThrowaway.exampleResumeData(loadFromJson("test_user_data"));

        connectTemplate();
        connectXml();
    }

    /*
    TODO:
        eventually, have this load [ResumeData] instead of [UserData].
        this is just for debugging. --arthur
     */
    UserData loadFromJson(String fileName) {
        File file = new File(getExternalFilesDir(null), fileName + ".json");
        Gson gson = new Gson();
        UserData data = null;

        try (FileReader reader = new FileReader(file)) {
            data = gson.fromJson(reader, UserData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
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

        float scaleFactor = Resources.getSystem().getDisplayMetrics().widthPixels / 612f;
        template.setScaleX(scaleFactor);
        template.setScaleY(scaleFactor);
    }

    void connectXml() {
        ExtendedFloatingActionButton buttonDownload = findViewById(R.id.button_download);
        buttonDownload.setOnClickListener(view -> {
            File file = new File(getExternalFilesDir(null), resumeData.fileName + ".pdf");

            PdfDocument pdfDoc = new PdfDocument();
            PdfDocument.PageInfo pdfInfo = new PdfDocument.PageInfo.Builder(612, 792, 1).create();
            PdfDocument.Page pdf = pdfDoc.startPage(pdfInfo);
            template.draw(pdf.getCanvas());
            pdfDoc.finishPage(pdf);

            try {
                pdfDoc.writeTo(new FileOutputStream(file));
            } catch (Exception e) {
                e.printStackTrace();
            }

            pdfDoc.close();
        });
    }
}