package com.example.recipegenius.ui.dietaryrestrictions;

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
import com.example.recipegenius.databinding.FragmentDietaryRestrictionsBinding;

import java.util.Map;

public class DietaryRestrictionsFragment extends Fragment {

    private FragmentDietaryRestrictionsBinding binding;
    private DietaryRestrictionsViewModel dietaryRestrictionsViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        dietaryRestrictionsViewModel = new ViewModelProvider(this).get(DietaryRestrictionsViewModel.class);

        binding = FragmentDietaryRestrictionsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.setViewModel(dietaryRestrictionsViewModel);

        TextView question = root.findViewById(R.id.question);

        question.setText("Do you have any dietary restrictions?");

        return root;
    }

    @Override
    public void onDestroyView() {

        SharedPreferences dietFilters = getActivity().getSharedPreferences("dietFilters", Context.MODE_PRIVATE);
        SharedPreferences allergyFilters = getActivity().getSharedPreferences("allergyFilters", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = dietFilters.edit();
        for (Map.Entry<String,Boolean> f: dietaryRestrictionsViewModel.restrictions_filters.entrySet()) {
            editor.putBoolean(f.getKey(), f.getValue());
        }
        editor.apply();

        editor = allergyFilters.edit();
        for (Map.Entry<String,Boolean> f: dietaryRestrictionsViewModel.allergy_filters.entrySet()) {
            editor.putBoolean(f.getKey(), f.getValue());
        }
        editor.apply();

        super.onDestroyView();
        binding = null;
    }

}