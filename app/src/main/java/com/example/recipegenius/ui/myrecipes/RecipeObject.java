package com.example.recipegenius.ui.myrecipes;

import java.util.Collection;

public class RecipeObject {
    String name;
    String[] ingredients;
    String[] instructions;
    String[] tags;
    String imageURL;

    RecipeObject(String name, String[] ingredients, String[] instructions, String[] tags, String imageURL) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.tags = tags;
        this.imageURL = imageURL;
    }

    public String[] getTags() {
        return this.tags;
    }

    public String[] getIngredients() {
        return this.ingredients;
    }

    public String getName() {
        return this.name;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public String [] getInstructions() {
        return this.instructions;
    }
}
