package com.example.recipegenius.ui.questionnaire.mealplanninggoals;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import com.example.recipegenius.R;

import com.example.recipegenius.databinding.FragmentMealPlanningGoalsBinding;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MealPlanningGoalsFragment extends Fragment {

    private FragmentMealPlanningGoalsBinding binding;
    private MealPlanningGoalsViewModel mealPlanningGoalsViewModelViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mealPlanningGoalsViewModelViewModel = new ViewModelProvider(this).get(MealPlanningGoalsViewModel.class);

        binding = FragmentMealPlanningGoalsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.setViewModel(mealPlanningGoalsViewModelViewModel);

        TextView question = root.findViewById(R.id.question);

        question.setText("What are your meal-planning goals?");

        return root;
    }

    @Override
    public void onDestroyView() {

        SharedPreferences goals = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = goals.edit();

        Set<String> s = new HashSet<>();
        for (Map.Entry<String,Boolean> f: mealPlanningGoalsViewModelViewModel.mealPlanningGoals.entrySet()) {
            if (f.getValue()) {
                s.add(f.getKey());
            }
        }
        editor.putStringSet("MealPlanGoals", s);
        editor.apply();

        super.onDestroyView();
        binding = null;
    }
}
