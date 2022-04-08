package com.example.resume_app.ui.resume_builder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.resume_app.R;

import java.util.ArrayList;

public class ResumeBuilderRecyclerAdapter extends RecyclerView.Adapter<ResumeBuilderRecyclerAdapter.ViewHolder> {

    private final ArrayList<String> data;
    private final LayoutInflater inflater;
    private IClickListener clickListener;

    ResumeBuilderRecyclerAdapter(Context context, ArrayList<String> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recycler_container, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    void setClickListener(IClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface IClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(view, getAdapterPosition());
        }
    }
}