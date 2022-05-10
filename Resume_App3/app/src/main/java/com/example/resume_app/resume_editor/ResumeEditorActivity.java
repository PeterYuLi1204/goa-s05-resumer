package com.example.resume_app.resume_editor;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
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

    static String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_editor);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fileName = extras.getString("file name");
        }

        connectXml();
    }

    void connectXml() {
        // Adding the fragment to the activity

        Fragment resumeEditorFragment = new ResumeEditorFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

//        Bundle bundle = new Bundle();
//        bundle.putString("file name", fileName);
//        resumeEditorFragment.setArguments(bundle);

        transaction.replace(R.id.frame, resumeEditorFragment);

        transaction.commit();


        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            View alert = getLayoutInflater().inflate(R.layout.dialog_confirm_erase_progress, null);
            dialogBuilder.setView(alert);
            AlertDialog dialog = dialogBuilder.show();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            Button continueButton = alert.findViewById(R.id.continue_working);
            continueButton.setOnClickListener(v -> dialog.dismiss());

            Button eraseButton = alert.findViewById(R.id.erase_progress);
            eraseButton.setOnClickListener(v -> {
                startActivity(new Intent(this, YourResumesActivity.class));
                dialog.dismiss();
            });
        });

        Button buttonPreview = findViewById(R.id.button_preview);
        buttonPreview.setOnClickListener(view -> {
            // TODO: pass user input for render
            startActivity(new Intent(this, ResumePreviewActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }
}



