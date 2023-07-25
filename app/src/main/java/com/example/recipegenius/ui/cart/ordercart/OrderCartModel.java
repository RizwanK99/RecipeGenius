package com.example.recipegenius.ui.cart.ordercart;

public class OrderCartModel {
    public String name = "New Cart";
    public String store = "";
    public String service = "";

    public OrderCartModel(String store, String service){
        this.store = store;
        this.service = service;
    }
}
