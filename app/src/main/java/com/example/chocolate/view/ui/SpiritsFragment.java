package com.example.chocolate.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.chocolate.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SpiritsFragment extends Fragment {

    private CheckBox mLemonadeCb,mCocaColaCb,mOrangeCb,mTonicCb,mIceCheckBox;
    private TextView mProductNameTv;
    private String mProductName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.spirits_fragment_add_ons,container,false);

        mLemonadeCb = view.findViewById(R.id.spirits_fragment_lemonade_cb);
        mCocaColaCb = view.findViewById(R.id.spirits_fragment_coca_cola_cb);
        mOrangeCb = view.findViewById(R.id.spirits_fragment_orange_cb);
        mTonicCb = view.findViewById(R.id.spirits_fragment_tonic_cb);
        mIceCheckBox = view.findViewById(R.id.spirits_fragment_ice_cb);
        mProductNameTv = view.findViewById(R.id.spirits_fragment_product_name_label_tv);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProductNameTv.setText(mProductName);
    }

    public void setProductName(String name) {
        mProductName = name;
    }

    public String getSpiritsDetails() {
        StringBuilder spiritsStringBuilder = new StringBuilder();
        int checkBoxesSelected = 0;
        boolean isFirst = true;
        if(mLemonadeCb.isChecked()){
            if(isFirst){
                spiritsStringBuilder.append("(");
                isFirst =false;
            }
            spiritsStringBuilder.append(mLemonadeCb.getText());
            checkBoxesSelected++;
        }
        if(mCocaColaCb.isChecked()){
            if(isFirst){
                spiritsStringBuilder.append("(");
                isFirst =false;
            }
            if(checkBoxesSelected>0)
                spiritsStringBuilder.append(",");
            spiritsStringBuilder.append(mCocaColaCb.getText());
        }
        if(mOrangeCb.isChecked()){
            if(isFirst){
                spiritsStringBuilder.append("(");
                isFirst =false;
            }
            if(checkBoxesSelected>0)
                spiritsStringBuilder.append(",");
            spiritsStringBuilder.append(mOrangeCb.getText());
        }
        if(mTonicCb.isChecked()){
            if(isFirst){
                spiritsStringBuilder.append("(");
                isFirst =false;
            }
            if(checkBoxesSelected>0)
                spiritsStringBuilder.append(",");
            spiritsStringBuilder.append(mTonicCb.getText());
        }
        if(mIceCheckBox.isChecked()){
            if(isFirst){
                spiritsStringBuilder.append("(");
                isFirst =false;
            }
            if(checkBoxesSelected>0)
                spiritsStringBuilder.append(",");
            spiritsStringBuilder.append(mIceCheckBox.getText());
        }
        if(!isFirst)
            spiritsStringBuilder.append(")");
        return String.valueOf(spiritsStringBuilder);
    }

    @Override
    public void onStart() {
        super.onStart();
        uncheckAddOns();
    }

    private void uncheckAddOns() {
        mLemonadeCb.setChecked(false);
        mCocaColaCb.setChecked(false);
        mOrangeCb.setChecked(false);
        mTonicCb.setChecked(false);
        mIceCheckBox.setChecked(false);
    }
}
