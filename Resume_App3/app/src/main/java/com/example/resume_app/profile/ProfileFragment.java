package com.example.resume_app.profile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.resume_app.R;
import com.example.resume_app.data_model.Award;
import com.example.resume_app.data_model.Certification;
import com.example.resume_app.data_model.Education;
import com.example.resume_app.data_model.Experience;
import com.example.resume_app.data_model.Skill;
import com.example.resume_app.data_model.UserData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    public static final String ID = "PROFILE";

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
    Dialog confirmEraseProgressDialog;
    Dialog confirmEraseCardDialog;

    TextView nameTextView;
    TextView currentJobTextView;
    TextView emailTextView;
    TextView phoneNumberTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_tab, container, false);

        userData = loadFromJson("user_data");

        connectXml(view);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        saveToJson(userData, "user_data");
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

        editInformationDialog = new Dialog(getContext());
        editExperienceDialog = new Dialog(getContext());
        editAwardDialog = new Dialog(getContext());
        editEducationDialog = new Dialog(getContext());
        editCertificationDialog = new Dialog(getContext());
        editSkillsDialog = new Dialog(getContext());
        confirmEraseProgressDialog = new Dialog(getContext());
        confirmEraseCardDialog = new Dialog(getContext());

        for (Experience experience : userData.experience) {
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
        informationEditButton.setOnClickListener(v -> openEditInformationDialog());

        // Experience section of the profile

        ImageButton experienceAddButton = view.findViewById(R.id.experience_add_button);
        experienceAddButton.setOnClickListener(v -> {
            userData.experience.add(new Experience());
            createExperienceCard(userData.experience.get(userData.experience.size() - 1));
            openEditExperienceDialog(experienceLinearLayout.getChildAt(experienceLinearLayout.getChildCount() - 1), userData.experience.get(userData.experience.size() - 1), true);
        });

        // Awards section of the profile

        ImageButton awardsAddButton = view.findViewById(R.id.awards_add_button);
        awardsAddButton.setOnClickListener(v -> {
            userData.awards.add(new Award());
            createAwardCard(userData.awards.get(userData.awards.size() - 1));
            openEditAwardDialog(awardsLinearLayout.getChildAt(awardsLinearLayout.getChildCount() - 1), userData.awards.get(userData.awards.size() - 1), true);
        });

        // Education section of the profile

        ImageButton educationAddButton = view.findViewById(R.id.education_add_button);
        educationAddButton.setOnClickListener(v -> {
            userData.education.add(new Education());
            createEducationCard(userData.education.get(userData.education.size() - 1));
            openEditEducationDialog(educationLinearLayout.getChildAt(educationLinearLayout.getChildCount() - 1), userData.education.get(userData.education.size() - 1), true);
        });

        // Certifications section of the profile

        ImageButton certificationsAddButton = view.findViewById(R.id.certifications_add_button);
        certificationsAddButton.setOnClickListener(v -> {
            userData.certifications.add(new Certification());
            createCertificationCard(userData.certifications.get(userData.certifications.size() - 1));
            openEditCertificationDialog(certificationsLinearLayout.getChildAt(certificationsLinearLayout.getChildCount() - 1), userData.certifications.get(userData.certifications.size() - 1), true);
        });

        // Skills section of the profile

        ImageButton skillsAddButton = view.findViewById(R.id.skills_add_button);
        skillsAddButton.setOnClickListener(v -> {
            userData.skills.add(new Skill());
            createSkillCard(userData.skills.get(userData.skills.size() - 1));
            openEditSkillDialog(skillsLinearLayout.getChildAt(skillsLinearLayout.getChildCount() - 1), userData.skills.get(userData.skills.size() - 1), true);
        });
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
        jobStartEndDateTextView.setText(getString(R.string.start_date_to_end_date, experience.startDate, experience.endDate));

        ImageButton experienceDeleteButton = card.findViewById(R.id.experience_delete_button);
        experienceDeleteButton.setOnClickListener(v -> confirmEraseCardDialog(userData.experience, experience, experienceLinearLayout, card));

        card.setOnClickListener(v -> openEditExperienceDialog(card, experience, false));
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
        awardDeleteButton.setOnClickListener(v -> confirmEraseCardDialog(userData.awards, award, awardsLinearLayout, card));

        card.setOnClickListener(v -> openEditAwardDialog(card, award, false));
    }

    private void createEducationCard(Education education) {
        View card = getLayoutInflater().inflate(R.layout.item_education_cardview, educationLinearLayout, false);
        educationLinearLayout.addView(card);

        TextView schoolNameTextView = card.findViewById(R.id.school_name_textview);
        TextView educationDescriptionTextView = card.findViewById(R.id.education_description_textview);
        TextView educationStartEndDateTextView = card.findViewById(R.id.education_start_end_date_textview);

        schoolNameTextView.setText(education.schoolName);
        educationDescriptionTextView.setText(education.description);
        educationStartEndDateTextView.setText(getString(R.string.start_date_to_end_date, education.startDate, education.endDate));

        ImageButton educationDeleteButton = card.findViewById(R.id.education_delete_button);
        educationDeleteButton.setOnClickListener(v -> confirmEraseCardDialog(userData.education, education, educationLinearLayout, card));

        card.setOnClickListener(v -> openEditEducationDialog(card, education, false));
    }

    private void createCertificationCard(Certification certification) {
        View card = getLayoutInflater().inflate(R.layout.item_certifications_cardview, certificationsLinearLayout, false);
        certificationsLinearLayout.addView(card);

        TextView certificationTitleTextView = card.findViewById(R.id.certification_title_textview);
        TextView certificationIssuerNameTextView = card.findViewById(R.id.certification_issuer_name_textview);
        TextView issuedDateTextView = card.findViewById(R.id.certification_issued_date_textview);

        certificationTitleTextView.setText(certification.certificationTitle);
        certificationIssuerNameTextView.setText(certification.issuer);
        issuedDateTextView.setText(getString(R.string.issued_date_to_expiry_date, certification.issuedOn, certification.expiryDate));

        ImageButton certificationDeleteButton = card.findViewById(R.id.certification_delete_button);
        certificationDeleteButton.setOnClickListener(v -> confirmEraseCardDialog(userData.certifications, certification, certificationsLinearLayout, card));

        card.setOnClickListener(v -> openEditCertificationDialog(card, certification, false));
    }

    private void createSkillCard(Skill skill) {
        View card = getLayoutInflater().inflate(R.layout.item_skills_cardview, skillsLinearLayout, false);
        skillsLinearLayout.addView(card);

        TextView skillNameTextView = card.findViewById(R.id.skill_name_textview);

        skillNameTextView.setText(skill.skillName);

        ImageButton skillDeleteButton = card.findViewById(R.id.skill_delete_button);
        skillDeleteButton.setOnClickListener(v -> confirmEraseCardDialog(userData.skills, skill, skillsLinearLayout, card));

        card.setOnClickListener(v -> openEditSkillDialog(card, skill, false));
    }

    private void openEditInformationDialog() {
        editInformationDialog.setCancelable(false);
        editInformationDialog.setContentView(R.layout.dialog_edit_information);
        editInformationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        editInformationDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

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

        closeEditInformation.setOnClickListener(v -> {
            //editInformationDialog
        });

        saveButton.setOnClickListener(v -> {

            userData.username = editTextName.getText().toString();
            userData.currentJob = editTextCurrentJob.getText().toString();
            userData.phone = editTextPhoneNumber.getText().toString();
            userData.email = editTextEmail.getText().toString();

            if (TextUtils.isEmpty(userData.username)) {
                editTextName.setError("Please type in your name");
                return;
            }

            nameTextView.setText(userData.username);
            currentJobTextView.setText(userData.currentJob);
            phoneNumberTextView.setText(userData.phone);
            emailTextView.setText(userData.email);

            editInformationDialog.dismiss();
        });

        editInformationDialog.show();
    }

    private void openEditExperienceDialog(View card, Experience experience, boolean createNew) {
        editExperienceDialog.setCancelable(false);
        editExperienceDialog.setContentView(R.layout.dialog_edit_experience);
        editExperienceDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        editExperienceDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        TextView positionTitleTextView = card.findViewById(R.id.position_title_textview);
        TextView organizationNameTextView = card.findViewById(R.id.organization_name_textview);
        TextView experienceDescriptionTextView = card.findViewById(R.id.experience_description_textview);
        TextView jobStartEndDateTextView = card.findViewById(R.id.job_start_end_date_textview);

        TextView title = editExperienceDialog.findViewById(R.id.textView2);
        EditText editTextPositionTitle = editExperienceDialog.findViewById(R.id.edittext_position_title);
        EditText editTextOrganizationName = editExperienceDialog.findViewById(R.id.edittext_organization_name);
        EditText editExperienceDescription = editExperienceDialog.findViewById(R.id.edittext_experience_description);
        EditText editTextExperienceStartDate = editExperienceDialog.findViewById(R.id.edittext_experience_start_date);
        EditText editTextExperienceEndDate = editExperienceDialog.findViewById(R.id.edittext_experience_end_date);

        if (!createNew) {
            title.setText(R.string.edit_experience);
            editTextPositionTitle.setText(experience.jobPosition);
            editTextOrganizationName.setText(experience.companyName);
            editExperienceDescription.setText(experience.description);
            editTextExperienceStartDate.setText(experience.startDate);
            editTextExperienceEndDate.setText(experience.endDate);
        }

        Button writingTipsButton = editExperienceDialog.findViewById(R.id.button_not_sure);
        ImageButton closeEditExperience = editExperienceDialog.findViewById(R.id.close_edit_experience_button);
        Button saveButton = editExperienceDialog.findViewById(R.id.experience_save_button);

        writingTipsButton.setOnClickListener(v -> experienceWritingTipsDialog());
        closeEditExperience.setOnClickListener(v -> confirmEraseProgressDialog(editExperienceDialog, createNew, userData.experience, experience, experienceLinearLayout, card));

        saveButton.setOnClickListener(v -> {

            if (editTextPositionTitle.getText().length() == 0) {
                editTextPositionTitle.setError("Please type in the position title");
                return;
            }

            if (editTextOrganizationName.getText().length() == 0) {
                editTextOrganizationName.setError("Please type in the organization name");
                return;
            }

            if (editExperienceDescription.getText().length() == 0) {
                editExperienceDescription.setError("Please type in a short description");
                return;
            }

            if (editTextExperienceStartDate.getText().length() == 0) {
                editTextExperienceStartDate.setError("Please type in the start date");
                return;
            }

            if (editTextExperienceEndDate.getText().length() == 0) {
                editTextExperienceEndDate.setError("Please type in the end date");
                return;
            }

            experience.jobPosition = editTextPositionTitle.getText().toString();
            experience.companyName = editTextOrganizationName.getText().toString();
            experience.description = editExperienceDescription.getText().toString();
            experience.startDate = editTextExperienceStartDate.getText().toString();
            experience.endDate = editTextExperienceEndDate.getText().toString();

            positionTitleTextView.setText(experience.jobPosition);
            organizationNameTextView.setText(experience.companyName);
            experienceDescriptionTextView.setText(experience.description);
            jobStartEndDateTextView.setText(getString(R.string.start_date_to_end_date, experience.startDate, experience.endDate));

            editExperienceDialog.dismiss();
        });

        editExperienceDialog.show();
    }

    private void openEditAwardDialog(View card, Award award, boolean createNew) {
        editAwardDialog.setCancelable(false);
        editAwardDialog.setContentView(R.layout.dialog_edit_award);
        editAwardDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        editAwardDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        TextView awardTitleTextView = card.findViewById(R.id.award_title_textview);
        TextView awardIssuerNameTextView = card.findViewById(R.id.award_issuer_name_textview);
        TextView awardDescriptionTextView = card.findViewById(R.id.award_description_textview);
        TextView awardedDateTextView = card.findViewById(R.id.awarded_date_textview);

        TextView title = editAwardDialog.findViewById(R.id.textView2);
        EditText editTextAwardTitle = editAwardDialog.findViewById(R.id.edittext_award_title);
        EditText editTextAwardIssuerName = editAwardDialog.findViewById(R.id.edittext_award_issuer_name);
        EditText editTextAwardDescription = editAwardDialog.findViewById(R.id.edittext_award_description);
        EditText editTextAwardedDate = editAwardDialog.findViewById(R.id.edittext_awarded_date);

        if (!createNew) {
            title.setText(R.string.edit_award);
            editTextAwardTitle.setText(award.awardName);
            editTextAwardIssuerName.setText(award.issuer);
            editTextAwardDescription.setText(award.description);
            editTextAwardedDate.setText(award.dateAwarded);
        }

        ImageButton closeEditAward = editAwardDialog.findViewById(R.id.close_edit_award_button);
        Button saveButton = editAwardDialog.findViewById(R.id.award_save_button);

        closeEditAward.setOnClickListener(v -> confirmEraseProgressDialog(editAwardDialog, createNew, userData.awards, award, awardsLinearLayout, card));

        saveButton.setOnClickListener(v -> {
            if (editTextAwardTitle.getText().length() == 0) {
                editTextAwardTitle.setError("Please type in the award or honour title");
                return;
            }

            if (editTextAwardIssuerName.getText().length() == 0) {
                editTextAwardIssuerName.setError("Please type in the award or honour issuer's name");
                return;
            }

            if (editTextAwardDescription.getText().length() == 0) {
                editTextAwardDescription.setError("Please type in a short description");
                return;
            }

            if (editTextAwardedDate.getText().length() == 0) {
                editTextAwardedDate.setError("Please type in the date the award or honour was issued");
                return;
            }

            award.awardName = editTextAwardTitle.getText().toString();
            award.issuer = editTextAwardIssuerName.getText().toString();
            award.description = editTextAwardDescription.getText().toString();
            award.dateAwarded = editTextAwardedDate.getText().toString();

            awardTitleTextView.setText(award.awardName);
            awardIssuerNameTextView.setText(award.issuer);
            awardDescriptionTextView.setText(award.description);
            awardedDateTextView.setText(award.dateAwarded);

            editAwardDialog.dismiss();
        });

        editAwardDialog.show();
    }

    private void openEditEducationDialog(View card, Education education, boolean createNew) {
        editEducationDialog.setCancelable(false);
        editEducationDialog.setContentView(R.layout.dialog_edit_education);
        editEducationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        editEducationDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        TextView schoolNameTextView = card.findViewById(R.id.school_name_textview);
        TextView educationDescriptionTextView = card.findViewById(R.id.education_description_textview);
        TextView educationStartEndDateTextView = card.findViewById(R.id.education_start_end_date_textview);

        TextView title = editEducationDialog.findViewById(R.id.textView2);
        EditText editTextSchoolName = editEducationDialog.findViewById(R.id.edittext_school_name);
        EditText editTextEducationDescription = editEducationDialog.findViewById(R.id.edittext_education_description);
        EditText editTextEducationStartDate = editEducationDialog.findViewById(R.id.edittext_education_start_date);
        EditText editTextEducationEndDate = editEducationDialog.findViewById(R.id.edittext_education_end_date);

        if (!createNew) {
            title.setText(R.string.edit_education);
            editTextSchoolName.setText(education.schoolName);
            editTextEducationDescription.setText(education.description);
            editTextEducationStartDate.setText(education.startDate);
            editTextEducationEndDate.setText(education.endDate);
        }

        ImageButton closeEditEducation = editEducationDialog.findViewById(R.id.close_edit_education_button);
        Button saveButton = editEducationDialog.findViewById(R.id.education_save_button);

        closeEditEducation.setOnClickListener(v -> confirmEraseProgressDialog(editEducationDialog, createNew, userData.education, education, educationLinearLayout, card));

        saveButton.setOnClickListener(v -> {
            if (editTextSchoolName.getText().length() == 0) {
                editTextSchoolName.setError("Please type in the school name");
                return;
            }

            if (editTextEducationDescription.getText().length() == 0) {
                editTextEducationDescription.setError("Please type in a short description");
                return;
            }

            if (editTextEducationStartDate.getText().length() == 0) {
                editTextEducationStartDate.setError("Please type in the start date");
                return;
            }

            if (editTextEducationEndDate.getText().length() == 0) {
                editTextEducationEndDate.setError("Please type in the end date");
                return;
            }

            education.schoolName = editTextSchoolName.getText().toString();
            education.description = editTextEducationDescription.getText().toString();
            education.startDate = editTextEducationStartDate.getText().toString();
            education.endDate = editTextEducationEndDate.getText().toString();

            schoolNameTextView.setText(education.schoolName);
            educationDescriptionTextView.setText(education.description);
            educationStartEndDateTextView.setText(getString(R.string.start_date_to_end_date, education.startDate, education.endDate));

            editEducationDialog.dismiss();
        });

        editEducationDialog.show();
    }

    public void openEditCertificationDialog(View card, Certification certification, boolean createNew) {
        editCertificationDialog.setCancelable(false);
        editCertificationDialog.setContentView(R.layout.dialog_edit_certification);
        editCertificationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        editCertificationDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        TextView certificationTitleTextView = card.findViewById(R.id.certification_title_textview);
        TextView certificationIssuerNameTextView = card.findViewById(R.id.certification_issuer_name_textview);
        TextView issuedDateTextView = card.findViewById(R.id.certification_issued_date_textview);

        TextView title = editCertificationDialog.findViewById(R.id.textView2);
        EditText editTextCertificationTitle = editCertificationDialog.findViewById(R.id.edittext_certification_title);
        EditText editTextCertificationIssuerName = editCertificationDialog.findViewById(R.id.edittext_certification_issuer_name);
        EditText editTextIssuedDate = editCertificationDialog.findViewById(R.id.edittext_issued_date);
        EditText editTextExpiryDate = editCertificationDialog.findViewById(R.id.edittext_expiry_date);

        if (!createNew) {
            title.setText(R.string.edit_certification);
            editTextCertificationTitle.setText(certification.certificationTitle);
            editTextCertificationIssuerName.setText(certification.issuer);
            editTextIssuedDate.setText(certification.issuedOn);
            editTextExpiryDate.setText(certification.expiryDate);
        }

        ImageButton closeEditCertification = editCertificationDialog.findViewById(R.id.close_edit_certification_button);
        Button saveButton = editCertificationDialog.findViewById(R.id.certification_save_button);

        closeEditCertification.setOnClickListener(v -> confirmEraseProgressDialog(editCertificationDialog, createNew, userData.certifications, certification, certificationsLinearLayout, card));

        saveButton.setOnClickListener(v -> {
            if (editTextCertificationTitle.getText().length() == 0) {
                editTextCertificationTitle.setError("Please type in the certification or license title");
                return;
            }

            if (editTextCertificationIssuerName.getText().length() == 0) {
                editTextCertificationIssuerName.setError("Please type in the certification or license issuer's name");
                return;
            }

            if (editTextIssuedDate.getText().length() == 0) {
                editTextIssuedDate.setError("Please type in the issuing date");
                return;
            }

            if (editTextExpiryDate.getText().length() == 0) {
                editTextExpiryDate.setError("Please type in the expiry date");
                return;
            }

            certification.certificationTitle = editTextCertificationTitle.getText().toString();
            certification.issuer = editTextCertificationIssuerName.getText().toString();
            certification.issuedOn = editTextIssuedDate.getText().toString();
            certification.expiryDate = editTextExpiryDate.getText().toString();

            certificationTitleTextView.setText(certification.certificationTitle);
            certificationIssuerNameTextView.setText(certification.issuer);
            issuedDateTextView.setText(getString(R.string.issued_date_to_expiry_date, certification.issuedOn, certification.expiryDate));

            editCertificationDialog.dismiss();
        });

        editCertificationDialog.show();
    }

    public void openEditSkillDialog(View card, Skill skill, boolean createNew) {
        editSkillsDialog.setCancelable(false);
        editSkillsDialog.setContentView(R.layout.dialog_edit_skill);
        editSkillsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        editSkillsDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        TextView skillNameTextView = card.findViewById(R.id.skill_name_textview);

        TextView title = editSkillsDialog.findViewById(R.id.textView2);
        EditText editTextSkillName = editSkillsDialog.findViewById(R.id.edittext_skill_title);

        if (!createNew) {
            title.setText(R.string.edit_skill);
            editTextSkillName.setText(skill.skillName);
        }

        ImageButton closeEditSkill = editSkillsDialog.findViewById(R.id.close_edit_skill_button);
        Button saveButton = editSkillsDialog.findViewById(R.id.skill_save_button);

        closeEditSkill.setOnClickListener(v -> confirmEraseProgressDialog(editSkillsDialog, createNew, userData.skills, skill, skillsLinearLayout, card));

        saveButton.setOnClickListener(v -> {
            if (editTextSkillName.getText().length() == 0) {
                editTextSkillName.setError("Please type in the skill");
                return;
            }

            skill.skillName = editTextSkillName.getText().toString();
            skillNameTextView.setText(skill.skillName);

            editSkillsDialog.dismiss();
        });

        editSkillsDialog.show();
    }

    private void confirmEraseProgressDialog(Dialog originalDialog, Boolean createNew, ArrayList arrayListOfObject, Object objectToDelete, LinearLayout linearLayoutOfCard, View cardToDelete) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        View alert = getLayoutInflater().inflate(R.layout.dialog_confirm_erase_progress, null);
        dialogBuilder.setView(alert);
        AlertDialog dialog = dialogBuilder.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button continueButton = alert.findViewById(R.id.continue_working);
        continueButton.setOnClickListener(v -> dialog.dismiss());

        Button eraseButton = alert.findViewById(R.id.erase_progress);
        eraseButton.setOnClickListener(v -> {
            if (createNew) {
                arrayListOfObject.remove(objectToDelete);
                linearLayoutOfCard.removeView(cardToDelete);
            }
            originalDialog.dismiss();
            dialog.dismiss();
        });
    }

    private void confirmEraseCardDialog(ArrayList arrayListOfObject, Object objectToDelete, LinearLayout linearLayoutOfCard, View cardToDelete) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        View alert = getLayoutInflater().inflate(R.layout.dialog_confirm_erase_card, null);
        dialogBuilder.setView(alert);
        AlertDialog dialog = dialogBuilder.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button cancelDelete = alert.findViewById(R.id.cancel_delete);
        cancelDelete.setOnClickListener(v -> dialog.dismiss());

        Button eraseButton = alert.findViewById(R.id.erase_card);
        eraseButton.setOnClickListener(v -> {
            arrayListOfObject.remove(objectToDelete);
            linearLayoutOfCard.removeView(cardToDelete);
            dialog.dismiss();
        });
    }

    void experienceWritingTipsDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        View alert = getLayoutInflater().inflate(R.layout.dialog_experience_writing_tips, null);
        dialogBuilder.setView(alert);
        AlertDialog dialog = dialogBuilder.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button continueButton = alert.findViewById(R.id.button_continue);
        continueButton.setOnClickListener(v -> dialog.dismiss());
    }
}