package com.example.resume_app.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.resume_app.R;
import com.example.resume_app.discussion.DiscussionActivity;
import com.example.resume_app.resume_builder.ResumeBuilderActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

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
        ArrayList<ModelObject> tabs = new ArrayList<>();

        tabs.add(ModelObject.INFO);
        tabs.add(ModelObject.POSTS_COMMENTS);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ProfilePagerAdapter(this, tabs));

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        ImageButton introductionEditButton = findViewById(R.id.introduction_edit_button);
        introductionEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageButton experienceAddButton = findViewById(R.id.experience_add_button);
        experienceAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageButton awardsAddButton = findViewById(R.id.awards_add_button);
        awardsAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageButton educationAddButton = findViewById(R.id.education_add_button);
        educationAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageButton certificationsAddButton = findViewById(R.id.certifications_add_button);
        certificationsAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageButton skillsAddButton = findViewById(R.id.skills_add_button);
        skillsAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        View bottomNavigation = findViewById(R.id.bottom_navigation);

        Button buttonDiscussion = bottomNavigation.findViewById(R.id.button_discussion);
        buttonDiscussion.setOnClickListener(view -> {
            startActivity(new Intent(this, DiscussionActivity.class));
        });

        Button buttonResumeBuilder = bottomNavigation.findViewById(R.id.button_resume_builder);
        buttonResumeBuilder.setOnClickListener(view -> {
            startActivity(new Intent(this, ResumeBuilderActivity.class));
        });
    }
}