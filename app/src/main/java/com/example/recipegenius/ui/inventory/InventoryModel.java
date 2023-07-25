package com.example.recipegenius.ui.inventory;

import com.example.recipegenius.ui.cart.CartIngredientModel;
import com.example.recipegenius.ui.ingredient.IngredientModel;
import com.example.recipegenius.ui.ingredient.MeasureUnit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public void addIngredient(CartIngredientModel ing){
        if(!this.containsKey(ing.name)){
            this.put(ing.name, new CartIngredientModel(ing.name, ing.quantity, ing.unit, ing.price, ing.store));
        }
        else {
            IngredientModel olding = this.get(ing.name);
            olding.addQuantity(ing.quantity,ing.unit);
            this.put(ing.name,ing);
        }
    }

    public List<IngredientModel> toList(){
        List<IngredientModel> list = new ArrayList<>();

        list.addAll(this.values());

        return list;
    }

}
