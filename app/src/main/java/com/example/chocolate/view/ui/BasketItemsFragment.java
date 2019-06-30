package com.example.chocolate.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.chocolate.R;
import com.example.chocolate.model.entities.BasketItemEntity;
import com.example.chocolate.view.adapter.BasketItemsAdapter;
import com.example.chocolate.viewmodel.BasketItemsViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BasketItemsFragment extends Fragment {

    public interface BasketItemsListener{
        void onAddProductClicked();
        void printOrder(List<BasketItemEntity> itemsToPrint);
        void basketIsEmpty();
    }

    private static final String TAG = "BasketItemsFragment";

    BasketItemsListener mBasketItemsListener;

    //source fragment to determine if the Basket.isEmpty() will be checked;
    private String mSourceFragment;

    private BasketItemsAdapter mBasketItemsAdapter;
    private BasketItemsViewModel mBasketItemsViewModel;
    private RecyclerView mBasketItemsRecyclerView;

    private CompositeDisposable disposables = new CompositeDisposable();

    private TextView mTableNameTv,mSumPriceTv;
    private Button mAddItemBtn,mPrintOrderBtn;

    private int mTableId;
    private String mTableName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.basket_items_fragment_layout,container,false);

        mBasketItemsRecyclerView = view.findViewById(R.id.basket_items_fragment_tables_order_rv);
        mTableNameTv = view.findViewById(R.id.basket_items_fragment_table_name_tv);
        mSumPriceTv = view.findViewById(R.id.basket_items_fragment_sum_price_tv);
        mAddItemBtn = view.findViewById(R.id.basket_items_fragment_add_order_item_btn);
        mPrintOrderBtn = view.findViewById(R.id.basket_items_fragment_print_order_btn);

        mBasketItemsRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        mBasketItemsRecyclerView.setHasFixedSize(false);
        mBasketItemsAdapter = new BasketItemsAdapter();
        mBasketItemsRecyclerView.setAdapter(mBasketItemsAdapter);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListeners();
        mBasketItemsViewModel = ViewModelProviders.of(this).get(BasketItemsViewModel.class);
        mTableNameTv.setText("Καλάθι - " + mTableName);
    }

    public void setTableId(int tableId) {
        this.mTableId = tableId;
    }

    public void setTableName(String tableName){
        this.mTableName = tableName;
    }

    private void setListeners() {

        mAddItemBtn.setOnClickListener(v-> mBasketItemsListener.onAddProductClicked());
        mPrintOrderBtn.setOnClickListener(v->{
            List<BasketItemEntity> basketList = mBasketItemsAdapter.getBasketList();
            if (basketList.isEmpty())
                return;
            int tableId = basketList.get(0).getTable_id();
            mBasketItemsViewModel.addOrderItems(basketList);
            mBasketItemsViewModel.deleteBasketItems(basketList);
            mBasketItemsViewModel.updateTableAvailability(tableId,false);
            mBasketItemsListener.printOrder(basketList);
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedItemsId = mBasketItemsAdapter.getSwipedItemsId(viewHolder.getAdapterPosition());
                mBasketItemsViewModel.deleteItemFromBasket(swipedItemsId);
                mSumPriceTv.setText(String.valueOf(mBasketItemsAdapter.getBasketSum()));
            }
        });

        itemTouchHelper.attachToRecyclerView(mBasketItemsRecyclerView);
    }

    private void subscribeForOrders() {

        mBasketItemsViewModel.getBasketItemsForTable(mTableId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<BasketItemEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(List<BasketItemEntity> basketItemEntities) {
                        mBasketItemsAdapter.updateOrderItemsList(basketItemEntities);
                        if(mSourceFragment.equals(TableOrderFragment.class.getSimpleName()))
                        if(mBasketItemsAdapter.isEmpty())
                            mBasketItemsListener.basketIsEmpty();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void subscribeForTableOrderSumPrice(){
        Log.d(TAG, "subscribeForTableOrderSumPrice: entered");
        mBasketItemsViewModel.getSumPriceForTableOrder(mTableId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: entered");
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(String s) {
                        Log.d(TAG, "onSuccess: entered");
                        mSumPriceTv.setText(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: entered",e
                        );
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: entered");
                    }
                });
    }

    public void setSourceFragment(String sourceFragment){
        mSourceFragment = sourceFragment;
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");

        subscribeForOrders();
        subscribeForTableOrderSumPrice();
    }

    @Override
    public void onStop() {
        super.onStop();
        disposables.clear();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof BasketItemsListener)
            mBasketItemsListener = (BasketItemsListener)context;
        else
            throw new RuntimeException(context.toString() +
                    "must implement BasketItemsFragment.BasketItemsListener");
    }
}
