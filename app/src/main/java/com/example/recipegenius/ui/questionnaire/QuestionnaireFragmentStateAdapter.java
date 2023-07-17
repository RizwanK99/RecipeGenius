package com.example.recipegenius.ui.questionnaire;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.recipegenius.ui.weightgoals.WeightGoalsFragment;

import java.util.List;

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
                return new QuestionnaireWelcomeScreenFragment();
            case 3:
                return new QuestionnaireWelcomeScreenFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }


}