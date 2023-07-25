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
import com.example.recipegenius.ui.cart.ordercart.OrderCartModel;
import com.example.recipegenius.ui.ingredient.MeasureUnit;
import com.example.recipegenius.ui.inventory.InventoryAdapter;
import com.example.recipegenius.ui.inventory.InventoryFragment;
import com.example.recipegenius.ui.inventory.InventoryModel;
import com.example.recipegenius.ui.inventory.InventoryViewModel;
import com.example.recipegenius.ui.myrecipes.ClickListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CartFragment extends Fragment {
    private CartViewModel cartViewModel;
    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private List<OrderCartModel> inventory = new ArrayList<>();

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.order_cart_list);

        inventory.add(new OrderCartModel("Walmart","Instacart"));
        inventory.add(new OrderCartModel("Sobey's","Uber"));

        ClickListener listener = new ClickListener() {
            @Override
            public void click(int index) {
                // print the recipe name to the console
                //System.out.println(recipeList.get(index));
            }
        };

        adapter = new CartAdapter(inventory, listener, getContext());
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