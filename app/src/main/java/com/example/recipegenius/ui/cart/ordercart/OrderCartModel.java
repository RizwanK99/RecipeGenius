package com.example.recipegenius.ui.cart.ordercart;

import com.example.recipegenius.ui.ingredient.IngredientModel;
import com.example.recipegenius.ui.ingredient.MeasureUnit;
import com.example.recipegenius.ui.inventory.InventoryModel;

import java.util.Collections;

public class OrderCartModel {
    public String name = "New Cart";
    public String store = "";
    public String service = "";

    private InventoryModel<CartIngredientModel> ingredients = new InventoryModel();

    public OrderCartModel(String store, String service){
        this.store = store;
        this.service = service;
    }

    public void addIngredient(String name, double quantity, MeasureUnit unit, double price){
        ingredients.addIngredient(name,quantity,unit,price);
    }

    public double getCost(){
        double sum = 0;

        for(CartIngredientModel ing : ingredients.toList()){
            sum += ing.price;
        }

        return sum;
    }

    public int getCartSize(){
        return ingredients.toList().size();
    }
}
