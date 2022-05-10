package com.example.resume_app.resume_editor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.resume_app.R;
import com.example.resume_app.data_model.Award;
import com.example.resume_app.data_model.Certification;
import com.example.resume_app.data_model.Education;
import com.example.resume_app.data_model.Experience;
import com.example.resume_app.data_model.Skill;
import com.example.resume_app.data_model.UserData;
import com.example.resume_app.profile.ProfileFragment;
import com.example.resume_app.your_resumes.YourResumesFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Takes user input for resume creation.
 */
public class ResumeEditorActivity extends AppCompatActivity {

    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_editor);

        connectXml();
    }

    void connectXml() {

        TextView title = findViewById(R.id.title);

        ImageButton buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(view -> onBackPressed());
    }

    /**
     * Takes care of opening and/or lazily adding fragments to the activity.
     * @param fragment The fragment to open and/or add.
     * @param tag A String used to identify the fragment.
     */
    void openFragment(Fragment fragment, String tag) {
        if (fragment == currentFragment) {
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);

        // hide whatever is on screen right now
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        // if the fragment has already been added to the activity, show it
        Fragment f = fragmentManager.findFragmentByTag(tag);
        if (f != null) {
            fragmentTransaction.show(f);
        }

        // otherwise add it to the activity
        else {
            fragmentTransaction.add(R.id.frame, fragment, tag);
        }

        fragmentTransaction.commitNow();
        currentFragment = fragment;
    }
}



