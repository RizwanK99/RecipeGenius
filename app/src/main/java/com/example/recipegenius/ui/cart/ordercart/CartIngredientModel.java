package com.example.recipegenius.ui.cart.ordercart;

import com.example.recipegenius.ui.ingredient.IngredientFactory;
import com.example.recipegenius.ui.ingredient.MeasureUnit;

public class CartIngredientModel extends IngredientFactory {
    public double price;

    public CartIngredientModel(String name, double quantity, MeasureUnit unit, double price){
        super(name,quantity,unit);
        this.price = price;
    }
}
