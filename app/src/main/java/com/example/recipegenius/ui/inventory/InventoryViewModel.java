package com.example.recipegenius.ui.inventory;

import static com.example.recipegenius.ui.ingredient.MeasureUnit.GRAM;

import androidx.lifecycle.ViewModel;

import com.example.recipegenius.ui.ingredient.IngredientViewModel;
import com.example.recipegenius.ui.ingredient.MeasureUnit;

import java.util.HashMap;

public class InventoryViewModel extends ViewModel {
    private InventoryModel inventory = new InventoryModel();
    public void addIngredient(String name, double amount, MeasureUnit unit){
        inventory.addIngredient(name,amount,unit);
    }

    public void addIngredient(String name, double amount){
        inventory.addIngredient(name,amount,MeasureUnit.GRAM);
    }

    public HashMap<String, IngredientViewModel> getInventory(){
        return inventory;
    }
}