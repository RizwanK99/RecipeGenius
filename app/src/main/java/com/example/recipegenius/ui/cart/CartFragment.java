package com.example.recipegenius.ui.cart;

import androidx.lifecycle.ViewModelProvider;

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
import com.example.recipegenius.databinding.FragmentCartBinding;
import com.example.recipegenius.ui.ingredient.MeasureUnit;
import com.example.recipegenius.ui.inventory.InventoryAdapter;
import com.example.recipegenius.ui.inventory.InventoryFragment;
import com.example.recipegenius.ui.inventory.InventoryModel;
import com.example.recipegenius.ui.inventory.InventoryViewModel;
import com.example.recipegenius.ui.myrecipes.ClickListener;

public class CartFragment extends Fragment {
    private CartViewModel cartViewModel;
    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private CartViewModel orderCart = new CartViewModel();
    private InventoryModel inventory = new InventoryModel();

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.order_cart_list);


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

        adapter = new CartAdapter(inventory.toList(), listener, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        // TODO: Use the ViewModel
    }

}