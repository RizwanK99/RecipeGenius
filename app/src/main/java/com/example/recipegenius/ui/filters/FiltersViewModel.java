package com.example.recipegenius.ui.filters;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

public class FiltersViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    public Map<String,Boolean> restrictions_filters = new HashMap<String,Boolean>();
    public Map<String,Boolean> allergy_filters = new HashMap<String,Boolean>();

    public static String VEGAN = "Vegan";
    public static String VEGETARIAN = "Vegetarian";
    public static String KETO = "Keto";

    public static String PALEO = "Paleo";
    public static String GLUTEN_FREE = "GlutenFree";
    public static String PESCATARIAN = "Pescatarian";
    public static String FODMAP = "Fodmap";

    public static String LACTOSE = "Lactose";
    public static String EGGS = "Eggs";
    public static String PEANUTS = "Peanuts";
    public static String TREE_NUTS = "Treenuts";
    public static String FISH = "Fish";
    public static String SHELLFISH = "Shellfish";
    public static String WHEAT = "Wheat";
    public static String CORN = "Corn";
    public static String SESAME = "Sesame";
    public static String COCONUT = "Coconut";
    public static String ALMOND = "Almond";
    public static String CHIA = "Chia";
    public static String STEVIA = "Stevia";
    public static String MELON = "Melon";
    public static String TOMATO = "Tomato";


    public FiltersViewModel() {
        mText = new MutableLiveData<>();
        restrictions_filters.put(VEGAN, true);
        restrictions_filters.put(VEGETARIAN, true);
        allergy_filters.put(PEANUTS, true);
        allergy_filters.put(SHELLFISH, true);
    }

    public LiveData<String> getText() {
        return mText;
    }
}