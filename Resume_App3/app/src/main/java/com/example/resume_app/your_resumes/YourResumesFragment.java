package com.example.resume_app.your_resumes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.resume_app.MainActivity;
import com.example.resume_app.R;
import com.example.resume_app.resume_editor.ResumeEditorActivity;

import java.util.ArrayList;

public class YourResumesFragment extends Fragment implements YourResumesRecyclerAdapter.IClickListener {

    public static final String ID = "YOUR_RESUMES";

    ArrayList<String> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_your_resumes, container, false);

        View loader = view.findViewById(R.id.loader);
        loader.setVisibility(View.VISIBLE);

        new Thread(() -> {
            data = new ArrayList<>();

            // TODO: load saved resumes
            data.add("PLACEHOLDER ITEM 1");
            data.add("PLACEHOLDER ITEM 2");

            requireActivity().runOnUiThread(() -> {
                connectXml(view);
                loader.setVisibility(View.GONE);
            });
        }).start();

        connectXml(view);

        return view;
    }

    void connectXml(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new YourResumesRecyclerAdapter(getContext(), data, this));

        Button buttonPlus = view.findViewById(R.id.button_create_resume);
        buttonPlus.setOnClickListener(v -> startActivity(new Intent(getContext(), ResumeEditorActivity.class)));
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtra("FILE_NAME", "PLACEHOLDER_TEXT");
        startActivity(intent);
    }
}