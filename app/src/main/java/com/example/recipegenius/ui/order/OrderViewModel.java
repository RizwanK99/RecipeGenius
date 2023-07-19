package com.example.recipegenius.ui.order;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public OrderViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome");
    }

    public LiveData<String> getText() {
        return mText;
    }
}