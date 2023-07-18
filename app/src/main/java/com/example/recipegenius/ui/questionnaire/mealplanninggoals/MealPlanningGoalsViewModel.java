package com.example.recipegenius.ui.questionnaire.mealplanninggoals;

import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

public class MealPlanningGoalsViewModel extends ViewModel {

    public Map<String,Boolean> mealPlanningGoals = new HashMap<String,Boolean>();

    public static String MEAL_PREP = "Meal Prep";
    public static String USE_LEFTOVERS = "Use Leftovers";
    public static String BUDGET = "Budget";

    public MealPlanningGoalsViewModel() {
    }
}