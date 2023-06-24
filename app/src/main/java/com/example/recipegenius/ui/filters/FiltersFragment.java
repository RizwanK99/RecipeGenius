package com.example.recipegenius.ui.filters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipegenius.databinding.FragmentFiltersBinding;

public class FiltersFragment extends Fragment {

    private FragmentFiltersBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FiltersViewModel filtersViewModel = new ViewModelProvider(this).get(FiltersViewModel.class);

        binding = FragmentFiltersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textFilters;
        filtersViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}