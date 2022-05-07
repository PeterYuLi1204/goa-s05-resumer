package com.example.resume_app.profile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.resume_app.R;
import com.example.resume_app.your_resumes.YourResumesActivity;

/**
 * Home for profile-related actions including editing information and viewing post history.
 */
public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        connectXml();
    }

    void connectXml() {

        // Adding the fragment to the activity

        Fragment infoTabFragment = new InfoTabFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frame, infoTabFragment);
        // transaction.addToBackStack(null);

        transaction.commit();

        Button buttonResumeBuilder = findViewById(R.id.button_resume_builder);
        buttonResumeBuilder.setOnClickListener(view -> startActivity(new Intent(this, YourResumesActivity.class)));
    }
}