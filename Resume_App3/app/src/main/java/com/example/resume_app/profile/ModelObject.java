package com.example.resume_app.profile;

import com.example.resume_app.R;

// TODO: get rid of this and give the views logic
public enum ModelObject {

    INFO(R.string.tab_info, R.layout.tab_info),
    POSTS_COMMENTS(R.string.tab_posts_comments, R.layout.tab_posts_comments);

    int titleResId;
    int layoutResId;

    ModelObject(int titleResId, int layoutResId) {
        this.titleResId = titleResId;
        this.layoutResId = layoutResId;
    }

    public int getTitleResId() {
        return titleResId;
    }

    public int getLayoutResId() {
        return layoutResId;
    }
}
