package com.example.recipegenius.ui.todayspicks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TodaysPicksModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TodaysPicksModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome");
    }

    public LiveData<String> getText() {
        return mText;
    }



}