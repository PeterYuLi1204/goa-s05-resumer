package com.example.resume_app.ui.profile;

import android.content.Intent;
import android.os.Bundle;

import com.example.resume_app.R;
import com.example.resume_app.ui.discussion.DiscussionActivity;
import com.example.resume_app.ui.resume_builder.ResumeBuilderActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

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
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        CustomPagerAdapter customPagerAdapter = new CustomPagerAdapter(this);
        viewPager.setAdapter(customPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

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