package com.example.recipegenius.ui.inventory;

import com.example.recipegenius.ui.ingredient.IngredientViewModel;
import com.example.recipegenius.ui.ingredient.MeasureUnit;

import java.util.HashMap;

public class InventoryModel extends HashMap<String, IngredientViewModel> {

    public void addIngredient(String name, double amount, MeasureUnit unit){
        if(!this.containsKey(name)){
            this.put(name, new IngredientViewModel(name, amount, unit));
        }
        else {
            IngredientViewModel ing = this.get(name);
            ing.addQuantity(amount,unit);
            this.put(name,ing);
        }
    }

}
