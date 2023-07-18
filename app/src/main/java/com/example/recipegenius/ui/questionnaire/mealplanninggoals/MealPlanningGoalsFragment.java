package com.example.recipegenius.ui.questionnaire.mealplanninggoals;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipegenius.R;

import com.example.recipegenius.databinding.FragmentMealPlanningGoalsBinding;

import java.util.Map;

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

        SharedPreferences goals = getActivity().getSharedPreferences("goals", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = goals.edit();
        for (Map.Entry<String,Boolean> f: mealPlanningGoalsViewModelViewModel.mealPlanningGoals.entrySet()) {
            editor.putBoolean(f.getKey(), f.getValue());
        }
        editor.apply();

        super.onDestroyView();
        binding = null;
    }
}
