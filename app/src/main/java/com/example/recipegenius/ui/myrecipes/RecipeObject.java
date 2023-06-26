package com.example.recipegenius.ui.myrecipes;

import java.util.Collection;

public class RecipeObject {
    String name;
    String[] ingredients;
    String[] tags;

    RecipeObject(String name,
             String[] ingredients, String[] tags)
    {
        this.name = name;
        this.ingredients = ingredients;
        this.tags = tags;
    }

    public String[] getTags() {
        return this.tags;
    }

    public String[] getIngredients() {
        return this.ingredients;
    }
}
