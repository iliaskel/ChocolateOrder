package com.example.chocolate.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chocolate.R;
import com.example.chocolate.model.entities.OrderItemsEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TableOrderAdapter extends RecyclerView.Adapter<TableOrderAdapter.OrderItemViewHolder> {

    private List<OrderItemsEntity> mTableOrderList = new ArrayList<>();

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.table_order_fragment_order_list_item,parent,false);

        return new OrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (mTableOrderList==null)
            return 0;
        return mTableOrderList.size();
    }

    public void setOrderItemsList(List<OrderItemsEntity> orderItemsEntities) {
        mTableOrderList = orderItemsEntities;
        notifyDataSetChanged();
    }

    public int getSwipedItemId(int adapterPosition) {
        int orderItemId = mTableOrderList.get(adapterPosition).getId();
        return orderItemId;
    }

    public int getSwipedItemTableId(int adapterPosition) {
        return mTableOrderList.get(adapterPosition).getTable_id();
    }

    public void deleteSwipedItem(int adapterPosition) {
        mTableOrderList.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }

    public float getBasketSum() {
        float basketSum = 0.0f;

        for(OrderItemsEntity orderItemsEntity:mTableOrderList )
            basketSum += orderItemsEntity.getProduct_price();

        return basketSum;
    }

    public boolean isListEmpty() {
        return (mTableOrderList.isEmpty());
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder {

        TextView mOrderItemName,mOrderItemPrice;

        OrderItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mOrderItemName = itemView.findViewById(R.id.basket_items_fragment_order_item_name_tv);
            mOrderItemPrice = itemView.findViewById(R.id.basket_items_fragment_order_item_price_tv);
        }

        void bind(int position) {
            setOrderItemName(position);
            setOrderItemPrice(position);
        }

        private void setOrderItemName(int position) {
            StringBuilder orderItemNameBuilder = new StringBuilder();
            orderItemNameBuilder.append(mTableOrderList.get(position).getProduct_name());
            if(mTableOrderList.get(position).getComment().length()>1)
                orderItemNameBuilder.append(" - ")
                .append(mTableOrderList.get(position).getComment());

            mOrderItemName.setText(orderItemNameBuilder);
        }
        private void setOrderItemPrice(int position) {
            String itemPriceString = String.valueOf(mTableOrderList.get(position).getProduct_price());
            itemPriceString = itemPriceString.substring(0,3);
            mOrderItemPrice.setText(String.valueOf(itemPriceString));
        }
    }
}
