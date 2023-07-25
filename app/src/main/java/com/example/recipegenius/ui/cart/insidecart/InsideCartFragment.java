package com.example.recipegenius.ui.cart.insidecart;

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
import com.example.recipegenius.ui.cart.CartViewModel;
import com.example.recipegenius.ui.cart.ordercart.CartIngredientModel;
import com.example.recipegenius.ui.ingredient.MeasureUnit;
import com.example.recipegenius.ui.myrecipes.ClickListener;

import java.util.ArrayList;
import java.util.List;

public class InsideCartFragment extends Fragment {

    private InsideCartViewModel mViewModel;
    private CartViewModel cartViewModel;
    private RecyclerView recyclerView;
    private InsideCartAdapter adapter;
    private List<CartIngredientModel> inventory = new ArrayList<>();

    public static InsideCartFragment newInstance() {
        return new InsideCartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inside_cart, container, false);

        recyclerView = view.findViewById(R.id.inside_cart_list);

        inventory.add(new CartIngredientModel("AAA Steak",2, MeasureUnit.POUND,40));

        ClickListener listener = new ClickListener() {
            @Override
            public void click(int index) {
                // print the recipe name to the console
                //System.out.println(recipeList.get(index));
            }
        };

        adapter = new InsideCartAdapter(inventory, listener, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InsideCartViewModel.class);
        // TODO: Use the ViewModel
    }

}