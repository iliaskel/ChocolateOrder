package com.example.chocolate.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chocolate.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProductLabelFragment extends Fragment {

    private TextView mProductLabelTv;
    private String mProductLabel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_label_fragment_layout,container,false);

        mProductLabelTv = view.findViewById(R.id.product_label_fragment_product_label_tv);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mProductLabelTv.setText(mProductLabel);
    }

    public void setProductLabel(String productLabel){
        mProductLabel = productLabel;
    }
}
