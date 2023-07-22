package com.example.recipegenius.ui.myrecipes;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipegenius.R;
import com.example.recipegenius.databinding.FragmentMyrecipesBinding;
import com.example.recipegenius.ui.myrecipes.RecipeObject;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyRecipesFragment extends Fragment {

    private FragmentMyrecipesBinding binding;
    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private List<RecipeObject> recipeList;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myrecipes, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        recipeList = new ArrayList<>();
        context = getContext();

        SharedPreferences numRecipes = context.getSharedPreferences("numRecipes", Context.MODE_PRIVATE);
        int num = numRecipes.getInt("len", 0);
        System.out.println("num: "+num);

        for (int i = 0; i < num; i++){
            SharedPreferences currentRecipe = context.getSharedPreferences("recipe-"+i, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = currentRecipe.getString("recipe", "");
            RecipeObject recipe = gson.fromJson(json, RecipeObject.class);
            System.out.println("recipe: ");
            System.out.println(json);
            System.out.println(recipe);

            recipeList.add(recipe);

        }

        // // create fake data of recipe objects
        // String name1 = "Peanut Butter and Jelly Sandwich";
        // String[] ingredients1 = { "Peanut Butter", "Jelly", "Bread" };
        // String[] instructions1 = { "On one slice of bread, spread peanut butter evenly over the bread.",
        //         "On the other slice of bread, spread the jelly evenly over the bread.",
        //         "Put the two slices of bread together with the peanut butter and jelly facing in.",
        //         "Serve and enjoy!",
        //         "Optional: Remove crusts, cut diagonally" };
        // String[] tags1 = { "Nuts", "Gluten", "Fruit" };
        // String imageURL1 = "https://t3.ftcdn.net/jpg/03/94/77/66/360_F_394776627_Fcf6vVQB4EO6sKVtDCTnfdnRJNBpeIEj.jpg";
        // recipeList.add(new RecipeObject(name1, ingredients1, instructions1, tags1, imageURL1));

        // String name2 = "Spaghetti and Meatballs";
        // String[] ingredients2 = { "Pasta", "Tomato Sauce", "Meatballs" };
        // String[] instructions2 = { "boil water", "cook pasta in boiling water", "heat up tomato sauce",
        //         "cook meatballs", "put pasta, tomato sauce, and meatballs together" };
        // String[] tags2 = { "Pasta", "Tomato", "Meat" };
        // String imageURL2 = "https://images.pexels.com/photos/1437267/pexels-photo-1437267.jpeg?cs=srgb&dl=pexels-engin-akyurt-1437267.jpg&fm=jpg";
        // recipeList.add(new RecipeObject(name2, ingredients2, instructions2, tags2, imageURL2));

        // String name3 = "Grilled Cheese Sandwich";
        // String[] ingredients3 = { "Bread", "Cheese", "Butter" };
        // String[] instructions3 = { "spread butter on one side of each slice of bread",
        //         "put cheese between the two slices of bread", "cook the sandwich on a pan" };
        // String[] tags3 = { "Gluten", "Vegan" };
        // String imageURL3 = "https://natashaskitchen.com/wp-content/uploads/2021/08/Grilled-Cheese-Sandwich-SQ.jpg";
        // recipeList.add(new RecipeObject(name3, ingredients3, instructions3, tags3, imageURL3));

        // String name4 = "Tomato Soup";
        // String[] ingredients4 = { "Tomatoes", "Water", "Salt", "Pepper" };
        // String[] instructions4 = { "boil water", "add tomatoes to boiling water", "add salt and pepper to taste" };
        // String[] tags4 = { "Vegan", "Vegetarian", "Gluten-Free" };
        // String imageURL4 = "https://www.inspiredtaste.net/wp-content/uploads/2022/11/Tomato-Soup-Recipe-Video.jpg";
        // recipeList.add(new RecipeObject(name4, ingredients4, instructions4, tags4, imageURL4));

        // String name5 = "Fried Chicken";
        // String[] ingredients5 = { "Chicken", "Flour", "Salt", "Pepper", "Oil" };
        // String[] instructions5 = { "heat up oil in a pan", "coat chicken in flour, salt, and pepper",
        //         "cook chicken in pan" };
        // String[] tags5 = { "Meat", "Gluten" };
        // String imageURL5 = "https://www.allrecipes.com/thmb/SoBuPU73KcbYHl3Kp3j8Xx4A3fc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/8805-CrispyFriedChicken-mfs-3x2-072-d55b8406d4ae45709fcdeb58a04143c2.jpg";
        // recipeList.add(new RecipeObject(name5, ingredients5, instructions5, tags5, imageURL5));

        // String name6 = "Chocolate Chip Cookies";
        // String[] ingredients6 = { "Flour", "Sugar", "Butter", "Chocolate Chips", "Peanuts" };
        // String[] instructions6 = { "mix flour, sugar, and butter together", "add chocolate chips to the mixture",
        //         "bake the cookies in the oven" };
        // String[] tags6 = { "Gluten", "Dairy" };
        // String imageURL6 = "https://www.marthastewart.com/thmb/1jygvpeBwdnfw1-2U84Nf7ju3JY=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/soft-chewy-chocolate-chip-cookies-01-7004023-0819_horiz_0623-9b9a60be6a624d029aa1579d715e01e7.jpg";
        // recipeList.add(new RecipeObject(name6, ingredients6, instructions6, tags6, imageURL6));

        // String name7 = "Steak Frites";
        // String[] ingredients7 = { "Steak", "Potatoes", "Salt", "Pepper", "Oil" };
        // String[] instructions7 = { "heat up oil in a pan", "cook steak in pan", "cut potatoes into strips",
        //         "fry potatoes in oil" };
        // String[] tags7 = { "Meat", "Gluten" };
        // String imageURl7 = "https://upload.wikimedia.org/wikipedia/commons/9/9a/Reel_and_Brand_-_September_2021_-_Sarah_Stierch_05.jpg";
        // recipeList.add(new RecipeObject(name7, ingredients7, instructions7, tags7, imageURl7));

        // String name8 = "Peanut Butter Cookies";
        // String[] ingredients8 = { "peanut butter", "sugar", "eggs", "flour" };
        // String[] instructions8 = { "mix peanut butter, sugar, and egg together", "bake the cookies in the oven" };
        // String[] tags8 = { "vegetarian", "pescatarian" };
        // String imageURL8 = "https://static.onecms.io/wp-content/uploads/sites/43/2022/12/15/10308-oatmeal-peanut-butter-cookies-DDMFS-beauty-3x4-3132.jpg";
        // recipeList.add(new RecipeObject(name8, ingredients8, instructions8, tags8, imageURL8));

        // String name9 = "Vai's Famous Paneer";
        // String[] ingredients9 = { "paneer", "salt", "pepper", "oil" };
        // String[] instructions9 = { "heat up oil in a pan", "cook paneer in pan", "add salt and pepper to taste" };
        // String[] tags9 = { "vegetarian" };
        // String imageURL9 = "https://thecurrymommy.com/wp-content/uploads/2021/09/butter-paneer-masala-one-pot.jpg";
        // recipeList.add(new RecipeObject(name9, ingredients9, instructions9, tags9, imageURL9));

        // String name10 = "Trail Mix";
        // String[] ingredients10 = { "almonds", "cashews", "raisins", "chocolate chips", "nuts" };
        // String[] instructions10 = { "mix almonds, cashews, raisins, and chocolate chips together" };
        // String[] tags10 = { "glutenFree", "vegan", "vegetarian", "pescatarian" };
        // String imageURL10 = "https://www.budgetbytes.com/wp-content/uploads/2023/05/Trail-Mix-1.jpg";
        // recipeList.add(new RecipeObject(name10, ingredients10, instructions10, tags10, imageURL10));

        // String name11 = "Pancakes";
        // String[] ingredients11 = { "flour", "sugar", "baking powder", "salt", "eggs", "milk", "butter" };
        // String[] instructions11 = { "mix flour, sugar, baking powder, and salt together",
        //         "add egg, milk, and butter to the mixture", "cook the pancakes on a pan" };
        // String[] tags11 = { "vegetrain", "pescatarian" };
        // String imageURL11 = "https://hips.hearstapps.com/hmg-prod/images/best-homemade-pancakes-index-640775a2dbad8.jpg?crop=0.503xw:1.00xh;0.235xw,0&resize=1200:*";
        // recipeList.add(new RecipeObject(name11, ingredients11, instructions11, tags11, imageURL11));

        // String name12 = "Peanut Butter";
        // String[] ingredients12 = { "peanuts", "salt" };
        // String[] instructions12 = { "blend peanuts and salt together", "enjoy!" };
        // String[] tags12 = { "vegan", "vegetarian", "pescatarian" };
        // String imageURL12 = "https://pinchofyum.com/wp-content/uploads/Homemade-Peanut-Butter-Square.png";
        // recipeList.add(new RecipeObject(name12, ingredients12, instructions12, tags12, imageURL12));

        // Map<String, Boolean> dietFilters = (Map<String, Boolean>) getActivity()
        //         .getSharedPreferences("dietFilters", Context.MODE_PRIVATE).getAll();
        // Map<String, Boolean> allergyFilters = (Map<String, Boolean>) getActivity()
        //         .getSharedPreferences("allergyFilters", Context.MODE_PRIVATE).getAll();

        // // filter recipes based on diet and allergy filters
        // int i = 0;
        // boolean found = false;
        // for (String key : dietFilters.keySet()) {
        //     found |= dietFilters.get(key);
        // }
        // // skip the filter if no diets applied
        // while (i < recipeList.size() && found) {
        //     String[] tagsList = recipeList.get(i).getTags();
        //     Boolean rizwank = false;

        //     for (String ing : tagsList) {
        //         if (dietFilters.containsKey(ing) && dietFilters.get(ing) == true) {
        //             rizwank = true;
        //         }
        //     }
        //     if (!rizwank) {
        //         recipeList.remove(i);
        //         i--;
        //     }
        //     i++;
        // }
        // i = 0;
        // while (i < recipeList.size() && allergyFilters.size() > 0) {
        //     String[] ingredientsList = recipeList.get(i).getIngredients();
        //     for (String ing : ingredientsList) {
        //         if (allergyFilters.containsKey(ing) && allergyFilters.get(ing) == true) {
        //             recipeList.remove(i);
        //             i--;
        //             break;
        //         }
        //     }
        //     i++;
        // }

        ClickListener listener = new ClickListener() {
            @Override
            public void click(int index) {
                // print the recipe name to the console
                System.out.println(recipeList.get(index));
            }
        };
        adapter = new RecipeAdapter(recipeList, listener, getContext());
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