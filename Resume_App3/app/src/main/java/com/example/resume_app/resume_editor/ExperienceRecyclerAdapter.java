package com.example.resume_app.resume_editor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.resume_app.R;
import com.example.resume_app.data_model.Experience;

import java.util.ArrayList;

public class ExperienceRecyclerAdapter extends RecyclerView.Adapter<ExperienceRecyclerAdapter.ViewHolder> {
    static ArrayList<Experience> experiences;

    ExperienceRecyclerAdapter(ArrayList<Experience> experiences) {
        ExperienceRecyclerAdapter.experiences = experiences;

    }

    @Override
    public ExperienceRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_experience_recycler_container, parent, false);
        return new ExperienceRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExperienceRecyclerAdapter.ViewHolder holder, int position) {
       // holder.positionTitle.setText(experiences.get(position).jobPosition);
        //the description
//        holder.description.setText(experiences.get(position).jobPosition);
//        holder.editButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //TODO
//                //make edit pop-up pop up
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return experiences.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView positionTitle;
        TextView description;
        Button editButton;


        ViewHolder(View itemView) {
            super(itemView);
         //   positionTitle = itemView.findViewById(R.id.position_title);
           // description = itemView.findViewById(R.id.description);
          //editButton = itemView.findViewById(R.id.edit_button);

        }

    }

}