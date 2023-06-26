package com.example.recipegenius.ui.myrecipes;
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
}
