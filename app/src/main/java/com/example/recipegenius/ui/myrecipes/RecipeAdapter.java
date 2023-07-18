package com.example.recipegenius.ui.myrecipes;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences;

import androidx.recyclerview.widget.RecyclerView;
import androidx.navigation.Navigation;

import com.example.recipegenius.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.*;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    List<RecipeObject> list = Collections.emptyList();

    Context context;
    ClickListener listener;

    public RecipeAdapter(List<RecipeObject> list, ClickListener listener, Context context) {
        this.list = list;
        this.listener = listener;
        this.context = context;
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
    public void onBindViewHolder(final RecipeViewHolder viewHolder, final int position) {
        int index = viewHolder.getAdapterPosition();
        viewHolder.recipeName
                .setText(list.get(position).name);
        // change ingredients and tags from array to single string

        String ingredients = "Ingrediants: ";
        if (list.get(position).ingredients.length > 0) {
            ingredients += list.get(position).ingredients[0];
        }

        String tags = "Tags: ";
        if (list.get(position).tags.length > 0) {
            tags += list.get(position).tags[0];
        }

        String instructions = "Instructions: ";
        if (list.get(position).instructions.length > 0) {
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

        // viewHolder.recipeIngredients
        // .setText(ingredients);
        // viewHolder.recipeTags
        // .setText(tags);
        // viewHolder.recipeInstructions
        // .setText(instructions);

        Uri imageUri = Uri.parse(list.get(position).imageURL);
        viewHolder.recipeImage.setImageURI(imageUri);
        Picasso.get().load(imageUri).fit().centerCrop().into(viewHolder.recipeImage);

        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.click(index);
                System.out.println("Clicked on " + index);
                SharedPreferences currentRecipe = context.getSharedPreferences("currentRecipe", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = currentRecipe.edit();
                editor.putString("r_name", list.get(position).name);

                editor.putString("r_imageURL", list.get(position).imageURL);

                Set<String> set_ingr = new HashSet<String>();
                for (int i = 0; i < list.get(position).ingredients.length; i++) {
                    set_ingr.add(list.get(position).ingredients[i]);
                }

                Set<String> set_inst = new HashSet<String>();
                for (int i = 0; i < list.get(position).instructions.length; i++) {
                    set_inst.add(list.get(position).instructions[i]);
                }

                Set<String> set_tags = new HashSet<String>();
                for (int i = 0; i < list.get(position).tags.length; i++) {
                    set_tags.add(list.get(position).tags[i]);
                }

                editor.putStringSet("r_ingredients", set_ingr);
                editor.putStringSet("r_instructions", set_inst);
                editor.putStringSet("r_tags", set_tags);

                editor.apply();
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
