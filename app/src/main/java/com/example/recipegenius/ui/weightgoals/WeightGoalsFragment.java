package com.example.recipegenius.ui.weightgoals;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.recipegenius.R;
import com.example.recipegenius.databinding.FragmentWeightGoalsBinding;

public class WeightGoalsFragment extends Fragment {

    private FragmentWeightGoalsBinding binding;
    private int weightGoal;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentWeightGoalsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView question = root.findViewById(R.id.question);
        RadioGroup weightGoalGroup = (RadioGroup) root.findViewById(R.id.weight_goal_buttons);

        question.setText("Do you want to: ");

        weightGoalGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.lose_weight_button) {
                weightGoal = 0;
            } else if (checkedId == R.id.maintain_weight_button) {
                weightGoal = 1;
            } else {
                weightGoal = 2;
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.weight_goal), weightGoal);
        editor.apply();

        super.onDestroyView();
        binding = null;
    }

}