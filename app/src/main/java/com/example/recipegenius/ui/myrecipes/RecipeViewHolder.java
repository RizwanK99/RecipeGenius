package com.example.recipegenius.ui.myrecipes;

import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recipegenius.R;
import com.example.recipegenius.ui.myrecipes.RecipeObject;

import com.squareup.picasso.Picasso;

public class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;
        TextView recipeIngredients;
        TextView recipeInstructions;
        TextView recipeTags;
        ImageView recipeImage;
        View view;

        RecipeViewHolder(View itemView) {
                super(itemView);
                recipeName = (TextView) itemView.findViewById(R.id.recipeName);
                recipeTags = (TextView) itemView.findViewById(R.id.recipeTags);
                recipeIngredients = (TextView) itemView.findViewById(R.id.recipeIngredients);
                recipeInstructions = (TextView) itemView.findViewById(R.id.recipeInstructions);

                String imageUri = "https://i.imgur.com/tGbaZCY.jpg";
                recipeImage = (ImageView) itemView.findViewById(R.id.recipeImage);
                Picasso.get().load(imageUri).fit().centerCrop().into(recipeImage);

                view = itemView;
        }

}
