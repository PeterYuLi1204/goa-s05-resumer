package com.example.resume_app.resume_editor;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
        // Adding the fragment to the activity

        Fragment addStuffFragment = new AddStuffFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frame, addStuffFragment);

        transaction.commit();

    }
}
