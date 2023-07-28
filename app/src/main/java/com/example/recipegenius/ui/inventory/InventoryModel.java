package com.example.recipegenius.ui.inventory;

import android.graphics.LinearGradient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.recipegenius.ui.cart.ordercart.CartIngredientModel;
import com.example.recipegenius.ui.ingredient.IngredientFactory;
import com.example.recipegenius.ui.ingredient.InventoryIngredientModel;
import com.example.recipegenius.ui.ingredient.IngredientViewModel;
import com.example.recipegenius.ui.ingredient.MeasureUnit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class InventoryModel<T> extends HashMap<String, T> {

    public void addIngredient(String name, double amount){
        if(!this.containsKey(name)){
            this.put(name, (T) IngredientFactory.valueOf(name, amount, MeasureUnit.GRAM));
        }
        else {
            InventoryIngredientModel ing = (InventoryIngredientModel) this.get(name);
            ing.addQuantity(amount,MeasureUnit.GRAM);
            this.put(name, (T) ing);
        }
    }

    public void addIngredient(String name, double amount, MeasureUnit unit){
        if(!this.containsKey(name)){
            this.put(name, (T) IngredientFactory.valueOf(name, amount, MeasureUnit.GRAM));
        }
        else {
            InventoryIngredientModel ing = (InventoryIngredientModel) this.get(name);
            ing.addQuantity(amount,unit);
            this.put(name, (T) ing);
        }
    }

    public void addIngredient(String name, double amount, MeasureUnit unit, double price){
        if(!this.containsKey(name)){
            this.put(name, (T) IngredientFactory.valueOf(name, amount, MeasureUnit.GRAM, price));
        }
        else {
            CartIngredientModel ing = (CartIngredientModel) this.get(name);
            ing.quantity = amount;
            this.put(name, (T) ing);
        }
    }

    public List<T> toList(){
        List<T> list = new ArrayList<>();

        list.addAll(this.values());

        return list;
    }

}
