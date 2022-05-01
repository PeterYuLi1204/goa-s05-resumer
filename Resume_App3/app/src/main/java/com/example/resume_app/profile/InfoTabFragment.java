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
import com.example.resume_app.data_model.Certification;
import com.example.resume_app.data_model.Education;
import com.example.resume_app.data_model.Experience;
import com.example.resume_app.data_model.Skill;
import com.example.resume_app.data_model.UserData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

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
    LinearLayout educationLinearLayout;
    LinearLayout certificationsLinearLayout;
    LinearLayout skillsLinearLayout;

    Dialog editInformationDialog;
    Dialog editExperienceDialog;
    Dialog editAwardDialog;
    Dialog editEducationDialog;
    Dialog editCertificationDialog;
    Dialog editSkillsDialog;

    TextView nameTextView;
    TextView currentJobTextView;
    TextView emailTextView;
    TextView phoneNumberTextView;

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
        educationLinearLayout = view.findViewById(R.id.education_linear_layout);
        certificationsLinearLayout = view.findViewById(R.id.certifications_linear_layout);
        skillsLinearLayout = view.findViewById(R.id.skills_linear_layout);

        for (Experience experience: userData.experience) {
            createExperienceCard(experience);
        }

        for (Award award : userData.awards) {
            createAwardCard(award);
        }

        for (Education education : userData.education) {
            createEducationCard(education);
        }

        for (Certification certification : userData.certifications) {
            createCertificationCard(certification);
        }

        for (Skill skill : userData.skills) {
            createSkillCard(skill);
        }

        // Information section of the profile

        nameTextView = view.findViewById(R.id.name_textview);
        currentJobTextView = view.findViewById(R.id.current_job_textview);
        phoneNumberTextView = view.findViewById(R.id.phone_number_textview);
        emailTextView = view.findViewById(R.id.email_textview);

        nameTextView.setText(userData.username);
        currentJobTextView.setText(userData.currentJob);
        phoneNumberTextView.setText(userData.phone);
        emailTextView.setText(userData.email);

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

        ImageButton educationAddButton = view.findViewById(R.id.education_add_button);
        educationAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userData.education.add(new Education());
                createEducationCard(userData.education.get(userData.education.size() - 1));
                openEditEducationDialog(educationLinearLayout.getChildAt(educationLinearLayout.getChildCount() - 1), userData.education.get(userData.education.size() - 1));
            }
        });

        editEducationDialog = new Dialog(getContext());

        // Certifications section of the profile

        ImageButton certificationsAddButton = view.findViewById(R.id.certifications_add_button);
        certificationsAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userData.certifications.add(new Certification());
                createCertificationCard(userData.certifications.get(userData.certifications.size() - 1));
                openEditCertificationDialog(certificationsLinearLayout.getChildAt(certificationsLinearLayout.getChildCount() - 1), userData.certifications.get(userData.certifications.size() - 1));
            }
        });

        editCertificationDialog = new Dialog(getContext());

        // Skills section of the profile

        ImageButton skillsAddButton = view.findViewById(R.id.skills_add_button);
        skillsAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userData.skills.add(new Skill());
                createSkillCard(userData.skills.get(userData.skills.size() - 1));
                openEditSkillDialog(skillsLinearLayout.getChildAt(skillsLinearLayout.getChildCount() - 1), userData.skills.get(userData.skills.size() - 1));
            }
        });

        editSkillsDialog = new Dialog(getContext());
    }

    private void createExperienceCard(Experience experience) {
        View card = getLayoutInflater().inflate(R.layout.item_experience_cardview, experienceLinearLayout, false);
        experienceLinearLayout.addView(card);

        TextView positionTitleTextView = card.findViewById(R.id.position_title_textview);
        TextView organizationNameTextView = card.findViewById(R.id.organization_name_textview);
        TextView experienceDescriptionTextView = card.findViewById(R.id.experience_description_textview);
        TextView jobStartEndDateTextView = card.findViewById(R.id.job_start_end_date_textview);

        positionTitleTextView.setText(experience.jobPosition);
        organizationNameTextView.setText(experience.companyName);
        experienceDescriptionTextView.setText(experience.description);
        jobStartEndDateTextView.setText(experience.startDate + " - " + experience.endDate);

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

        TextView awardTitleTextView = card.findViewById(R.id.award_title_textview);
        TextView awardIssuerNameTextView = card.findViewById(R.id.award_issuer_name_textview);
        TextView awardDescriptionTextView = card.findViewById(R.id.award_description_textview);
        TextView awardedDateTextView = card.findViewById(R.id.awarded_date_textview);

        awardTitleTextView.setText(award.awardName);
        awardIssuerNameTextView.setText(award.issuer);
        awardDescriptionTextView.setText(award.description);
        awardedDateTextView.setText(award.dateAwarded);

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

    private void createEducationCard(Education education) {
        View card = getLayoutInflater().inflate(R.layout.item_education_cardview, educationLinearLayout, false);
        educationLinearLayout.addView(card);

        TextView schoolNameTextView = card.findViewById(R.id.school_name_textview);
        TextView educationDescriptionTextView = card.findViewById(R.id.education_description_textview);
        TextView educationStartEndDateTextView = card.findViewById(R.id.education_start_end_date_textview);

        schoolNameTextView.setText(education.schoolName);
        educationDescriptionTextView.setText(education.description);
        educationStartEndDateTextView.setText(education.startDate + " - " + education.endDate);

        ImageButton educationDeleteButton = card.findViewById(R.id.education_delete_button);
        educationDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userData.education.remove(education);
                educationLinearLayout.removeView(card);
            }
        });

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditEducationDialog(card, education);
            }
        });
    }

    private void createCertificationCard(Certification certification) {
        View card = getLayoutInflater().inflate(R.layout.item_certifications_cardview, certificationsLinearLayout, false);
        certificationsLinearLayout.addView(card);

        TextView certificationTitleTextView = card.findViewById(R.id.certification_title_textview);
        TextView certificationIssuerNameTextView = card.findViewById(R.id.certification_issuer_name_textview);
        TextView issuedDateTextView = card.findViewById(R.id.certification_issued_date_textview);
        TextView expiryDateTextView = card.findViewById(R.id.certification_expiry_date_textview);

        certificationTitleTextView.setText(certification.certificationTitle);
        certificationIssuerNameTextView.setText(certification.issuer);
        issuedDateTextView.setText("Issued: " + certification.issuedOn);
        expiryDateTextView.setText("Expires: " + certification.expiryDate);

        ImageButton certificationDeleteButton = card.findViewById(R.id.certification_delete_button);
        certificationDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userData.certifications.remove(certification);
                certificationsLinearLayout.removeView(card);
            }
        });

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditCertificationDialog(card, certification);
            }
        });
    }

    private void createSkillCard(Skill skill) {
        View card = getLayoutInflater().inflate(R.layout.item_skills_cardview, skillsLinearLayout, false);
        skillsLinearLayout.addView(card);

        TextView skillNameTextView = card.findViewById(R.id.skill_name_textview);

        skillNameTextView.setText(skill.skillName);

        ImageButton skillDeleteButton = card.findViewById(R.id.skill_delete_button);
        skillDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userData.skills.remove(skill);
                skillsLinearLayout.removeView(card);
            }
        });

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditSkillDialog(card, skill);
            }
        });
    }

    private void openEditInformationDialog() {
        editInformationDialog.setContentView(R.layout.dialog_edit_information);
        editInformationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText editTextName = editInformationDialog.findViewById(R.id.edittext_name);
        EditText editTextCurrentJob = editInformationDialog.findViewById(R.id.edittext_current_job);
        EditText editTextPhoneNumber = editInformationDialog.findViewById(R.id.edittext_phone_number);
        EditText editTextEmail = editInformationDialog.findViewById(R.id.edittext_email);

        editTextName.setText(userData.username);
        editTextCurrentJob.setText(userData.currentJob);
        editTextPhoneNumber.setText(userData.phone);
        editTextEmail.setText(userData.email);

        ImageButton closeEditInformation = editInformationDialog.findViewById(R.id.close_edit_information_button);
        Button saveButton = editInformationDialog.findViewById(R.id.information_save_button);

        closeEditInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editInformationDialog.dismiss();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userData.username = editTextName.getText().toString();
                userData.currentJob = editTextCurrentJob.getText().toString();
                userData.phone = editTextPhoneNumber.getText().toString();
                userData.email = editTextEmail.getText().toString();

                if(TextUtils.isEmpty(userData.username)) {
                    editTextName.setError("Please type in your name");
                    return;
                }

                nameTextView.setText(userData.username);
                currentJobTextView.setText(userData.currentJob);
                phoneNumberTextView.setText(userData.phone);
                emailTextView.setText(userData.email);

                editInformationDialog.dismiss();
            }
        });

        editInformationDialog.show();
    }

    private void openEditExperienceDialog(View card, Experience experience) {
        editExperienceDialog.setContentView(R.layout.dialog_edit_experience);
        editExperienceDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView positionTitleTextView = card.findViewById(R.id.position_title_textview);
        TextView organizationNameTextView = card.findViewById(R.id.organization_name_textview);
        TextView experienceDescriptionTextView = card.findViewById(R.id.experience_description_textview);
        TextView jobStartEndDateTextView = card.findViewById(R.id.job_start_end_date_textview);

        EditText editTextPositionTitle = editExperienceDialog.findViewById(R.id.edittext_position_title);
        EditText editTextOrganizationName = editExperienceDialog.findViewById(R.id.edittext_organization_name);
        EditText editExperienceDescription = editExperienceDialog.findViewById(R.id.edittext_experience_description);
        EditText editTextExperienceStartDate = editExperienceDialog.findViewById(R.id.edittext_experience_start_date);
        EditText editTextExperienceEndDate = editExperienceDialog.findViewById(R.id.edittext_experience_end_date);

        editTextPositionTitle.setText(experience.jobPosition);
        editTextOrganizationName.setText(experience.companyName);
        editExperienceDescription.setText(experience.description);
        editTextExperienceStartDate.setText(experience.startDate);
        editTextExperienceEndDate.setText(experience.endDate);

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
                organizationNameTextView.setText(experience.companyName);
                experienceDescriptionTextView.setText(experience.description);
                jobStartEndDateTextView.setText(experience.startDate + " - " + experience.endDate);

                editExperienceDialog.dismiss();
            }
        });

        editExperienceDialog.show();
    }

    private void openEditAwardDialog(View card, Award award) {
        editAwardDialog.setContentView(R.layout.dialog_edit_award);
        editAwardDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView awardTitleTextView = card.findViewById(R.id.award_title_textview);
        TextView awardIssuerNameTextView = card.findViewById(R.id.award_issuer_name_textview);
        TextView awardDescriptionTextView = card.findViewById(R.id.award_description_textview);
        TextView awardedDateTextView = card.findViewById(R.id.awarded_date_textview);

        EditText editTextAwardTitle = editAwardDialog.findViewById(R.id.edittext_award_title);
        EditText editTextAwardIssuerName = editAwardDialog.findViewById(R.id.edittext_award_issuer_name);
        EditText editTextAwardDescription = editAwardDialog.findViewById(R.id.edittext_award_description);
        EditText editTextAwardedDate = editAwardDialog.findViewById(R.id.edittext_awarded_date);

        editTextAwardTitle.setText(award.awardName);
        editTextAwardIssuerName.setText(award.issuer);
        editTextAwardDescription.setText(award.description);
        editTextAwardedDate.setText(award.dateAwarded);

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

                awardTitleTextView.setText(award.awardName);
                awardIssuerNameTextView.setText(award.issuer);
                awardDescriptionTextView.setText(award.description);
                awardedDateTextView.setText(award.dateAwarded);

                editAwardDialog.dismiss();
            }
        });

        editAwardDialog.show();
    }

    private void openEditEducationDialog(View card, Education education) {
        editEducationDialog.setContentView(R.layout.dialog_edit_education);
        editInformationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView schoolNameTextView = card.findViewById(R.id.school_name_textview);
        TextView educationDescriptionTextView = card.findViewById(R.id.education_description_textview);
        TextView educationStartEndDateTextView = card.findViewById(R.id.education_start_end_date_textview);

        EditText editTextSchoolName = editEducationDialog.findViewById(R.id.edittext_school_name);
        EditText editTextEducationDescription = editEducationDialog.findViewById(R.id.edittext_education_description);
        EditText editTextEducationStartDate = editEducationDialog.findViewById(R.id.edittext_education_start_date);
        EditText editTextEducationEndDate = editEducationDialog.findViewById(R.id.edittext_education_end_date);

        editTextSchoolName.setText(education.schoolName);
        editTextEducationDescription.setText(education.description);
        editTextEducationStartDate.setText(education.startDate);
        editTextEducationEndDate.setText(education.endDate);

        ImageButton closeEditEducation = editEducationDialog.findViewById(R.id.close_edit_education_button);
        Button saveButton = editEducationDialog.findViewById(R.id.education_save_button);

        closeEditEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editEducationDialog.dismiss();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                education.schoolName = editTextSchoolName.getText().toString();
                education.description = editTextEducationDescription.getText().toString();
                education.startDate = editTextEducationStartDate.getText().toString();
                education.endDate = editTextEducationEndDate.getText().toString();

                if(TextUtils.isEmpty(education.schoolName)) {
                    editTextSchoolName.setError("Please type in the school name");
                    return;
                }

                if(TextUtils.isEmpty(education.description)) {
                    editTextEducationDescription.setError("Please type in a short description");
                    return;
                }

                if(TextUtils.isEmpty(education.startDate)) {
                    editTextEducationStartDate.setError("Please type in the start date");
                    return;
                }

                if(TextUtils.isEmpty(education.endDate)) {
                    editTextEducationEndDate.setError("Please type in the end date");
                    return;
                }

                schoolNameTextView.setText(education.schoolName);
                educationDescriptionTextView.setText(education.description);
                educationStartEndDateTextView.setText(education.startDate + " - " + education.endDate);

                editEducationDialog.dismiss();
            }
        });

        editEducationDialog.show();
    }

    public void openEditCertificationDialog(View card, Certification certification) {
        editCertificationDialog.setContentView(R.layout.dialog_edit_certification);
        editCertificationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView certificationTitleTextView = card.findViewById(R.id.certification_title_textview);
        TextView certificationIssuerNameTextView = card.findViewById(R.id.certification_issuer_name_textview);
        TextView issuedDateTextView = card.findViewById(R.id.certification_issued_date_textview);
        TextView expiryDateTextView = card.findViewById(R.id.certification_expiry_date_textview);

        EditText editTextCertificationTitle = editCertificationDialog.findViewById(R.id.edittext_certification_title);
        EditText editTextCertificationIssuerName = editCertificationDialog.findViewById(R.id.edittext_certification_issuer_name);
        EditText editTextIssuedDate = editCertificationDialog.findViewById(R.id.edittext_issued_date);
        EditText editTextExpiryDate = editCertificationDialog.findViewById(R.id.edittext_expiry_date);

        editTextCertificationTitle.setText(certification.certificationTitle);
        editTextCertificationIssuerName.setText(certification.issuer);
        editTextIssuedDate.setText(certification.issuedOn);
        editTextExpiryDate.setText(certification.expiryDate);

        ImageButton closeEditCertification = editCertificationDialog.findViewById(R.id.close_edit_certification_button);
        Button saveButton = editCertificationDialog.findViewById(R.id.certification_save_button);

        closeEditCertification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCertificationDialog.dismiss();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                certification.certificationTitle = editTextCertificationTitle.getText().toString();
                certification.issuer = editTextCertificationIssuerName.getText().toString();
                certification.issuedOn = editTextIssuedDate.getText().toString();
                certification.expiryDate = editTextExpiryDate.getText().toString();

                if(TextUtils.isEmpty(certification.certificationTitle)) {
                    certificationTitleTextView.setError("Please type in the certification or license title");
                    return;
                }

                if(TextUtils.isEmpty(certification.issuer)) {
                    certificationIssuerNameTextView.setError("Please type in the certification or license issuer's name");
                    return;
                }

                if(TextUtils.isEmpty(certification.issuedOn)) {
                    issuedDateTextView.setError("Please type in the issuing date");
                    return;
                }

                if(TextUtils.isEmpty(certification.expiryDate)) {
                    expiryDateTextView.setError("Please type in the expiry date");
                    return;
                }

                certificationTitleTextView.setText(certification.certificationTitle);
                certificationIssuerNameTextView.setText(certification.issuer);
                issuedDateTextView.setText("Issued: " + certification.issuedOn);
                expiryDateTextView.setText("Expires: " + certification.expiryDate);

                editCertificationDialog.dismiss();
            }
        });

        editCertificationDialog.show();
    }

    public void openEditSkillDialog(View card, Skill skill) {
        editSkillsDialog.setContentView(R.layout.dialog_edit_skill);
        editSkillsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView skillNameTextView = card.findViewById(R.id.skill_name_textview);

        EditText editTextSkillName = editSkillsDialog.findViewById(R.id.edittext_skill_title);

        editTextSkillName.setText(skill.skillName);

        ImageButton closeEditSkill = editSkillsDialog.findViewById(R.id.close_edit_skill_button);
        Button saveButton = editSkillsDialog.findViewById(R.id.skill_save_button);

        closeEditSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editSkillsDialog.dismiss();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skill.skillName = editTextSkillName.getText().toString();

                if(TextUtils.isEmpty(skill.skillName)) {
                    editTextSkillName.setError("Please type in the skill");
                    return;
                }

                skillNameTextView.setText(skill.skillName);

                editSkillsDialog.dismiss();
            }
        });

        editSkillsDialog.show();
    }
}




