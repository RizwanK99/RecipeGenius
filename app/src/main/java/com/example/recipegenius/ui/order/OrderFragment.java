package com.example.recipegenius.ui.order;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.recipegenius.R;
import com.example.recipegenius.databinding.FragmentHomeBinding;
import com.example.recipegenius.databinding.FragmentOrderBinding;
import com.example.recipegenius.ui.home.HomeViewModel;

public class OrderFragment extends Fragment {

    private FragmentOrderBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OrderViewModel orderViewModel =
                new ViewModelProvider(this).get(OrderViewModel.class);

        binding = FragmentOrderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*Button recipebutton = (Button) root.findViewById(R.id.myRecipesButton);
        recipebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.navigation_my_recipes);
            }
        });

        Button picksbutton = (Button) root.findViewById(R.id.todaysPicksButton);
        picksbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.navigation_todays_picks);
            }
        });

        Button filterbutton = (Button) root.findViewById(R.id.editFiltersButton);
        filterbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.navigation_filters);
            }
        });*/

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}