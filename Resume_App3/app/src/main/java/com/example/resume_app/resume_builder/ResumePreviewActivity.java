package com.example.resume_app.resume_builder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.resume_app.R;

/**
 * Displays a resume as a rendered PDF.
 */
public class ResumePreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_preview);

        // TODO: render pdf
    }
}