package com.example.resume_app.resume_editor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.resume_app.R;
import com.example.resume_app.data_model.ResumeData;
import com.example.resume_app.data_model.UserData;
import com.example.resume_app.your_resumes.YourResumesActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class AddStuffActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stuff);

        Button temporary = findViewById(R.id.temporary);
        temporary.setOnClickListener(view -> {
            saveToJson(new ResumeData("hello"), "thing");
        });
    }
    void saveToJson(ResumeData data, String fileName) {
        File file = new File(getApplicationContext().getExternalFilesDir(null), fileName + ".json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println("it actually worked");
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    ResumeData loadFromJson(String fileName) {
        File file = new File(getApplicationContext().getExternalFilesDir(null), fileName + ".json");
        Gson gson = new Gson();
        ResumeData data = null;

        try (FileReader reader = new FileReader(file)) {
            data = gson.fromJson(reader, ResumeData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
