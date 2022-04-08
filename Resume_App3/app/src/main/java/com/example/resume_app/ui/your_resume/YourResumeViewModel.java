package com.example.resume_app.ui.your_resume;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class YourResumeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public YourResumeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is your resume fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}