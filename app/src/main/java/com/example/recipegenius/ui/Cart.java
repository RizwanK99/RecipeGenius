package com.example.recipegenius.ui;

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
import com.example.recipegenius.databinding.FragmentCartBinding;
import com.example.recipegenius.databinding.FragmentOrderBinding;
import com.example.recipegenius.ui.order.OrderViewModel;

public class Cart extends Fragment {

    private CartViewModel mViewModel;
    private FragmentCartBinding binding;

    public static Cart newInstance() {
        return new Cart();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CartViewModel cartViewModel =
                new ViewModelProvider(this).get(CartViewModel.class);

        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        // TODO: Use the ViewModel
    }

}