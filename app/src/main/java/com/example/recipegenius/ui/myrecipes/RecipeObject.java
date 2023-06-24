package com.example.recipegenius.ui.myrecipes;

public class RecipeObject {
    // each recipe must have a name, store an array of ingredients, and store an array of instructions and store tags of the recipe like dietary restrictions
    private String name;
    private String[] ingredients;
    private String[] instructions;
    private String[] tags;

    // constructor
    public RecipeObject(String name, String[] ingredients, String[] instructions, String[] tags) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String[] getInstructions() {
        return instructions;
    }

    public String[] getTags() {
        return tags;
    }

    // method to check if the recipe contains a certain tag
    public boolean containsTag(String tag) {
        for (String t : tags) {
            if (t.equals(tag)) {
                return true;
            }
        }
        return false;
    }
    
    // method to check if the recipe contains a certain ingredient
    public boolean containsIngredient(String ingredient) {
        for (String i : ingredients) {
            if (i.equals(ingredient)) {
                return true;
            }
        }
        return false;
    }

    // destructor
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
    
}
