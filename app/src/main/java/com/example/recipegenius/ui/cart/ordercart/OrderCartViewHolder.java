package com.example.recipegenius.ui.cart.ordercart;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recipegenius.R;

public class OrderCartViewHolder extends RecyclerView.ViewHolder{
    public TextView ingredientName;
    public TextView ingredientAmount;
    public TextView ingredientUnit;
    View view;

    public OrderCartViewHolder(View itemView)  {
        super(itemView);
        ingredientName = (TextView) itemView.findViewById(R.id.ing_name);
        //ingredientName = (TextView) itemView.findViewById();

        ingredientAmount = (TextView) itemView.findViewById(R.id.ing_amount);
        ingredientUnit = (TextView) itemView.findViewById(R.id.ing_unit);

        view = itemView;
    }
}
