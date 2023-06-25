package com.example.recipegenius.ui.myrecipes;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recipegenius.R;

import com.example.recipegenius.ui.myrecipes.RecipeObject;

public class RecipeViewHolder extends RecyclerView.ViewHolder {
    private TextView recipeName;
    private TextView recipeIngredients;
    private TextView recipeInstructions;
    private TextView recipeTags;
    View view;

    public RecipeViewHolder(View itemView) {
        super(itemView);
        //recipeName = itemView.findViewById(R.id.recipeName);
        view = itemView;
    }
}
