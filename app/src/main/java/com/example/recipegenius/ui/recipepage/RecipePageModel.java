package com.example.recipegenius.ui.recipepage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecipePageModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public RecipePageModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome");
    }

    public LiveData<String> getText() {
        return mText;
    }
}