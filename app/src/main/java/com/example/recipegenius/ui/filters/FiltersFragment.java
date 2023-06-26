package com.example.recipegenius.ui.filters;

import android.content.Context;
import android.content.SharedPreferences;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class FiltersFragment extends Fragment {

    private FragmentFiltersBinding binding;
    private FiltersViewModel mViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FiltersViewModel filtersViewModel = new ViewModelProvider(this).get(FiltersViewModel.class);

        binding = FragmentFiltersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.setViewModel(filtersViewModel);

        SharedPreferences dietFilters = getActivity().getSharedPreferences("dietFilters", Context.MODE_PRIVATE);
        SharedPreferences allergyFilters = getActivity().getSharedPreferences("allergyFilters", Context.MODE_PRIVATE);

        filtersViewModel.restrictions_filters = (Map<String, Boolean>) dietFilters.getAll();
        filtersViewModel.allergy_filters = (Map<String, Boolean>) allergyFilters.getAll();

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

                //TODO: change Keto to Ketogenic for API
                //API test
//                Map<String, ?> dietMap = dietFilters.getAll();
//                ArrayList<String> trueDietFilters = new ArrayList<String>();
//                for (Map.Entry<String, ?> entry : dietMap.entrySet()) {
//                    if ((Boolean)entry.getValue() == true)
//                        trueDietFilters.add(entry.getKey());
//                }
//                //TODO: Async Task
//                String url = "https://api.spoonacular.com/recipes/complexSearch?excludeCuisine=" + String.join(",", trueDietFilters);
//                try {
//                    getRequest(url);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println(url);


                editor = allergyFilters.edit();
                for (Map.Entry<String,Boolean> f: filtersViewModel.allergy_filters.entrySet()) {
                    editor.putBoolean(f.getKey(), f.getValue());
                }
                editor.apply();

                // navigate back to home
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

    private static void getRequest(String url) throws IOException {
        URL getURL = new URL(url);
        HttpURLConnection con = (HttpURLConnection) getURL.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        //TODO: better API key implementation
        con.setRequestProperty("x-api-key", "ebe7fae3f40b431dab2335358eab0c38");
        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
            System.out.println("OK");
        }
    }
}