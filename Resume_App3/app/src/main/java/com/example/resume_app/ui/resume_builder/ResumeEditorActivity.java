package com.example.resume_app.ui.resume_builder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.resume_app.R;

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
        Button buttonPreview = findViewById(R.id.button_preview);
        buttonPreview.setOnClickListener(view -> {
            // TODO: pass user input for render
            startActivity(new Intent(this, ResumePreviewActivity.class));
        });
    }
}