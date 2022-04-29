package com.example.resume_app.profile;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.resume_app.ExampleDataGeneratorThrowaway;
import com.example.resume_app.R;
import com.example.resume_app.data_model.UserData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InfoTabFragment extends Fragment {

    UserData userData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_tab, container, false);

        userData = loadFromJson("test_user_data");

        connectXml(view);

        return view;
    }

    /*
    TODO:
        once user input is confirmed, use it to create a [UserData] object.
        then, write the [UserData] to a JSON file like this. --arthur
     */
    void saveToJson(UserData data, String fileName) {
        File file = new File(requireContext().getExternalFilesDir(null), fileName + ".json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    UserData loadFromJson(String fileName) {
        File file = new File(requireContext().getExternalFilesDir(null), fileName + ".json");
        Gson gson = new Gson();
        UserData data = null;

        try (FileReader reader = new FileReader(file)) {
            data = gson.fromJson(reader, UserData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    void connectXml(View view) {

        // Information section of the profile

        nameTextView = view.findViewById(R.id.name_textview);
        nameTextView.setText(userData.username);

        ImageButton informationEditButton = view.findViewById(R.id.information_edit_button);
        informationEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditInformationDialog();
            }
        });

        editInformationDialog = new Dialog(getContext());

        // Experience section of the profile

        LinearLayout experienceLinearLayout = view.findViewById(R.id.experience_linear_layout);

        ImageButton experienceAddButton = view.findViewById(R.id.experience_add_button);
        experienceAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View card = getLayoutInflater().inflate(R.layout.item_experience_cardview, experienceLinearLayout, false);
                experienceLinearLayout.addView(card);

                ImageButton experienceDeleteButton = card.findViewById(R.id.experience_delete_button);
                TextView positionTitleTextview = card.findViewById(R.id.position_title_textview);

                experienceDeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        experienceLinearLayout.removeView(card);
                    }
                });

                card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        positionTitleTextview.setText("Hi");
                    }
                });

            }
        });

        // Awards section of the profile

        LinearLayout awardsLinearLayout = view.findViewById(R.id.awards_linear_layout);

        ImageButton awardsAddButton = view.findViewById(R.id.awards_add_button);
        awardsAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View card = getLayoutInflater().inflate(R.layout.item_awards_cardview, awardsLinearLayout, false);

                awardsLinearLayout.addView(card);
            }
        });

        // Education section of the profile

        LinearLayout educationLinearLayout = view.findViewById(R.id.education_linear_layout);

        ImageButton educationAddButton = view.findViewById(R.id.education_add_button);
        educationAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View card = getLayoutInflater().inflate(R.layout.item_education_cardview, educationLinearLayout, false);

                educationLinearLayout.addView(card);
            }
        });

        // Certifications section of the profile

        LinearLayout certificationsLinearLayout = view.findViewById(R.id.certifications_linear_layout);

        ImageButton certificationsAddButton = view.findViewById(R.id.certifications_add_button);
        certificationsAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View card = getLayoutInflater().inflate(R.layout.item_certifications_cardview, certificationsLinearLayout, false);

                certificationsLinearLayout.addView(card);
            }
        });

        // Skills section of the profile

        LinearLayout skillsLinearLayout = view.findViewById(R.id.skills_linear_layout);

        ImageButton skillsAddButton = view.findViewById(R.id.skills_add_button);
        skillsAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View card = getLayoutInflater().inflate(R.layout.item_skills_cardview, skillsLinearLayout, false);

                skillsLinearLayout.addView(card);
            }
        });


    }

    // Dialog to edit the information section of the profile

    Dialog editInformationDialog;
    TextView nameTextView;

    private void openEditInformationDialog() {
        editInformationDialog.setContentView(R.layout.dialog_edit_information);
        editInformationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageButton closeEditInformation = editInformationDialog.findViewById(R.id.close_edit_information_button);
        Button saveButton = editInformationDialog.findViewById(R.id.information_save_button);

        EditText editTextName = editInformationDialog.findViewById(R.id.edittext_name);
        EditText editTextJobPosition = editInformationDialog.findViewById(R.id.edittext_job_position);
        EditText editTextPhoneNumber = editInformationDialog.findViewById(R.id.edittext_phone_number);
        EditText editTextEmail = editInformationDialog.findViewById(R.id.edittext_email);

        closeEditInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editInformationDialog.dismiss();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameTextView.setText(userData.username);

                userData.username = editTextName.getText().toString();

                saveToJson(userData, "test_user_data");
                editInformationDialog.dismiss();
            }
        });

        editTextName.setText(userData.username);

        editInformationDialog.show();
    }
}

