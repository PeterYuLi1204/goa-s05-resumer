package com.example.resume_app.your_resumes;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.resume_app.R;
import com.example.resume_app.data_model.Experience;
import com.example.resume_app.data_model.ResumeData;
import com.example.resume_app.data_model.UserData;
import com.example.resume_app.profile.ProfileActivity;
import com.example.resume_app.resume_editor.ResumeEditorActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Home for resume-related actions including creating and downloading resumes.
 */
public class YourResumesActivity extends AppCompatActivity implements YourResumesRecyclerAdapter.IClickListener {

    UserData userData;
    ResumeData resumeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_resumes);

        userData = loadUserFromJson("user_data");
        resumeData = new ResumeData();

        connectXml();
    }

    UserData loadUserFromJson(String fileName) {
        File file = new File(getApplicationContext().getExternalFilesDir(null), fileName);
        Gson gson = new Gson();
        UserData data = null;
        try (FileReader reader = new FileReader(file)) {
            data = gson.fromJson(reader, UserData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    void saveResumeToJson(ResumeData data, String fileName) {
        //TODO figure out file names
        File file = new File(getApplicationContext().getExternalFilesDir(null), fileName);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println("it actually worked");
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void saveUserToJson(UserData data, String fileName) {
        //TODO figure out file names
        File file = new File(getApplicationContext().getExternalFilesDir(null), fileName);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println("it actually worked");
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void connectXml() {

        // TODO: load saved resumes

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       try {
           recyclerView.setAdapter(new YourResumesRecyclerAdapter(this, userData.resumeFiles, this));
       } catch (NullPointerException e) {
           //show point to create resume button
       }
        Button buttonNewResume = findViewById(R.id.button_create_resume);
        buttonNewResume.setOnClickListener(view -> openCreateResumeDialog());

        Button buttonProfile = findViewById(R.id.button_profile);
        buttonProfile.setOnClickListener(view -> startActivity(new Intent(this, ProfileActivity.class)));
    }
    private void openCreateResumeDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View alert = getLayoutInflater().inflate(R.layout.dialog_create_resume, null);
        dialogBuilder.setView(alert);
        AlertDialog dialog = dialogBuilder.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageButton closeButton = alert.findViewById(R.id.close_create_resume_button);
        closeButton.setOnClickListener(v -> dialog.dismiss());

        EditText nameEditText = alert.findViewById(R.id.edittext_resume_name);
        Button saveButton = alert.findViewById(R.id.resume_name_save_button);
        saveButton.setOnClickListener(v -> {
            if (nameEditText.getText().length() == 0) {
                nameEditText.setError("Please type in a name");
                return;
            }
            userData.resumeFiles.add(nameEditText.getText().toString());
            saveUserToJson(userData, "user_data");
            saveResumeToJson(resumeData, nameEditText.getText().toString());
            startActivity(new Intent(this, ResumeEditorActivity.class));
            dialog.dismiss();
        });
    }
    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, ResumeEditorActivity.class);
        intent.putExtra("file name", userData.resumeFiles.get(position));
        startActivity(intent);
    }
}