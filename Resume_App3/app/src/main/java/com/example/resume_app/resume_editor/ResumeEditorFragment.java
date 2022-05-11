package com.example.resume_app.resume_editor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import com.example.resume_app.data_model.ResumeData;
import com.example.resume_app.data_model.Skill;

import java.util.ArrayList;

public class ResumeEditorFragment extends Fragment {

    public static final String ID = "RESUME_EDITOR";

    ResumeData data = ResumeEditorActivity.resumeData;

    LinearLayout experienceLinearLayout;
    LinearLayout awardsLinearLayout;
    LinearLayout educationLinearLayout;
    LinearLayout certificationsLinearLayout;
    LinearLayout skillsLinearLayout;

    Dialog editIntroductionDialog;
    Dialog confirmEraseProgressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_resume_editor_lists, container, false);
        connectXml(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        experienceLinearLayout.removeAllViews();
        awardsLinearLayout.removeAllViews();
        educationLinearLayout.removeAllViews();
        certificationsLinearLayout.removeAllViews();
        skillsLinearLayout.removeAllViews();

        for (Experience experience : data.experience) {
            createExperienceCard(experience);
        }

        for (Award award : data.awards) {
            createAwardCard(award);
        }

        for (Education education : data.education) {
            createEducationCard(education);
        }

        for (Certification certification : data.certifications) {
            createCertificationCard(certification);
        }

        for (Skill skill : data.skills) {
            createSkillCard(skill);
        }
    }

    void connectXml(View view) {

        experienceLinearLayout = view.findViewById(R.id.experience_linear_layout);
        awardsLinearLayout = view.findViewById(R.id.awards_linear_layout);
        educationLinearLayout = view.findViewById(R.id.education_linear_layout);
        certificationsLinearLayout = view.findViewById(R.id.certifications_linear_layout);
        skillsLinearLayout = view.findViewById(R.id.skills_linear_layout);

        editIntroductionDialog = new Dialog(getContext());
        confirmEraseProgressDialog = new Dialog(getContext());

        View introductionCard = view.findViewById(R.id.introduction_cardview);
        TextView introductionTextView = introductionCard.findViewById(R.id.introduction_textview);
        introductionTextView.setText(data.introduction);
        introductionCard.setOnClickListener(v -> openEditIntroductionDialog(introductionCard, data.introduction));

        Button buttonPreview = view.findViewById(R.id.button_preview);
        buttonPreview.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ResumePreviewActivity.class);
            intent.putExtra("FILE_NAME", data.fileName);
            intent.putExtra("TEMPLATE_ID", R.layout.template_resume_classic);
            startActivity(intent);
        });

        // Experience section of the profile

        ImageButton experienceAddButton = view.findViewById(R.id.experience_add_button);
        experienceAddButton.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), AddStuffActivity.class);
            i.putExtra("CATEGORY", "EXPERIENCE");
            startActivity(i);
        });

        // Awards section of the profile

        ImageButton awardsAddButton = view.findViewById(R.id.awards_add_button);
        awardsAddButton.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), AddStuffActivity.class);
            i.putExtra("CATEGORY", "AWARDS");
            startActivity(i);
        });

        // Education section of the profile

        ImageButton educationAddButton = view.findViewById(R.id.education_add_button);
        educationAddButton.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), AddStuffActivity.class);
            i.putExtra("CATEGORY", "EDUCATION");
            startActivity(i);
        });

        // Certifications section of the profile

        ImageButton certificationsAddButton = view.findViewById(R.id.certifications_add_button);
        certificationsAddButton.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), AddStuffActivity.class);
            i.putExtra("CATEGORY", "CERTIFICATIONS");
            startActivity(i);
        });

        // Skills section of the profile

        ImageButton skillsAddButton = view.findViewById(R.id.skills_add_button);
        skillsAddButton.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), AddStuffActivity.class);
            i.putExtra("CATEGORY", "SKILLS");
            startActivity(i);
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
        experienceDeleteButton.setVisibility(View.GONE);
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
        awardDeleteButton.setVisibility(View.GONE);
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
        educationDeleteButton.setVisibility(View.GONE);
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
        certificationDeleteButton.setVisibility(View.GONE);
    }

    private void createSkillCard(Skill skill) {
        View card = getLayoutInflater().inflate(R.layout.item_skills_cardview, skillsLinearLayout, false);
        skillsLinearLayout.addView(card);

        TextView skillNameTextView = card.findViewById(R.id.skill_name_textview);

        skillNameTextView.setText(skill.skillName);

        ImageButton skillDeleteButton = card.findViewById(R.id.skill_delete_button);
        skillDeleteButton.setVisibility(View.GONE);
    }

    void openEditIntroductionDialog(View card, String introduction) {
        editIntroductionDialog.setCancelable(false);
        editIntroductionDialog.setContentView(R.layout.dialog_edit_introduction);
        editIntroductionDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        editIntroductionDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        TextView textView = card.findViewById(R.id.introduction_textview);

        EditText editText = editIntroductionDialog.findViewById(R.id.edittext_intro);

        editText.setText(introduction);

        Button tips = editIntroductionDialog.findViewById(R.id.button_not_sure);
        ImageButton close = editIntroductionDialog.findViewById(R.id.close_edit_intro_button);
        Button save = editIntroductionDialog.findViewById(R.id.intro_save_button);

        tips.setOnClickListener(v -> introductionWritingTipsDialog());
        close.setOnClickListener(v -> confirmEraseProgressDialog(editIntroductionDialog));

        save.setOnClickListener(v -> {
            if (editText.getText().length() == 0) {
                editText.setError(getString(R.string.error_fill_field));
                return;
            }

            data.introduction = editText.getText().toString();

            textView.setText(data.introduction);

            editIntroductionDialog.dismiss();
        });

        editIntroductionDialog.show();
    }

    private void confirmEraseProgressDialog(Dialog originalDialog) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        View alert = getLayoutInflater().inflate(R.layout.dialog_confirm_erase_progress, null);
        dialogBuilder.setView(alert);
        AlertDialog dialog = dialogBuilder.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button continueButton = alert.findViewById(R.id.continue_working);
        continueButton.setOnClickListener(v -> dialog.dismiss());

        Button eraseButton = alert.findViewById(R.id.erase_progress);
        eraseButton.setOnClickListener(v -> {
            originalDialog.dismiss();
            dialog.dismiss();
        });
    }

    void introductionWritingTipsDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        View alert = getLayoutInflater().inflate(R.layout.dialog_introduction_writing_tips, null);
        dialogBuilder.setView(alert);
        AlertDialog dialog = dialogBuilder.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button continueButton = alert.findViewById(R.id.button_continue);
        continueButton.setOnClickListener(v -> dialog.dismiss());
    }
}