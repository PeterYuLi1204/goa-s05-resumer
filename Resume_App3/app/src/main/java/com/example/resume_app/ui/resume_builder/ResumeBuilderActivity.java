package com.example.resume_app.ui.resume_builder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.resume_app.R;
import com.example.resume_app.ui.discussion.DiscussionActivity;
import com.example.resume_app.ui.profile.ProfileActivity;

import java.util.ArrayList;

/**
 * Home for resume-related actions including creating and downloading resumes.
 */
public class ResumeBuilderActivity extends AppCompatActivity implements ResumeBuilderRecyclerAdapter.IClickListener {

    ResumeBuilderRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_builder);

        connectXml();
    }

    void connectXml() {
        ArrayList<String> data = new ArrayList<>();

        // TODO: load saved resumes
        data.add("PLACEHOLDER ITEM 1");
        data.add("PLACEHOLDER ITEM 2");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ResumeBuilderRecyclerAdapter(this, data);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

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