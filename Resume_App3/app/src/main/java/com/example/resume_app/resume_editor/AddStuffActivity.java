package com.example.resume_app.resume_editor;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.resume_app.R;
import com.example.resume_app.data_model.Award;
import com.example.resume_app.data_model.Certification;
import com.example.resume_app.data_model.Education;
import com.example.resume_app.data_model.Experience;
import com.example.resume_app.data_model.ResumeData;
import com.example.resume_app.data_model.Skill;
import com.example.resume_app.data_model.UserData;
import com.example.resume_app.profile.InfoTabFragment;
import com.example.resume_app.your_resumes.YourResumesActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class AddStuffActivity extends AppCompatActivity {
    //screen where you add experiences

    static String thingToBeAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stuff);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            thingToBeAdded = extras.getString("thing");
        }

        connectXml();

    }

    void connectXml() {
        TextView title = findViewById(R.id.thing);
        if (thingToBeAdded.equals("experience")) {
            title.setText(R.string.header_add_experience);
        }

        if (thingToBeAdded.equals("award")) {
            title.setText(R.string.header_add_awards);
        }

        if (thingToBeAdded.equals("education")) {
            title.setText(R.string.header_add_education);
        }

        if (thingToBeAdded.equals("certification")) {
            title.setText(R.string.header_add_certifications);
        }

        if (thingToBeAdded.equals("skill")) {
            title.setText(R.string.header_add_skills);
        }

        Button addStuffButton = findViewById(R.id.add_thing_button);
        // Adding the fragment to the activity
        AddStuffFragment addStuffFragment = new AddStuffFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        addStuffFragment.bindButton(addStuffButton);

        transaction.replace(R.id.frame, addStuffFragment);

        transaction.commit();

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> startActivity(new Intent(this, ResumeEditorActivity.class)));

    }

}
