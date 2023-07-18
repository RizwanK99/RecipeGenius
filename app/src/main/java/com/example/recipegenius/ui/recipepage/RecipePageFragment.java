package com.example.recipegenius.ui.recipepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;


import com.example.recipegenius.databinding.FragmentRecipepageBinding;

public class RecipePageFragment extends Fragment {

    private FragmentRecipepageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RecipePageModel recipePageViewModel =
                new ViewModelProvider(this).get(RecipePageModel.class);

        binding = FragmentRecipepageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Button recipebutton = (Button) root.findViewById(R.id.myRecipesButton);
        // recipebutton.setOnClickListener(new View.OnClickListener() {
        //     public void onClick(View v) {
        //         Navigation.findNavController(v).navigate(R.id.navigation_my_recipes);
        //     }
        // });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;


    }


}