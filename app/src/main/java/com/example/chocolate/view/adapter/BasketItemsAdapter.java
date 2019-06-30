package com.example.chocolate.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chocolate.R;
import com.example.chocolate.model.entities.BasketItemEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BasketItemsAdapter extends RecyclerView.Adapter<BasketItemsAdapter.BasketItemsViewHolder> {

    private List<BasketItemEntity> mAllBasketItems = new ArrayList<>();

    @NonNull
    @Override
    public BasketItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.basket_items_fragment_order_list_item,parent,false);

        return new BasketItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BasketItemsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mAllBasketItems.size();
    }

    public void updateOrderItemsList(List<BasketItemEntity> basketItemEntities) {
        mAllBasketItems = basketItemEntities;
        notifyDataSetChanged();
    }

    public int getSwipedItemsId(int adapterPosition) {
        int swipedItemId = mAllBasketItems.get(adapterPosition).getId();
        int swipedItemPosition = mAllBasketItems.get(adapterPosition).getId();
        mAllBasketItems.remove(adapterPosition);
        notifyItemRemoved(swipedItemPosition);
        return swipedItemId;
    }

    public float getBasketSum() {
        float basketSum = 0.0f;

        for(BasketItemEntity basketItemEntity: mAllBasketItems)
            basketSum += basketItemEntity.getProductPrice()*basketItemEntity.getQuantity();

        return basketSum;
    }

    public List<BasketItemEntity> getBasketList() {
        return mAllBasketItems;
    }

    public boolean isEmpty() {
        return mAllBasketItems == null || mAllBasketItems.size() < 1;
    }

    public class BasketItemsViewHolder extends RecyclerView.ViewHolder {

        private TextView mBasketItemName, mBasketItemPrice;

        BasketItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            mBasketItemName = itemView.findViewById(R.id.basket_items_fragment_order_item_name_tv);
            mBasketItemPrice = itemView.findViewById(R.id.basket_items_fragment_order_item_price_tv);
        }

        public void bind(int position) {
            setBasketItemName(position);
            setBasketItemPrice(position);
        }

        private void setBasketItemPrice(int position) {
            String itemPriceString = String.valueOf(mAllBasketItems.get(position).getProductPrice());
            itemPriceString = itemPriceString.substring(0,3);
            mBasketItemPrice.setText(itemPriceString);
        }

        private void setBasketItemName(int position) {
            StringBuilder basketItemNameBuilder = new StringBuilder();
            basketItemNameBuilder.append("x")
                    .append(mAllBasketItems.get(position).getQuantity())
                    .append(" ")
                    .append(mAllBasketItems.get(position).getProductName());

            if(mAllBasketItems.get(position).getComment().length()>1)
                basketItemNameBuilder.append(" - ")
                        .append(mAllBasketItems.get(position).getComment());

            mBasketItemName.setText(basketItemNameBuilder);
        }
    }
}
