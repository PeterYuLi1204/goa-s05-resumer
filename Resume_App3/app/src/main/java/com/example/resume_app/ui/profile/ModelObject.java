package com.example.resume_app.ui.profile;

import com.example.resume_app.R;

public enum ModelObject {
    INFO(R.string.tab_info, R.layout.activity_info),
    POSTSCOMMENTS(R.string.tab_posts_comments, R.layout.activity_posts_comments);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }
}
