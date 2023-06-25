package com.example.recipegenius.ui.filters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

public class FiltersViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    public Map<String,Boolean> filters = new HashMap<String,Boolean>();
    public static String VEGAN = "Vegan";
    public static String VEGETARIAN = "Vegetarian";
    public static String KETO = "Keto";

    public static String PALEO = "Paleo";
    public static String GLUTEN_FREE = "GlutenFree";
    public static String PESCATARIAN = "Pescatarian";
    public static String FODMAP = "Fodmap";

    public FiltersViewModel() {
        mText = new MutableLiveData<>();
        filters.put(VEGAN, true);
        filters.put(VEGETARIAN, true);
        filters.put(KETO, true);
    }
    public LiveData<String> getText() {
        return mText;
    }
}