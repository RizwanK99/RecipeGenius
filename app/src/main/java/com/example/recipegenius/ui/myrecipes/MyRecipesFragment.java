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
import java.util.Map;

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
        String[] tags3 = {"bread", "vegan"};
        recipeList.add(new RecipeObject("Grilled Cheese Sandwich", ingredients3, tags3));

        String name4 = "Tomato Soup";
        String[] ingredients4 = {"tomatoes", "water", "salt", "pepper"};
        // String[] instructions4 = {"boil water", "add tomatoes to boiling water", "add salt and pepper to taste"};
        String[] tags4 = {"vegan", "vegetarian", "gluren-free"};
        recipeList.add(new RecipeObject(name4, ingredients3, tags3));

        String name5 = "Fried Chicken";
        String[] ingredients5 = {"chicken", "flour", "salt", "pepper", "oil"};
        // String[] instructions5 = {"heat up oil in a pan", "coat chicken in flour, salt, and pepper", "cook chicken in pan"};
        String[] tags5 = {"meat", "gluten"};
        recipeList.add(new RecipeObject(name5, ingredients5, tags5));

        String name6 = "Chocolate Chip Cookies";
        String[] ingredients6 = {"flour", "sugar", "butter", "chocolate chips"};
        // String[] instructions6 = {"mix flour, sugar, and butter together", "add chocolate chips to the mixture", "bake the cookies in the oven"};
        String[] tags6 = {"gluten", "dairy"};
        recipeList.add(new RecipeObject(name6, ingredients6, tags6));

        String name7 = "Steak Frites";
        String[] ingredients7 = {"steak", "potatoes", "salt", "pepper", "oil"};
        // String[] instructions7 = {"heat up oil in a pan", "cook steak in pan", "cut potatoes into strips", "fry potatoes in oil"};
        String[] tags7 = {"meat", "gluten"};
        recipeList.add(new RecipeObject(name7, ingredients7, tags7));

        Map<String, Boolean> dietFilters = (Map<String, Boolean>) getActivity().getSharedPreferences("dietFilters", Context.MODE_PRIVATE).getAll();
        Map<String, Boolean> allergyFilters = (Map<String, Boolean>) getActivity().getSharedPreferences("allergyFilters", Context.MODE_PRIVATE).getAll();

        // filter recipes based on diet and allergy filters
        for (Map.Entry<String, Boolean> entry : dietFilters.entrySet()) {
            if (entry.getValue()) {
                for (int i = 0; i < recipeList.size(); i++) {
                    String[] tagsList = recipeList.get(i).getTags();
                    Boolean rizwank = false;
                    for (int j = 0; j < tagsList.length; j++) {
                        if (tagsList[j].equalsIgnoreCase(entry.getKey())) {
                            rizwank = true;
                        }
                    }
                    if (!rizwank) {
                        recipeList.remove(i);
                        i--;
                    }
                }
            }
        }

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