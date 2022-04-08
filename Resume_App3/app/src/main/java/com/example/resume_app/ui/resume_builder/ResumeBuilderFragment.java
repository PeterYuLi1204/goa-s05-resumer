package com.example.resume_app.ui.resume_builder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.resume_app.databinding.FragmentResumeBuilderBinding;

import java.util.ArrayList;

public class ResumeBuilderFragment extends Fragment implements ResumeBuilderRecyclerAdapter.IClickListener {

    ResumeBuilderRecyclerAdapter adapter;
    private FragmentResumeBuilderBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentResumeBuilderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        connectXml();

        return root;
    }

    void connectXml() {
        Button button = binding.buttonResumeBuilder;
        button.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), ResumeEditorActivity.class));
        });

        ArrayList<String> data = new ArrayList<>();

        // TODO: load saved resumes
        data.add("PLACEHOLDER ITEM 1");
        data.add("PLACEHOLDER ITEM 2");

        RecyclerView recyclerView = binding.recyclerResumeBuilder;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ResumeBuilderRecyclerAdapter(getContext(), data);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(View view, int position) {
        // TODO: "download pdf" modal
    }
}