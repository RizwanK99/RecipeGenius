package com.example.recipegenius.ui.filters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.recipegenius.R;
import com.example.recipegenius.databinding.FragmentFiltersBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FiltersFragment extends Fragment {

    private FragmentFiltersBinding binding;
    private FiltersViewModel mViewModel;

    SearchView dietSearchView;
    ArrayList<String> dietList;
    ArrayAdapter<String> dietAdapter;
    SearchView allergySearchView;
    ListView allergyListView;
    ArrayList<String> allergyList;
    ArrayAdapter<String> allergyAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FiltersViewModel filtersViewModel = new ViewModelProvider(this).get(FiltersViewModel.class);

        binding = FragmentFiltersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.setViewModel(filtersViewModel);

        // filters
        SharedPreferences dietFilters = getActivity().getSharedPreferences("dietFilters", Context.MODE_PRIVATE);
        SharedPreferences allergyFilters = getActivity().getSharedPreferences("allergyFilters", Context.MODE_PRIVATE);

        filtersViewModel.restrictions_filters = (Map<String, Boolean>) dietFilters.getAll();
        filtersViewModel.allergy_filters = (Map<String, Boolean>) allergyFilters.getAll();

        //TODO: implement search bars (suggestions, selection, etc.)
        /*dietSearchView = (SearchView) root.findViewById(R.id.dietFilterSearch);
        dietAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1, dietList);
        dietSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });*/

//        allergySearchView = (SearchView) root.findViewById(R.id.allergyFilterSearch);
//        allergySuggestions = (ListView) root.findViewById(R.id.allergySuggestions);

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

                //TODO: change Filters to work with API
                //API test
                Map<String, ?> dietMap = dietFilters.getAll();
                ArrayList<String> trueDietFilters = new ArrayList<String>();
                for (Map.Entry<String, ?> entry : dietMap.entrySet()) {
                    if ((Boolean)entry.getValue() == true)
                        trueDietFilters.add(entry.getKey());
                }

                Map<String, ?> AllergyMap = allergyFilters.getAll();
                ArrayList<String> trueAllergyFilters = new ArrayList<String>();
                for (Map.Entry<String, ?> entry : AllergyMap.entrySet()) {
                    if ((Boolean)entry.getValue() == true)
                        trueAllergyFilters.add(entry.getKey());
                }

                try {
                    GetRequest("https://api.spoonacular.com/recipes/complexSearch?apiKey=ebe7fae3f40b431dab2335358eab0c38&excludeCuisine=" + String.join(",", trueDietFilters) + "&excludeIngredients=" + String.join(",", trueAllergyFilters));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // navigate back to home
                Navigation.findNavController(v).navigateUp();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void GetRequest(String url) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String res = response.body().string();
                        System.out.println(res);
                    }
                });
    }


}