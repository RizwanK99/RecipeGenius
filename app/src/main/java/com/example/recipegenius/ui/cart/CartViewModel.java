package com.example.recipegenius.ui.cart;

import androidx.lifecycle.ViewModel;

import com.example.recipegenius.ui.ingredient.IngredientModel;
import com.example.recipegenius.ui.ingredient.MeasureUnit;
import com.example.recipegenius.ui.inventory.InventoryModel;

import java.util.List;

public class CartViewModel extends ViewModel {
    InventoryModel ingredients;
    CartViewModel(){
        this.ingredients = ingredients;
    }

    public void addIngredient(String name, double quantity, MeasureUnit unit, double price, String store){
        ingredients.addIngredient(new CartIngredientModel(name,quantity,unit,price,store));
    }

    public int getLength() {
        return ingredients.size();
    }

    public double getTotalPrice(){
        double sum = 0;

        /*for (CartIngredientModel ing : ((List<CartIngredientModel>)ingredients.toList())) {
            sum += ing.price
        }*/

        return sum;
    }
}