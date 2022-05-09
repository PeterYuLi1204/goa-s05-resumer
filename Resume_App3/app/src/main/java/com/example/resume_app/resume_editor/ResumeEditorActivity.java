package com.example.resume_app.resume_editor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.resume_app.R;
import com.example.resume_app.your_resumes.YourResumesActivity;

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
        // Adding the fragment to the activity

        Fragment resumeEditorFragment = new ResumeEditorFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frame, resumeEditorFragment);

        transaction.commit();


        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> {
            startActivity(new Intent(this, YourResumesActivity.class));
        });

        Button buttonPreview = findViewById(R.id.button_preview);
        buttonPreview.setOnClickListener(view -> {
            // TODO: pass user input for render
            startActivity(new Intent(this, ResumePreviewActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }
}



