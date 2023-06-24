package com.example.recipegenius.ui.myrecipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipegenius.databinding.FragmentMyrecipesBinding;


public class MyRecipesFragment extends Fragment {

    private FragmentMyrecipesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyRecipesModel myRecipesViewModel =
                new ViewModelProvider(this).get(MyRecipesModel.class);

        binding = FragmentMyrecipesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMyRecipes;
        myRecipesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;


    }


}