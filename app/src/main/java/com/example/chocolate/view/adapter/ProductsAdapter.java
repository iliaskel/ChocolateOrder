package com.example.chocolate.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chocolate.R;
import com.example.chocolate.model.entities.ProductsEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    public interface ProductsClickListener{
        void onProductClicked(ProductsEntity product);
    }

    private List<ProductsEntity> mProductsList = new ArrayList<>();

    private ProductsClickListener mProductsClickListener;

    public ProductsAdapter(ProductsClickListener productsClickListener) {
        mProductsClickListener = productsClickListener;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.products_fragment_products_list_item,parent,false);

        return new ProductsViewHolder(view,mProductsClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(mProductsList==null)
            return 0;
        else
            return mProductsList.size();
    }

    public void updateProductsList(List<ProductsEntity> productsEntities) {
        mProductsList = productsEntities;
        notifyDataSetChanged();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mProductTextView;
        private TextView mPriceTextView;
        private ProductsClickListener mProductsClickListener;

        ProductsViewHolder(@NonNull View itemView, ProductsClickListener productsClickListener) {
            super(itemView);
            mProductTextView = itemView.findViewById(R.id.products_fragment_recycler_view_product_name_tv);
            mPriceTextView = itemView.findViewById(R.id.products_fragment_recycler_view_product_price_tv);
            this.mProductsClickListener = productsClickListener;
            itemView.setOnClickListener(this);
        }

        void bind(int position) {
            mProductTextView.setText(mProductsList.get(position).getName());
            mPriceTextView.setText(String.valueOf(mProductsList.get(position).getPrice()) + " €");
        }

        @Override
        public void onClick(View v) {
            mProductsClickListener.onProductClicked(mProductsList.get(getAdapterPosition()));
        }
    }
}