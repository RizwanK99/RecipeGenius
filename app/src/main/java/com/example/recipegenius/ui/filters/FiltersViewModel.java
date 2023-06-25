package com.example.recipegenius.ui.filters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

public class FiltersViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    public Map<String, Boolean> filters;

    public FiltersViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}