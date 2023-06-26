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

        String name1 = "Fish and Chips";
        String[] ingredients1 = {"fish", "potatoes", "salt", "pepper", "oil", "flour"};
        // String[] instructions1 = {"heat up oil in a pan", "cook fish in pan", "cut potatoes into strips", "fry potatoes in oil"};
        String[] tags1 = {"pescatarian"};
        recipeList.add(new RecipeObject(name1, ingredients1, tags1));

        String name2 = "Spaghetti and Meatballs";
        String[] ingredients2 = {"pasta", "tomato sauce", "meatballs"};
        // String[] instructions2 = {"boil water", "cook pasta in boiling water", "heat up tomato sauce", "cook meatballs", "put pasta, tomato sauce, and meatballs together"};
        String[] tags2 = {"glutenFree"};
        recipeList.add(new RecipeObject(name2, ingredients2, tags2));

        String name3 = "Grilled Cheese Sandwich";
        String[] ingredients3 = {"bread", "cheese", "butter"};
        // String[] instructions3 = {"spread butter on one side of each slice of bread", "put cheese between the two slices of bread", "cook the sandwich on a pan"};
        String[] tags3 = {"lactose", "vegetarian", "pescatarian"};
        recipeList.add(new RecipeObject(name3, ingredients3, tags3));

        String name4 = "Tomato Soup";
        String[] ingredients4 = {"tomatoes", "water", "salt", "pepper"};
        // String[] instructions4 = {"boil water", "add tomatoes to boiling water", "add salt and pepper to taste"};
        String[] tags4 = {"vegan", "vegetarian", "glutenfree", "pescatarian"};
        recipeList.add(new RecipeObject(name4, ingredients4, tags4));

        String name5 = "Fried Chicken";
        String[] ingredients5 = {"chicken", "flour", "salt", "pepper", "oil"};
        // String[] instructions5 = {"heat up oil in a pan", "coat chicken in flour, salt, and pepper", "cook chicken in pan"};
        String[] tags5 = {};
        recipeList.add(new RecipeObject(name5, ingredients5, tags5));

        String name6 = "Chocolate Chip Cookies";
        String[] ingredients6 = {"flour", "sugar", "butter", "chocolate chips"};
        // String[] instructions6 = {"mix flour, sugar, and butter together", "add chocolate chips to the mixture", "bake the cookies in the oven"};
        String[] tags6 = {"vegetarian", "pescatarian"};
        recipeList.add(new RecipeObject(name6, ingredients6, tags6));

        String name7 = "Steak Frites";
        String[] ingredients7 = {"steak", "potatoes", "salt", "pepper", "oil"};
        // String[] instructions7 = {"heat up oil in a pan", "cook steak in pan", "cut potatoes into strips", "fry potatoes in oil"};
        String[] tags7 = {"glutenFree"};
        recipeList.add(new RecipeObject(name7, ingredients7, tags7));

        String name8 = "Peanut Butter Cookies";
        String[] ingredients8 = {"peanut butter", "sugar", "eggs", "flour"};
        // String[] instructions8 = {"mix peanut butter, sugar, and egg together", "bake the cookies in the oven"};
        String[] tags8 = {"vegetarian", "pescatarian"};
        recipeList.add(new RecipeObject(name8, ingredients8, tags8));

        String name9 = "Peanut Butter and Jelly Sandwich";
        String[] ingredients9 = {"peanut butter", "jelly", "bread"};
        // String[] instructions9 = {"spread peanut butter on one slice of bread", "spread jelly on the other slice of bread", "put the two slices of bread together"};
        String[] tags9 = {"vegan", "vegetarian", "pescatarian"};
        recipeList.add(new RecipeObject(name9, ingredients9, tags9));

        String name10 = "Trail Mix";
        String[] ingredients10 = {"almonds", "cashews", "raisins", "chocolate chips", "nuts"};
        // String[] instructions10 = {"mix almonds, cashews, raisins, and chocolate chips together"};
        String[] tags10 = {"glutenFree", "vegan", "vegetarian", "pescatarian"};
        recipeList.add(new RecipeObject(name10, ingredients10, tags10));

        String name11 = "Pancakes";
        String[] ingredients11 = {"flour", "sugar", "baking powder", "salt", "eggs", "milk", "butter"};
        // String[] instructions11 = {"mix flour, sugar, baking powder, and salt together", "add egg, milk, and butter to the mixture", "cook the pancakes on a pan"};
        String[] tags11 = {"vegetrain", "pescatarian"};
        recipeList.add(new RecipeObject(name11, ingredients11, tags11));

        String name12 = "Peanut Butter";
        String[] ingredients12 = {"peanuts", "salt"};
        // String[] instructions12 = {"blend peanuts and salt together"};
        String[] tags12 = {"vegan", "vegetarian", "pescatarian"};
        recipeList.add(new RecipeObject(name12, ingredients12, tags12));

        String name13 = "Vai's Famous Paneer";
        String[] ingredients13 = {"paneer", "salt", "pepper", "oil"};
        // String[] instructions13 = {"heat up oil in a pan", "cook paneer in pan", "add salt and pepper to taste"};
        String[] tags13 = {"vegetarian"};
        recipeList.add(new RecipeObject(name13, ingredients13, tags13));


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
                        if (i >= recipeList.size()){
                            break;
                        }

                    }
                }
            }
        }



        for (Map.Entry<String, Boolean> entry : allergyFilters.entrySet()) {
            if (entry.getValue()) {
                int i = 0;
                while (i < recipeList.size()){
                    String[] ingredientsList = recipeList.get(i).getIngredients();
                    for (int j = 0; j < ingredientsList.length; j++) {
                        if (ingredientsList[j].equalsIgnoreCase(entry.getKey())) {
                            recipeList.remove(i);
                            i--;
                            break;
                        }
                    }
                    i++;
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