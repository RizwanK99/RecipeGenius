package com.example.recipegenius.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipegenius.R;
import com.example.recipegenius.databinding.FragmentHomeBinding;
import com.example.recipegenius.ui.filters.FiltersFragment;
import com.example.recipegenius.ui.myrecipes.ClickListener;
import com.example.recipegenius.ui.myrecipes.RecipeObject;
import com.example.recipegenius.ui.myrecipes.RecipeAdapter;
import com.example.recipegenius.ui.filters.FiltersViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private List<RecipeObject> recipeList;

    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        context = getContext();

        FiltersViewModel filtersViewModel = new ViewModelProvider(this).get(FiltersViewModel.class);
        Button recipebutton = (Button) view.findViewById(R.id.myRecipesButton);
        recipebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.navigation_my_recipes);
            }
        });

        // Button picksbutton = (Button) root.findViewById(R.id.todaysPicksButton);
        // picksbutton.setOnClickListener(new View.OnClickListener() {
        //     public void onClick(View v) {
        //         Navigation.findNavController(v).navigate(R.id.navigation_todays_picks);
        //     }
        // });

        Button filterbutton = (Button) view.findViewById(R.id.editFiltersButton);
        filterbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.navigation_filters);
            }
        });

        

        recyclerView = view.findViewById(R.id.recyclerViewToday);

        recipeList = new ArrayList<>();

        SharedPreferences dietFilters = getActivity().getSharedPreferences("dietFilters", Context.MODE_PRIVATE);
        SharedPreferences allergyFilters = getActivity().getSharedPreferences("allergyFilters",
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = dietFilters.edit();
        for (Map.Entry<String, Boolean> f : filtersViewModel.restrictions_filters.entrySet()) {
            editor.putBoolean(f.getKey(), f.getValue());
        }
        editor.apply();

        editor = allergyFilters.edit();
        for (Map.Entry<String, Boolean> f : filtersViewModel.allergy_filters.entrySet()) {
            editor.putBoolean(f.getKey(), f.getValue());
        }
        editor.apply();

        // TODO: change Filters to work with API
        // API test
        Map<String, ?> dietMap = dietFilters.getAll();
        ArrayList<String> trueDietFilters = new ArrayList<String>();
        for (Map.Entry<String, ?> entry : dietMap.entrySet()) {
            if ((Boolean) entry.getValue() == true)
                trueDietFilters.add(entry.getKey());
        }

        Map<String, ?> AllergyMap = allergyFilters.getAll();
        ArrayList<String> trueAllergyFilters = new ArrayList<String>();
        for (Map.Entry<String, ?> entry : AllergyMap.entrySet()) {
            if ((Boolean) entry.getValue() == true)
                trueAllergyFilters.add(entry.getKey());
        }

        // create fake data of recipe objects
        String name1 = "Peanut Butter and Jelly Sandwich";
        String[] ingredients1 = { "Peanut Butter", "Jelly", "Bread" };
        String[] instructions1 = { "On one slice of bread, spread peanut butter evenly over the bread.",
                "On the other slice of bread, spread the jelly evenly over the bread.",
                "Put the two slices of bread together with the peanut butter and jelly facing in.",
                "Serve and enjoy!",
                "Optional: Remove crusts, cut diagonally" };
        String[] tags1 = { "Nuts", "Gluten", "Fruit" };
        String imageURL1 = "https://t3.ftcdn.net/jpg/03/94/77/66/360_F_394776627_Fcf6vVQB4EO6sKVtDCTnfdnRJNBpeIEj.jpg";
        recipeList.add(new RecipeObject(name1, ingredients1, instructions1, tags1, imageURL1));

        String name2 = "Spaghetti and Meatballs";
        String[] ingredients2 = { "Pasta", "Tomato Sauce", "Meatballs" };
        String[] instructions2 = { "boil water", "cook pasta in boiling water", "heat up tomato sauce",
                "cook meatballs", "put pasta, tomato sauce, and meatballs together" };
        String[] tags2 = { "Pasta", "Tomato", "Meat" };
        String imageURL2 = "https://images.pexels.com/photos/1437267/pexels-photo-1437267.jpeg?cs=srgb&dl=pexels-engin-akyurt-1437267.jpg&fm=jpg";
        recipeList.add(new RecipeObject(name2, ingredients2, instructions2, tags2, imageURL2));

        try {
            // just 15 recipes for testing
            String url =
                    "https://api.spoonacular.com/recipes/complexSearch?apiKey=ebe7fae3f40b431dab2335358eab0c38&excludeCuisine="
                            + String.join(",", trueDietFilters) + "&excludeIngredients="
                            + String.join(",", trueAllergyFilters)
                            + "&number=3&instructionsRequired=true&addRecipeInformation=true&fillIngredients=true";

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            System.out.println("There was an issue reaching the API, please try again later.");
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            try {
                                JSONObject obj = new JSONObject(response.body().string());
                                JSONArray arr = obj.getJSONArray("results");

                                System.out.println("API response:");
                                System.out.println(arr.length());
                                SharedPreferences arrLen = context.getSharedPreferences("numRecipes",
                                        Context.MODE_PRIVATE);
                                SharedPreferences.Editor e = arrLen.edit();
                                e.putInt("len", arr.length());
                                e.apply();
                                for (int i = 0; i < arr.length(); ++i) {
                                    JSONArray ingrArray = arr.getJSONObject(i).getJSONArray("extendedIngredients");
                                    ArrayList<String> ingrList = new ArrayList<String>();
                                    for (int j = 0; j < ingrArray.length(); j++) {
                                        ingrList.add(ingrArray.getJSONObject(j).getString("original"));
                                    }
                                    String[] ingredients = ingrList.toArray(new String[ingrArray.length()]);

                                    JSONArray instrArray = arr.getJSONObject(i).getJSONArray("analyzedInstructions")
                                            .getJSONObject(0).getJSONArray("steps");
                                    ArrayList<String> stepList = new ArrayList<String>();
                                    for (int j = 0; j < instrArray.length(); j++) {
                                        stepList.add(instrArray.getJSONObject(j).getString("step"));
                                    }
                                    String[] steps = stepList.toArray(new String[instrArray.length()]);

                                    JSONArray tagArray = arr.getJSONObject(i).getJSONArray("diets");
                                    ArrayList<String> tagList = new ArrayList<String>();
                                    for (int j = 0; j < tagArray.length(); j++) {
                                        tagList.add(tagArray.getString(j));
                                    }
                                    String[] tags = tagList.toArray(new String[tagArray.length()]);

                                    RecipeObject recipeObject = new RecipeObject(
                                            arr.getJSONObject(i).getString("title"),
                                            ingredients,
                                            steps,
                                            tags,
                                            arr.getJSONObject(i).getString("image"));
                                    recipeList.add(recipeObject);
                                    System.out.println(recipeObject.getName());
                                }

                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("HETE");
        for (RecipeObject r : recipeList) {
            System.out.println("Here" + r.getName());
        }


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

    public void GetRequest(String url) throws IOException {

    }


}