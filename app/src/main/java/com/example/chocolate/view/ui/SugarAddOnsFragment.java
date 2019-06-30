package com.example.chocolate.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.example.chocolate.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SugarAddOnsFragment extends Fragment {


    private RadioButton mNosugarRb,mMediumSugarRb,mSweetRb;
    private CheckBox mSaccharinCb,mBrownSugarCb,mDecaffeineCb,mMilkCb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sugar_add_ons_fragment,container,false);

        mNosugarRb = view.findViewById(R.id.sugar_fragment_no_sugar_rb);
        mMediumSugarRb = view.findViewById(R.id.sugar_fragment_medium_sugar_rb);
        mSweetRb = view.findViewById(R.id.sugar_fragment_sweet_sugar_rb);

        mSaccharinCb = view.findViewById(R.id.sugar_fragment_saccharin_sugar_cb);
        mBrownSugarCb = view.findViewById(R.id.sugar_fragment_brown_sugar_cb);
        mDecaffeineCb = view.findViewById(R.id.sugar_fragment_decaffeine_cb);
        mMilkCb = view.findViewById(R.id.sugar_fragment_milk_cb);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public String getSugarDetails() {
        StringBuilder sugarDetailsBuilder = new StringBuilder();
        if(mNosugarRb.isChecked())
            sugarDetailsBuilder.append(mNosugarRb.getText());
        else if(mMediumSugarRb.isChecked())
            sugarDetailsBuilder.append(mMediumSugarRb.getText());
        else
            sugarDetailsBuilder.append(mSweetRb.getText());

        if(mSaccharinCb.isChecked()){
            sugarDetailsBuilder.append(",")
                    .append(mSaccharinCb.getText());
        }
        if(mBrownSugarCb.isChecked()){
            sugarDetailsBuilder.append(",")
                    .append(mBrownSugarCb.getText());
        }
        if(mDecaffeineCb.isChecked()){
            sugarDetailsBuilder.append(",")
                    .append(mDecaffeineCb.getText());
        }
        if(mMilkCb.isChecked()){
            sugarDetailsBuilder.append(",")
                    .append(mMilkCb.getText());
        }

        return String.valueOf(sugarDetailsBuilder);
    }

    @Override
    public void onStart() {
        super.onStart();
        mNosugarRb.setChecked(true);

        mSaccharinCb.setChecked(false);
        mBrownSugarCb.setChecked(false);
        mDecaffeineCb.setChecked(false);
        mMilkCb.setChecked(false);
    }
}
