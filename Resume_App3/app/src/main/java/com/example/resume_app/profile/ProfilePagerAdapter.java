package com.example.resume_app.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class ProfilePagerAdapter extends PagerAdapter {

    LayoutInflater inflater;
    ArrayList<ModelObject> tabs;

    public ProfilePagerAdapter(Context context, ArrayList<ModelObject> tabs) {
        this.inflater = LayoutInflater.from(context);
        this.tabs = tabs;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewGroup view = (ViewGroup) inflater.inflate(tabs.get(position).getLayoutResId(), container, false);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ViewGroup) object);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return inflater.getContext().getString(tabs.get(position).getTitleResId());
    }
}
