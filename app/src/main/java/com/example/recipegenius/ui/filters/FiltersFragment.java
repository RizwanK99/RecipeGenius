package com.example.recipegenius.ui.filters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.recipegenius.R;
import com.example.recipegenius.databinding.FragmentFiltersBinding;

import java.util.Map;

public class FiltersFragment extends Fragment {

    private FragmentFiltersBinding binding;
    private FiltersViewModel mViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FiltersViewModel filtersViewModel = new ViewModelProvider(this).get(FiltersViewModel.class);

        binding = FragmentFiltersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.setViewModel(filtersViewModel);

        SharedPreferences dietFilters = getActivity().getSharedPreferences("dietFilters", Context.MODE_PRIVATE);
        SharedPreferences allergyFilters = getActivity().getSharedPreferences("allergyFilters", Context.MODE_PRIVATE);

        filtersViewModel.restrictions_filters = (Map<String, Boolean>) dietFilters.getAll();
        filtersViewModel.allergy_filters = (Map<String, Boolean>) allergyFilters.getAll();

        Button applyFiltersButton = (Button) root.findViewById(R.id.applyFiltersButton);
        applyFiltersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences dietFilters = getActivity().getSharedPreferences("dietFilters", Context.MODE_PRIVATE);
                SharedPreferences allergyFilters = getActivity().getSharedPreferences("allergyFilters", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = dietFilters.edit();
                for (Map.Entry<String,Boolean> f: filtersViewModel.restrictions_filters.entrySet()) {
                    editor.putBoolean(f.getKey(), f.getValue());
                }
                editor.apply();

                editor = allergyFilters.edit();
                for (Map.Entry<String,Boolean> f: filtersViewModel.allergy_filters.entrySet()) {
                    editor.putBoolean(f.getKey(), f.getValue());
                }
                editor.apply();

                // navigate back to home
                Navigation.findNavController(v).navigate(R.id.navigation_home);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}