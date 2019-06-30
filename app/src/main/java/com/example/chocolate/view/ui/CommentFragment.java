package com.example.chocolate.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chocolate.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CommentFragment extends Fragment {

    public interface CommentFragmentListener{
        void onAddItemToBasketClicked(String comment,int quantity);
    }

    private static final String TAG = "CommentFragment";

    private CommentFragmentListener mCommentFragmentListener;

    private EditText mCommentEditText;
    private TextView mQuantityTv;
    private Button mAddToBasketBtn,mCancelBtn,mIncreaseQuantity,mDecreaseQuantity;
    private int mQuantity = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comment_fragment_layout,container,false);

        mCommentEditText = view.findViewById(R.id.comment_fragment_product_comment_et);
        mAddToBasketBtn = view.findViewById(R.id.comment_fragment_add_to_basket_btn);
        mCancelBtn = view.findViewById(R.id.comment_fragment_cancel_btn);
        mIncreaseQuantity = view.findViewById(R.id.comment_fragment_increase_btn);
        mDecreaseQuantity = view.findViewById(R.id.comment_fragment_decrease_btn);
        mQuantityTv = view.findViewById(R.id.comment_fragment_quantity_tv_tv);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mQuantityTv.setText(String.valueOf(mQuantity));
        setListeners();
    }

    private void setListeners() {

        mIncreaseQuantity.setOnClickListener(v->{
            if (mQuantity>=10)
                return;
            mQuantity++;
            mQuantityTv.setText(String.valueOf(mQuantity));
        });

        mDecreaseQuantity.setOnClickListener(v->{
            if(mQuantity<=1)
                return;
            mQuantity--;
            mQuantityTv.setText(String.valueOf(mQuantity));
        });

        mAddToBasketBtn.setOnClickListener(v-> {
            String comment;
            if(mCommentEditText.getText()!=null)
                comment = String.valueOf(mCommentEditText.getText());
            else
                comment = "";
            mCommentFragmentListener.onAddItemToBasketClicked(comment,mQuantity);
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        mQuantity = 1;
        mQuantityTv.setText(String.valueOf(mQuantity));
        mCommentEditText.setText("");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CommentFragmentListener)
            mCommentFragmentListener = (CommentFragmentListener)context;
        else
            throw new RuntimeException(context.toString() +
                    " must implement CommentFragment.CommentFragmentListener");
    }
}
