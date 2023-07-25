package com.example.recipegenius.ui.cart.insidecart;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recipegenius.R;

public class InsideCartViewHolder {
    public class OrderCartViewHolder extends RecyclerView.ViewHolder{
        public TextView orderCartName;
        public TextView orderCartStore;
        public TextView orderCartService;
        public TextView orderCartPrice;
        public TextView orderCartItemCount;
        View view;

        public OrderCartViewHolder(View itemView)  {
            super(itemView);
            orderCartName = (TextView) itemView.findViewById(R.id.ordercart_name);
            //ingredientName = (TextView) itemView.findViewById();

            orderCartStore = (TextView) itemView.findViewById(R.id.ordercart_store);
            orderCartService = (TextView) itemView.findViewById(R.id.ordercart_service);
            orderCartPrice = (TextView) itemView.findViewById(R.id.ordercart_price);
            orderCartItemCount = (TextView) itemView.findViewById(R.id.ordercart_itemcount);

            view = itemView;
        }
    }
}
