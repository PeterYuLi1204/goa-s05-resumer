package com.example.resume_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.resume_app.data_model.UserData;
import com.example.resume_app.your_resumes.YourResumesActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this refreshes the test user data json when the app starts to avoid
        // permanently affecting the save while testing
        // TODO: delete this before submission! --arthur
        UserData data = ExampleDataGeneratorThrowaway.exampleUserData();
        File file = new File(getExternalFilesDir(null), "user_data.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        startActivity(new Intent(this, YourResumesActivity.class));
    }
}