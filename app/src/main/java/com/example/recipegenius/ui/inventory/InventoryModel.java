package com.example.recipegenius.ui.inventory;

import android.graphics.LinearGradient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.recipegenius.ui.ingredient.IngredientModel;
import com.example.recipegenius.ui.ingredient.IngredientViewModel;
import com.example.recipegenius.ui.ingredient.MeasureUnit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class InventoryModel extends HashMap<String, IngredientModel> {

    public void addIngredient(String name, double amount){
        if(!this.containsKey(name)){
            this.put(name, new IngredientModel(name, amount, MeasureUnit.GRAM));
        }
        else {
            IngredientModel ing = this.get(name);
            ing.addQuantity(amount,MeasureUnit.GRAM);
            this.put(name,ing);
        }
    }

    public void addIngredient(String name, double amount, MeasureUnit unit){
        if(!this.containsKey(name)){
            this.put(name, new IngredientModel(name, amount, unit));
        }
        else {
            IngredientModel ing = this.get(name);
            ing.addQuantity(amount,unit);
            this.put(name,ing);
        }
    }

    public List<IngredientModel> toList(){
        List<IngredientModel> list = new ArrayList<>();

        list.addAll(this.values());

        return list;
    }

}
