package com.example.recipegenius.ui.cart;

import com.example.recipegenius.ui.ingredient.IngredientModel;
import com.example.recipegenius.ui.ingredient.MeasureUnit;

public class CartIngredientModel extends IngredientModel {
    public double price = 0;
    public String store = "";

    public CartIngredientModel(String name, double amount, MeasureUnit unit, double price, String store) {
        super(name, amount, unit);
        this.price = price;
        this.store = store;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
