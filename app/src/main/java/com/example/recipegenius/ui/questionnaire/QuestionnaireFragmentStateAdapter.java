package com.example.recipegenius.ui.questionnaire;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.recipegenius.ui.questionnaire.dietaryrestrictions.DietaryRestrictionsFragment;
import com.example.recipegenius.ui.questionnaire.mealplanninggoals.MealPlanningGoalsFragment;
import com.example.recipegenius.ui.questionnaire.weightgoals.WeightGoalsFragment;

public class QuestionnaireFragmentStateAdapter extends FragmentStateAdapter {

    public QuestionnaireFragmentStateAdapter(FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new QuestionnaireWelcomeScreenFragment();
            case 1:
                return new WeightGoalsFragment();
            case 2:
                return new DietaryRestrictionsFragment();
            case 3:
                return new MealPlanningGoalsFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }


}
