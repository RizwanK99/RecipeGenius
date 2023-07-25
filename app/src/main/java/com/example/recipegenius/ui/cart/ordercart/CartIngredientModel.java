package com.example.recipegenius.ui.cart.ordercart;

import com.example.recipegenius.ui.ingredient.IngredientModel;
import com.example.recipegenius.ui.ingredient.MeasureUnit;

public class CartIngredientModel extends IngredientModel {
    double price = 0;

    public CartIngredientModel(String name, double quantity, MeasureUnit unit, double price){
        super(name, quantity, unit);
        this.price = price;
    }
}
