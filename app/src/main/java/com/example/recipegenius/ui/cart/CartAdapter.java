package com.example.recipegenius.ui.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipegenius.R;
import com.example.recipegenius.ui.cart.ordercart.OrderCartModel;
import com.example.recipegenius.ui.cart.ordercart.OrderCartViewHolder;
import com.example.recipegenius.ui.ingredient.IngredientModel;
import com.example.recipegenius.ui.myrecipes.ClickListener;

import java.util.Collections;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<OrderCartViewHolder>{

    List<OrderCartModel> list = Collections.emptyList();
    Context context;
    ClickListener listener;

    public CartAdapter(List<OrderCartModel> list, ClickListener listener, Context context) {
        this.list = list;
        this.listener = listener;
        this.context = context;
    }

    public OrderCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout

        View photoView = inflater
                .inflate(R.layout.fragment_order_cart,
                        parent, false);

        return new OrderCartViewHolder(photoView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderCartViewHolder holder, int position) {
        //int index = holder.getAdapterPosition();
        holder.orderCartName.setText(list.get(position).name);
        holder.orderCartStore.setText(list.get(position).store);
        holder.orderCartService.setText(list.get(position).service);
        holder.orderCartPrice.setText(list.get(position).store);
        holder.orderCartItemCount.setText(list.get(position).service);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
