package com.example.resume_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.resume_app.profile.ProfileFragment;
import com.example.resume_app.your_resumes.YourResumesFragment;

/**
 * Home for profile-related actions including editing information and viewing post history.
 */
public class MainActivity extends AppCompatActivity {

    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectXml();
    }

    void connectXml() {

        Fragment yourResumesFragment = new YourResumesFragment();
        Fragment profileFragment = new ProfileFragment();

        // by default open the your resumes fragment
        openFragment(yourResumesFragment, YourResumesFragment.ID);

        TextView title = findViewById(R.id.title);

        Button buttonResumeBuilder = findViewById(R.id.button_resume_builder);
        buttonResumeBuilder.setOnClickListener(view -> {
            title.setText(R.string.title_your_resumes);
            openFragment(yourResumesFragment, YourResumesFragment.ID);
        });

        Button buttonProfile = findViewById(R.id.button_profile);
        buttonProfile.setOnClickListener(view -> {
            title.setText(R.string.title_profile);
            openFragment(profileFragment, ProfileFragment.ID);
        });

    }

    /**
     * Takes care of opening and/or lazily adding fragments to the activity.
     * @param fragment The fragment to open and/or add.
     * @param tag A String used to identify the fragment.
     */
    void openFragment(Fragment fragment, String tag) {
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