package com.example.resume_app.your_resumes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.resume_app.R;
import com.example.resume_app.discussion.DiscussionActivity;
import com.example.resume_app.profile.ProfileActivity;
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

        Button buttonPlus = findViewById(R.id.button_plus);
        buttonPlus.setOnClickListener(view -> {
            startActivity(new Intent(this, ResumeEditorActivity.class));
        });

        View bottomNavigation = findViewById(R.id.bottom_navigation);

        Button buttonDiscussion = bottomNavigation.findViewById(R.id.button_discussion);
        buttonDiscussion.setOnClickListener(view -> {
            startActivity(new Intent(this, DiscussionActivity.class));
        });

        Button buttonProfile = bottomNavigation.findViewById(R.id.button_profile);
        buttonProfile.setOnClickListener(view -> {
            startActivity(new Intent(this, ProfileActivity.class));
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        // TODO: "download pdf" modal
    }
}