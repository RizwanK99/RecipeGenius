package com.example.recipegenius.ui.inventory;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recipegenius.R;
import com.example.recipegenius.ui.ingredient.IngredientViewModel;
import com.example.recipegenius.ui.ingredient.InventoryIngredientModel;
import com.example.recipegenius.ui.ingredient.MeasureUnit;
import com.example.recipegenius.ui.myrecipes.ClickListener;
import com.example.recipegenius.ui.myrecipes.RecipeAdapter;
import com.example.recipegenius.ui.myrecipes.RecipeObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryFragment extends Fragment {

    private InventoryViewModel inventoryViewModel;
    private RecyclerView recyclerView;
    private InventoryAdapter adapter;
    private InventoryModel<InventoryIngredientModel> inventory = new InventoryModel();

    public static InventoryFragment newInstance() {
        return new InventoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_inventory, container, false);

            recyclerView = view.findViewById(R.id.ingredient_list);

            inventory.addIngredient("Salt",0.5);
            inventory.addIngredient("Potato",500);
            inventory.addIngredient("Oil",500, MeasureUnit.MILLILITER);
            inventory.addIngredient("Chicken",0.2, MeasureUnit.POUND);
            inventory.addIngredient("Carrot",0.1, MeasureUnit.POUND);

            ClickListener listener = new ClickListener() {
                @Override
                public void click(int index) {
                    // print the recipe name to the console
                    //System.out.println(recipeList.get(index));
                }
            };

            adapter = new InventoryAdapter(inventory.toList(), listener, getContext());
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
            return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inventoryViewModel = new ViewModelProvider(this).get(InventoryViewModel.class);
        // TODO: Use the ViewModel
    }

}