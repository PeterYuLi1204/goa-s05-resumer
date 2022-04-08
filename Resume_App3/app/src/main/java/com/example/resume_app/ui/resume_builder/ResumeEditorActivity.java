package com.example.resume_app.ui.resume_builder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.resume_app.R;

public class ResumeEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_editor);

        connectXml();
    }

    void connectXml() {
        Button buttonPreview = findViewById(R.id.button_resume_editor_preview);
        buttonPreview.setOnClickListener(view -> {
            // TODO: render pdf from input
            startActivity(new Intent(this, ResumePreviewActivity.class));
        });
    }
}