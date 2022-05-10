package com.example.resume_app.your_resumes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.resume_app.R;
import com.example.resume_app.MainActivity;
import com.example.resume_app.resume_editor.ResumeEditorActivity;

import java.util.ArrayList;

/**
 * Home for resume-related actions including creating and downloading resumes.
 */
public class YourResumesActivity extends AppCompatActivity implements YourResumesRecyclerAdapter.IClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_resumes);

        connectXml();
    }

    void connectXml() {
        ArrayList<String> data = new ArrayList<>();

        // TODO: load saved resumes
        data.add("PLACEHOLDER ITEM 1");
        data.add("PLACEHOLDER ITEM 2");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new YourResumesRecyclerAdapter(this, data, this));

        Button buttonPlus = findViewById(R.id.button_create_resume);
        buttonPlus.setOnClickListener(view -> startActivity(new Intent(this, ResumeEditorActivity.class)));

        Button buttonProfile = findViewById(R.id.button_profile);
        buttonProfile.setOnClickListener(view -> startActivity(new Intent(this, MainActivity.class)));
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("FILE_NAME", "PLACEHOLDER_TEXT");
        startActivity(intent);
    }
}