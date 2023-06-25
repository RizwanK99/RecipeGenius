package com.example.recipegenius.ui.filters;

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

public class FiltersFragment extends Fragment {

    private FragmentFiltersBinding binding;
    private FiltersViewModel mViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FiltersViewModel filtersViewModel = new ViewModelProvider(this).get(FiltersViewModel.class);

        binding = FragmentFiltersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.setViewModel(filtersViewModel);

        Button applyFiltersButton = (Button) root.findViewById(R.id.applyFiltersButton);
        applyFiltersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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