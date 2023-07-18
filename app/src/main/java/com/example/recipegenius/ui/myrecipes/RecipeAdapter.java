package com.example.recipegenius.ui.myrecipes;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import androidx.navigation.Navigation;

import com.example.recipegenius.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    List<RecipeObject> list = Collections.emptyList();

    Context context;
    ClickListener listener;

    public RecipeAdapter(List<RecipeObject> list, ClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout

        View photoView = inflater
                .inflate(R.layout.recipe_card,
                        parent, false);

        return new RecipeViewHolder(photoView);
    }

    @Override
    public void onBindViewHolder(final RecipeViewHolder viewHolder, final int position)
    {
        int index = viewHolder.getAdapterPosition();
        viewHolder.recipeName
                .setText(list.get(position).name);
        // change ingredients and tags from array to single string

        String ingredients = "Ingrediants: ";
        if (list.get(position).ingredients.length > 0){
            ingredients += list.get(position).ingredients[0];
        }

        String tags = "Tags: ";
        if (list.get(position).tags.length > 0){
            tags += list.get(position).tags[0];
        }

        String instructions = "Instructions: ";
        if (list.get(position).instructions.length > 0){
            instructions += list.get(position).instructions[0];
        }


        for (int i = 1; i < list.get(position).ingredients.length; i++) {
            ingredients = ingredients + ", " + list.get(position).ingredients[i];
        }
        for (int i = 1; i < list.get(position).tags.length; i++) {
            tags = tags + ", " + list.get(position).tags[i];
        } 
        for (int i = 1; i < list.get(position).instructions.length; i++) {
            instructions = instructions + ", " + list.get(position).instructions[i];
        } 

        viewHolder.recipeIngredients
                .setText(ingredients);
        viewHolder.recipeTags
                .setText(tags);
        viewHolder.recipeInstructions
                .setText(instructions);

        Uri imageUri = Uri.parse(list.get(position).imageURL);
        viewHolder.recipeImage.setImageURI(imageUri);
        Picasso.get().load(imageUri).fit().centerCrop().into(viewHolder.recipeImage);


        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                listener.click(index);
                System.out.println("Clicked on " + index);
                Navigation.findNavController(view).navigate(R.id.navigation_recipe_page);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
