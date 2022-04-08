package com.example.resume_app.ui.your_resume;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.resume_app.databinding.FragmentYourResumeBinding;

public class YourResumeFragment extends Fragment {

    private YourResumeViewModel yourResumeViewModel;
    private FragmentYourResumeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        yourResumeViewModel =
                new ViewModelProvider(this).get(YourResumeViewModel.class);

        binding = FragmentYourResumeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textYourResume;
        yourResumeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}