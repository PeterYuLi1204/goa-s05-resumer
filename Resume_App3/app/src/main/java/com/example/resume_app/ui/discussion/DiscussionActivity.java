package com.example.resume_app.ui.discussion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.resume_app.R;
import com.example.resume_app.ui.profile.ProfileActivity;
import com.example.resume_app.ui.resume_builder.ResumeBuilderActivity;

/**
 * Home for discussion-related actions including making, viewing, and replying to posts.
 */
public class DiscussionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        connectXml();
    }

    void connectXml() {
        View bottomNavigation = findViewById(R.id.bottom_navigation);

        Button buttonResumeBuilder = bottomNavigation.findViewById(R.id.button_resume_builder);
        buttonResumeBuilder.setOnClickListener(view -> {
            startActivity(new Intent(this, ResumeBuilderActivity.class));
        });

        Button buttonProfile = bottomNavigation.findViewById(R.id.button_profile);
        buttonProfile.setOnClickListener(view -> {
            startActivity(new Intent(this, ProfileActivity.class));
        });
    }
}