package com.example.recipegenius.ui.ingredient;

import com.example.recipegenius.ui.cart.ordercart.CartIngredientModel;

public class IngredientFactory {
    public String name = "";
    public double quantity = 0;
    public MeasureUnit unit = MeasureUnit.GRAM;
    public static InventoryIngredientModel valueOf(String name, double amount, MeasureUnit unit){

        return new InventoryIngredientModel(name,amount,unit);
    }

    public static CartIngredientModel valueOf(String name, double amount, MeasureUnit unit, double price){

        return new CartIngredientModel(name,amount,unit,price);
    }

    protected IngredientFactory(String name, double quantity, MeasureUnit unit){
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }
}
