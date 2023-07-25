package com.example.recipegenius.ui.ingredient;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recipegenius.R;

public class IngredientViewHolder extends RecyclerView.ViewHolder{
    public TextView ingredientName;
    public TextView ingredientAmount;
    public TextView ingredientUnit;
    View view;

    public IngredientViewHolder(View itemView)  {
        super(itemView);
        ingredientName = (TextView) itemView.findViewById(R.id.ordercart_name);
        //ingredientName = (TextView) itemView.findViewById();

        ingredientAmount = (TextView) itemView.findViewById(R.id.ordercart_store);
        ingredientUnit = (TextView) itemView.findViewById(R.id.ordercart_service);

        view = itemView;
    }
}
