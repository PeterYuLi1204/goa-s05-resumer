package com.example.resume_app.profile;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
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
import com.example.resume_app.data_model.Award;
import com.example.resume_app.data_model.Experience;
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

    LinearLayout experienceLinearLayout;
    LinearLayout awardsLinearLayout;

    Dialog editInformationDialog;
    Dialog editExperienceDialog;
    Dialog editAwardDialog;

    TextView nameTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_tab, container, false);

        userData = loadFromJson("test_user_data");

        connectXml(view);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        saveToJson(userData, "test_user_data");
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
        experienceLinearLayout = view.findViewById(R.id.experience_linear_layout);
        awardsLinearLayout = view.findViewById(R.id.awards_linear_layout);

        for (Experience experience: userData.experience) {
            createExperienceCard(experience);
        }

        for (Award award : userData.awards) {
            createAwardCard(award);
        }

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

        ImageButton experienceAddButton = view.findViewById(R.id.experience_add_button);
        experienceAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userData.experience.add(new Experience());
                createExperienceCard(userData.experience.get(userData.experience.size() - 1));
                openEditExperienceDialog(experienceLinearLayout.getChildAt(experienceLinearLayout.getChildCount() - 1), userData.experience.get(userData.experience.size() - 1));
            }
        });

        editExperienceDialog = new Dialog(getContext());


        // Awards section of the profile

        ImageButton awardsAddButton = view.findViewById(R.id.awards_add_button);
        awardsAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userData.awards.add(new Award());
                createAwardCard(userData.awards.get(userData.awards.size() - 1));
                openEditAwardDialog(awardsLinearLayout.getChildAt(awardsLinearLayout.getChildCount() - 1), userData.awards.get(userData.awards.size() - 1));
            }
        });

        editAwardDialog = new Dialog(getContext());


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

    private void createExperienceCard(Experience experience) {
        View card = getLayoutInflater().inflate(R.layout.item_experience_cardview, experienceLinearLayout, false);
        experienceLinearLayout.addView(card);

        TextView positionTitleTextview = card.findViewById(R.id.position_title_textview);
        TextView organizationNameTextview = card.findViewById(R.id.organization_name_textview);
        TextView experienceDescriptionTextview = card.findViewById(R.id.experience_description_textview);
        TextView jobStartEndDateTextview = card.findViewById(R.id.job_start_end_date_textview);

        positionTitleTextview.setText(experience.jobPosition);
        organizationNameTextview.setText(experience.companyName);
        experienceDescriptionTextview.setText(experience.description);
        jobStartEndDateTextview.setText(experience.startDate + " - " + experience.endDate);

        ImageButton experienceDeleteButton = card.findViewById(R.id.experience_delete_button);
        experienceDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userData.experience.remove(experience);
                experienceLinearLayout.removeView(card);
            }
        });

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditExperienceDialog(card, experience);
            }
        });
    }

    private void createAwardCard(Award award) {
        View card = getLayoutInflater().inflate(R.layout.item_awards_cardview, awardsLinearLayout, false);
        awardsLinearLayout.addView(card);

        TextView awardTitleTextview = card.findViewById(R.id.award_title_textview);
        TextView awardIssuerNameTextview = card.findViewById(R.id.award_issuer_name_textview);
        TextView awardDescriptionTextview = card.findViewById(R.id.award_description_textview);
        TextView awardedDateTextview = card.findViewById(R.id.awarded_date_textview);

        awardTitleTextview.setText(award.awardName);
        awardIssuerNameTextview.setText(award.issuer);
        awardDescriptionTextview.setText(award.description);
        awardedDateTextview.setText(award.dateAwarded);

        ImageButton awardDeleteButton = card.findViewById(R.id.award_delete_button);
        awardDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userData.awards.remove(award);
                awardsLinearLayout.removeView(card);
            }
        });

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditAwardDialog(card, award);
            }
        });
    }

    // Dialog to edit the information section of the profile

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

                editInformationDialog.dismiss();
            }
        });

        editTextName.setText(userData.username);

        editInformationDialog.show();
    }

    private void openEditExperienceDialog(View card, Experience experience) {
        editExperienceDialog.setContentView(R.layout.dialog_edit_experience);
        editExperienceDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView positionTitleTextView = card.findViewById(R.id.position_title_textview);
        TextView organizationNameTextview = card.findViewById(R.id.organization_name_textview);
        TextView experienceDescriptionTextview = card.findViewById(R.id.experience_description_textview);
        TextView jobStartEndDateTextview = card.findViewById(R.id.job_start_end_date_textview);

        EditText editTextPositionTitle = editExperienceDialog.findViewById(R.id.edittext_position_title);
        EditText editTextOrganizationName = editExperienceDialog.findViewById(R.id.edittext_organization_name);
        EditText editExperienceDescription = editExperienceDialog.findViewById(R.id.edittext_experience_description);
        EditText editTextExperienceStartDate = editExperienceDialog.findViewById(R.id.edittext_experience_start_date);
        EditText editTextExperienceEndDate = editExperienceDialog.findViewById(R.id.edittext_experience_end_date);

        ImageButton closeEditExperience = editExperienceDialog.findViewById(R.id.close_edit_experience_button);
        Button saveButton = editExperienceDialog.findViewById(R.id.experience_save_button);

        closeEditExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editExperienceDialog.dismiss();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                experience.jobPosition = editTextPositionTitle.getText().toString();
                experience.companyName = editTextOrganizationName.getText().toString();
                experience.description = editExperienceDescription.getText().toString();
                experience.startDate = editTextExperienceStartDate.getText().toString();
                experience.endDate = editTextExperienceEndDate.getText().toString();

                if(TextUtils.isEmpty(experience.jobPosition)) {
                    editTextPositionTitle.setError("Please type in the position title");
                    return;
                }

                if(TextUtils.isEmpty(experience.companyName)) {
                    editTextOrganizationName.setError("Please type in the organization name");
                    return;
                }

                if(TextUtils.isEmpty(experience.description)) {
                    editExperienceDescription.setError("Please type in a short description");
                    return;
                }

                if(TextUtils.isEmpty(experience.startDate)) {
                    editTextExperienceStartDate.setError("Please type in the start date");
                    return;
                }

                if(TextUtils.isEmpty(experience.endDate)) {
                    editTextExperienceEndDate.setError("Please type in the end date");
                    return;
                }

                positionTitleTextView.setText(experience.jobPosition);
                organizationNameTextview.setText(experience.companyName);
                experienceDescriptionTextview.setText(experience.description);
                jobStartEndDateTextview.setText(experience.startDate + " - " + experience.endDate);

                editExperienceDialog.dismiss();
            }
        });

        editExperienceDialog.show();
    }

    private void openEditAwardDialog(View card, Award award) {
        editAwardDialog.setContentView(R.layout.dialog_edit_award);
        editAwardDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView awardTitleTextview = card.findViewById(R.id.award_title_textview);
        TextView awardIssuerNameTextview = card.findViewById(R.id.award_issuer_name_textview);
        TextView awardDescriptionTextview = card.findViewById(R.id.award_description_textview);
        TextView awardedDateTextview = card.findViewById(R.id.awarded_date_textview);

        EditText editTextAwardTitle = editAwardDialog.findViewById(R.id.edittext_award_title);
        EditText editTextAwardIssuerName = editAwardDialog.findViewById(R.id.edittext_award_issuer_name);
        EditText editTextAwardDescription = editAwardDialog.findViewById(R.id.edittext_award_description);
        EditText editTextAwardedDate = editAwardDialog.findViewById(R.id.edittext_awarded_date);

        ImageButton closeEditAward = editAwardDialog.findViewById(R.id.close_edit_award_button);
        Button saveButton = editAwardDialog.findViewById(R.id.award_save_button);

        closeEditAward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editAwardDialog.dismiss();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                award.awardName = editTextAwardTitle.getText().toString();
                award.issuer = editTextAwardIssuerName.getText().toString();
                award.description = editTextAwardDescription.getText().toString();
                award.dateAwarded = editTextAwardedDate.getText().toString();


                if(TextUtils.isEmpty(award.awardName)) {
                    editTextAwardTitle.setError("Please type in the award or honour title");
                    return;
                }

                if(TextUtils.isEmpty(award.issuer)) {
                    editTextAwardIssuerName.setError("Please type in the award or honour issuer's name");
                    return;
                }

                if(TextUtils.isEmpty(award.description)) {
                    editTextAwardDescription.setError("Please type in a short description");
                    return;
                }

                if(TextUtils.isEmpty(award.dateAwarded)) {
                    editTextAwardedDate.setError("Please type in the date the award or honour was issued");
                    return;
                }

                awardTitleTextview.setText(award.awardName);
                awardIssuerNameTextview.setText(award.issuer);
                awardDescriptionTextview.setText(award.description);
                awardedDateTextview.setText(award.dateAwarded);

                editAwardDialog.dismiss();
            }
        });

        editAwardDialog.show();
    }

}




