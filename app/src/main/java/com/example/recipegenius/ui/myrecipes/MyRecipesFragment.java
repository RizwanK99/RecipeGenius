package com.example.recipegenius.ui.myrecipes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipegenius.R;
import com.example.recipegenius.databinding.FragmentMyrecipesBinding;
import com.example.recipegenius.ui.myrecipes.RecipeObject;

import java.util.ArrayList;
import java.util.List;


public class MyRecipesFragment extends Fragment {

    private FragmentMyrecipesBinding binding;
    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private List<RecipeObject> recipeList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myrecipes, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        recipeList = new ArrayList<>();
        

        // create fake data of recipe objects

        String[] ingredients = {"peanut butter", "jelly", "bread"};
        String[] instructions = {"spread peanut butter on one slice of bread", "spread jelly on the other slice of bread", "put the two slices of bread together"};
        String[] tags = {"nuts", "gluten", "fruit"};
        recipeList.add(new RecipeObject("Peanut Butter and Jelly", ingredients, tags));
//        recipeList.add(new RecipeObject("Peanut Butter and Jelly", ingredients, instructions, tags));
//
        String[] ingredients2 = {"pasta", "tomato sauce", "meatballs"};
//        String[] instructions2 = {"boil water", "cook pasta in boiling water", "heat up tomato sauce", "cook meatballs", "put pasta, tomato sauce, and meatballs together"};
        String[] tags2 = {"pasta", "tomato", "meat"};
        recipeList.add(new RecipeObject("Spaghetti and Meatballs", ingredients2, tags2));
//
        String[] ingredients3 = {"bread", "cheese", "butter"};
//        String[] instructions3 = {"spread butter on one side of each slice of bread", "put cheese between the two slices of bread", "cook the sandwich on a pan"};
        String[] tags3 = {"bread", "dairy"};
        recipeList.add(new RecipeObject("Grilled Cheese Sandwich", ingredients3, tags3));


        //final TextView textView = binding.textMyRecipes;
        //myRecipesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        ClickListener listener = new ClickListener() {
            @Override
            public void click(int index) {
                // print the recipe name to the console
                System.out.println(recipeList.get(index));
            }
        };
        adapter = new RecipeAdapter(recipeList, listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;


    }


}