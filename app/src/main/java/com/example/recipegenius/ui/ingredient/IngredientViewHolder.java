package com.example.recipegenius.ui.ingredient;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recipegenius.R;
import com.squareup.picasso.Picasso;

public class IngredientViewHolder extends RecyclerView.ViewHolder{
    TextView ingredientName;
    TextView ingredientAmount;
    TextView ingredientUnit;
    View view;

    public IngredientViewHolder(View itemView)  {
        super(itemView);
        //ingredientName = (TextView) itemView.findViewById();

        String imageUri = "https://i.imgur.com/tGbaZCY.jpg";

        view = itemView;
    }
}
