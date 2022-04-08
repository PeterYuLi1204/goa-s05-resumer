package com.example.resume_app.ui.resume_builder;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ResumeBuilderViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ResumeBuilderViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is resume builder fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}