package com.example.resume_app.resume_editor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.resume_app.R;
import com.example.resume_app.data_model.Award;

import java.util.ArrayList;

public class AwardsRecyclerAdapter extends RecyclerView.Adapter<AwardsRecyclerAdapter.ViewHolder> {
    static ArrayList<Award> awards;

    AwardsRecyclerAdapter(ArrayList<Award> awards) {
        AwardsRecyclerAdapter.awards = awards;
    }

    @Override
    public AwardsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_experience_recycler_container, parent, false);
        return new AwardsRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AwardsRecyclerAdapter.ViewHolder holder, int position) {
        holder.positionTitle.setText(awards.get(position).awardName);
        //the description
//        holder.description.setText(experiences.get(position).jobPosition);
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                //make edit pop-up pop up
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                //make are you sure pop-up pop up
            }
        });
    }

    @Override
    public int getItemCount() {
        return awards.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView positionTitle;
        TextView description;
        Button editButton;
        Button deleteButton;

        ViewHolder(View itemView) {
            super(itemView);
            positionTitle = itemView.findViewById(R.id.position_title);
            description = itemView.findViewById(R.id.description);
            editButton = itemView.findViewById(R.id.edit_button);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }

    }

}
