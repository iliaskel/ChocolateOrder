package com.example.chocolate.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.chocolate.R;
import com.example.chocolate.model.entities.IceCreamEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MilkshakeAdapter extends RecyclerView.Adapter<MilkshakeAdapter.MilkshakeViewHolder> {

    private static final String TAG = "MilkshakeAdapter";

    private List<IceCreamEntity> mAllIceCreamsList = new ArrayList<>();
    private List<Boolean> mFlavoursChecked = new ArrayList<>(Collections.nCopies(16, false));

    @NonNull
    @Override
    public MilkshakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.milkshake_fragment_list_item,parent,false);


        return new MilkshakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MilkshakeViewHolder holder, int position) {
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

    public String getSelectedFlavours() {
        StringBuilder iceCreamFlavoursStringBuilder = new StringBuilder();
        int flavoursChecked = 0;
        for (int i=0;i<mAllIceCreamsList.size();i++){
            if(mFlavoursChecked.get(i)){
                if(flavoursChecked>0)
                    iceCreamFlavoursStringBuilder.append(",");
                iceCreamFlavoursStringBuilder.append(mAllIceCreamsList.get(i).getName());
                flavoursChecked++;
            }
        }

        return String.valueOf(iceCreamFlavoursStringBuilder);
    }

    public class MilkshakeViewHolder extends RecyclerView.ViewHolder {

        private CheckBox mFlavourCb;

        MilkshakeViewHolder(@NonNull View itemView) {
            super(itemView);
            mFlavourCb = itemView.findViewById(R.id.milkshake_fragment_flavour_cb);
        }

        public void bind(int position) {
            mFlavourCb.setText(String.valueOf(mAllIceCreamsList.get(position).getName()));
            setListeners();
        }

        private void setListeners() {
            mFlavourCb.setOnClickListener(this::onClick);
        }

        private void onClick(View v) {
            if (mFlavourCb.isChecked())
                mFlavoursChecked.set(getAdapterPosition(),true);
            else
                mFlavoursChecked.set(getAdapterPosition(),false);
        }
    }
}
