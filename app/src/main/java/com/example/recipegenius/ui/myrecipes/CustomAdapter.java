package com.example.recipegenius.ui.myrecipes;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<RecipeObject> objectsList;

    public void TransactionListAdapter(Context context, List<Transaction> transactionList) {
        this.context = context;
        this.transactionsList = transactionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final YourObject object = objectsList.get(position);

        holder.textView.setText("Stuff here");
        holder.imageView.setImageResource(R.id.myNiceImage);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        ViewHolder(@NonNull View view) {
            super(view);

            imageView.findViewById(R.id.image_view);
            textView.findViewById(R.id.text_view);

        }
    }
}
