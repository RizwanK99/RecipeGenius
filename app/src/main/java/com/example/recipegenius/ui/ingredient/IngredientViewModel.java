package com.example.recipegenius.ui.ingredient;

import androidx.lifecycle.ViewModel;

import java.util.HashMap;

public class IngredientViewModel extends ViewModel {

    public String name = "";
    private double quantity = 0;
    boolean isLiquid = false;
    MeasureUnit unit = null;

    public static HashMap<String,IngredientViewModel> allIngredients;

    //Factory constructor
    public static IngredientViewModel valueOf(String name, double amount, MeasureUnit unit){
        IngredientViewModel ingredient = null;

        if(allIngredients.containsKey(name)){
            ingredient = getStatic(name);
            ingredient.addQuantity(amount, unit);
        }
        else {
            ingredient = new IngredientViewModel(name,amount,unit);
            allIngredients.put(name,ingredient);
        }

        return ingredient;
    }

    //Normal constructor
    private IngredientViewModel(String name, double amount, MeasureUnit unit){
        this.name = name;
        this.quantity = convertValue(amount, unit);
    }

    double addQuantity(double amount, MeasureUnit unit){
        getStatic(this.name).quantity += convertValue(amount,unit);

        return this.quantity;
    }

    double subtractQuantity(double amount, MeasureUnit unit){
        if(getStatic(this.name).quantity - convertValue(amount,unit) < 0){
            return -1;
        }

        getStatic(this.name).quantity -= convertValue(amount,unit);

        return this.quantity;
    }

    double getQuantityGrams(){
        return getStatic(this.name).getQuantityGrams();
    }

    double convertValue(double amount, MeasureUnit unit){

        //Convert all to gram/milliliter
        switch(unit) {
            case OUNCE:
                amount *= 28.3495;
                break;
            case POUND:
                amount *= 453.592;
                break;
            case FLUID_OUNCE:
                amount *= 28.4131;
                break;
            case CUP:
                amount *= 236.588;
            default:
                break;
        }

        return amount;
    }

    private static IngredientViewModel getStatic(String name){
        return allIngredients.get(name);
    }
}