package com.example.recipegenius.ui.myrecipes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyRecipesModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyRecipesModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome");
    }

    public LiveData<String> getText() {
        return mText;
    }



}