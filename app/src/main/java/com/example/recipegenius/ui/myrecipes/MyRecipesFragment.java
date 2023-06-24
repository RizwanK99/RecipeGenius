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
import com.example.recipegenius.ui.myrecipes.RecipeObject;

import java.util.List;


public class MyRecipesFragment extends Fragment {

    private FragmentMyrecipesBinding binding;
//    private RecyclerView recyclerView;
//    private CustomAdapter adapter;
    private List<RecipeObject> recipeList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyRecipesModel myRecipesViewModel =
                new ViewModelProvider(this).get(MyRecipesModel.class);

        binding = FragmentMyrecipesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // create fake data of recipe objects

        
//        String[] ingredients = {"peanut butter", "jelly", "bread"};
//        String[] instructions = {"spread peanut butter on one slice of bread", "spread jelly on the other slice of bread", "put the two slices of bread together"};
//        String[] tags = {"nuts", "bread", "fruit"};
//        recipeList.add(new RecipeObject("Peanut Butter and Jelly", ingredients, instructions, tags));
//
//        String[] ingredients2 = {"pasta", "tomato sauce", "meatballs"};
//        String[] instructions2 = {"boil water", "cook pasta in boiling water", "heat up tomato sauce", "cook meatballs", "put pasta, tomato sauce, and meatballs together"};
//        String[] tags2 = {"pasta", "tomato", "meat"};
//        recipeList.add(new RecipeObject("Spaghetti and Meatballs", ingredients2, instructions2, tags2));
//
//        String[] ingredients3 = {"bread", "cheese", "butter"};
//        String[] instructions3 = {"spread butter on one side of each slice of bread", "put cheese between the two slices of bread", "cook the sandwich on a pan"};
//        String[] tags3 = {"bread", "dairy"};
//        recipeList.add(new RecipeObject("Grilled Cheese", ingredients3, instructions3, tags3));

        // // display the recipe names
        // TextView recipe1 = root.findViewById(R.id.recipe1);
        // recipe1.setText(recipes[0].getName());
        // TextView recipe2 = root.findViewById(R.id.recipe2);
        // recipe2.setText(recipes[1].getName());
        // TextView recipe3 = root.findViewById(R.id.recipe3);
        // recipe3.setText(recipes[2].getName());

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