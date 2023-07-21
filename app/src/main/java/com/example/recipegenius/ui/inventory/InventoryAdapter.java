package com.example.recipegenius.ui.inventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipegenius.R;
import com.example.recipegenius.ui.ingredient.IngredientModel;
import com.example.recipegenius.ui.ingredient.IngredientViewHolder;
import com.example.recipegenius.ui.myrecipes.ClickListener;
import com.example.recipegenius.ui.myrecipes.RecipeViewHolder;

import java.util.Collections;
import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<IngredientViewHolder>{

    List<IngredientModel> list = Collections.emptyList();
    Context context;
    ClickListener listener;

    public InventoryAdapter(List<IngredientModel> list, ClickListener listener, Context context) {
        this.list = list;
        this.listener = listener;
        this.context = context;
    }

    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout

        View photoView = inflater
                .inflate(R.layout.fragment_ingredient,
                        parent, false);

        return new IngredientViewHolder(photoView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        int index = holder.getAdapterPosition();
        holder.ingredientName.setText(list.get(position).name);
        holder.ingredientAmount.setText(String.valueOf(list.get(position).quantity));
        holder.ingredientUnit.setText(list.get(position).unit.toString());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
