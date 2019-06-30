package com.example.chocolate.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.chocolate.R;
import com.example.chocolate.model.entities.IceCreamEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IceCreamAdapter extends RecyclerView.Adapter<IceCreamAdapter.IceCreamViewHolder> {

    private static final String TAG = "IceCreamAdapter";

    private List<IceCreamEntity> mAllIceCreamsList = new ArrayList<>();
    private List<Integer> mIceCreamQuantityList = new ArrayList<>(Collections.nCopies(16, 0));

    @NonNull
    @Override
    public IceCreamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ice_cream_fragment_list_item,parent,false);

        return new IceCreamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IceCreamAdapter.IceCreamViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mAllIceCreamsList.size();
    }

    public void updateIceCreamList(List<IceCreamEntity> iceCreamEntities) {
        mAllIceCreamsList = iceCreamEntities;
        notifyDataSetChanged();
    }

    public String getIceCreamFlavours() {
        StringBuilder iceCreamFlavoursStringBuilder = new StringBuilder();

        boolean isFirst = true;
        for (int i = 0 ; i<mAllIceCreamsList.size() ; i++){
            if(mIceCreamQuantityList.get(i)>0){
                if (isFirst)
                {
                    iceCreamFlavoursStringBuilder.append("( ");
                    isFirst=false;
                }
                iceCreamFlavoursStringBuilder.append("x")
                .append(mIceCreamQuantityList.get(i))
                .append(" ")
                .append(mAllIceCreamsList.get(i).getName())
                .append(" ");
            }
        }
        if(!isFirst)
            iceCreamFlavoursStringBuilder.append(")");
        return String.valueOf(iceCreamFlavoursStringBuilder);
    }

    public float getIceCreamPriceForSiropiasta() {
        float iceCreamPrice = 0.0f;
        for (int i = 0 ; i<mAllIceCreamsList.size() ; i++ )
        {
            if(mIceCreamQuantityList.get(i)>0)
                iceCreamPrice += mIceCreamQuantityList.get(i)*1.00f;
        }
        return iceCreamPrice;
    }

    public float getIceCreamPrice() {
        float iceCreamPrice = 0.0f;
        for (int i = 0 ; i<mAllIceCreamsList.size() ; i++ )
        {
            if(mIceCreamQuantityList.get(i)>0)
                iceCreamPrice += mIceCreamQuantityList.get(i)*1.50f;
        }
        return iceCreamPrice;
    }

    class IceCreamViewHolder extends RecyclerView.ViewHolder{

        private TextView mFlavourTv,mFlavourQuantityTv;
        private Button mIncreaseBtn,mDecreaseBtn;

        IceCreamViewHolder(@NonNull View itemView) {
            super(itemView);
            mFlavourTv = itemView.findViewById(R.id.ice_cream_fragment_flavour_tv);
            mFlavourQuantityTv = itemView.findViewById(R.id.ice_cream_fragment_quantity_tv);
            mIncreaseBtn = itemView.findViewById(R.id.ice_cream_fragment_increase_btn);
            mDecreaseBtn = itemView.findViewById(R.id.ice_cream_fragment_decrease_btn);

        }

        void bind(int position) {
            mFlavourQuantityTv.setText(String.valueOf(0));
            mFlavourTv.setText(mAllIceCreamsList.get(position).getName());
            setListeners();
        }

        private void setListeners() {
            Log.d(TAG, "setListeners: ");
            mIncreaseBtn.setOnClickListener(this::onClickIncreaseButton);
            mDecreaseBtn.setOnClickListener(this::onClickDecreaseButton);

        }

        private void onClickDecreaseButton(View view) {

            int quantity = mIceCreamQuantityList.get(getAdapterPosition());
            if(quantity<=0)
                return;
            quantity --;
            mIceCreamQuantityList.set(getAdapterPosition(),quantity);
            mFlavourQuantityTv.setText(String.valueOf(quantity));

        }

        private void onClickIncreaseButton(View view) {
            int quantity = mIceCreamQuantityList.get(getAdapterPosition());
            if (quantity>8)
                return;
            quantity ++;
            mIceCreamQuantityList.set(getAdapterPosition(),quantity);
            mFlavourQuantityTv.setText(String.valueOf(quantity));
        }


    }
}
