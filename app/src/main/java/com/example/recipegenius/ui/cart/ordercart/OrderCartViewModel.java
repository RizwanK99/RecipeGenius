package com.example.recipegenius.ui.cart.ordercart;

import androidx.lifecycle.ViewModel;

public class OrderCartViewModel extends ViewModel {
    String name = "New Cart";
    String store = "";
    String service = "";

    OrderCartViewModel(String store, String service){
        this.store = store;
        this.service = service;
    }
}