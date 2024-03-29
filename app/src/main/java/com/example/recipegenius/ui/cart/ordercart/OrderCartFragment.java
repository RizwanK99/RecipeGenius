package com.example.recipegenius.ui.cart.ordercart;

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
import com.example.recipegenius.ui.cart.CartAdapter;
import com.example.recipegenius.ui.cart.CartViewModel;
import com.example.recipegenius.ui.myrecipes.ClickListener;

import java.util.ArrayList;
import java.util.List;

public class OrderCartFragment extends Fragment {

    private OrderCartViewModel mViewModel;
    private CartViewModel cartViewModel;
    private RecyclerView recyclerView;
    private CartAdapter adapter;

    public static OrderCartFragment newInstance() {
        return new OrderCartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, container, false);


        ClickListener listener = new ClickListener() {
            @Override
            public void click(int index) {
                // print the recipe name to the console
                //System.out.println(recipeList.get(index));
            }
        };

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OrderCartViewModel.class);

    }

}