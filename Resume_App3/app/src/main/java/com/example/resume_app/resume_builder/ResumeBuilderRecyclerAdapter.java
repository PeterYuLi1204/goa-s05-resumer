package com.example.resume_app.resume_builder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.resume_app.R;

import java.util.ArrayList;

public class ResumeBuilderRecyclerAdapter extends RecyclerView.Adapter<ResumeBuilderRecyclerAdapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<String> data;
    IClickListener clickListener;

    ResumeBuilderRecyclerAdapter(Context context, ArrayList<String> data, IClickListener clickListener) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recycler_container, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textName.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface IClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textName;

        ViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(view, getAdapterPosition());
        }
    }
}