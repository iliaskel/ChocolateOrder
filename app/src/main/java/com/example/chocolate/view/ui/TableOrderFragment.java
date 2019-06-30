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
import com.example.chocolate.model.entities.OrderItemsEntity;
import com.example.chocolate.view.adapter.TableOrderAdapter;
import com.example.chocolate.viewmodel.TableOrderViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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

public class TableOrderFragment extends Fragment {

    public interface TableOrderFragmentListener{
        void onGoToTableBasketClicked();

    }

    private static final String TAG = "TableOrderFragment";

    private TableOrderViewModel mTableOrderViewModel;
    private TableOrderAdapter mTableOrderAdapter;
    private RecyclerView mTableOrderRecyclerView;

    private TableOrderFragmentListener mTableOrderFragmentListener;

    private TextView mTableNameTv,mSumPriceTv;
    private Button mPayButton, mBasketBtn;

    private int tableId;
    private String tableName;

    private CompositeDisposable disposables = new CompositeDisposable();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: entered");
        View view = inflater.inflate(R.layout.table_order_fragment_layout,container,false);

        mTableOrderRecyclerView = view.findViewById(R.id.basket_items_fragment_tables_order_rv);
        mTableNameTv = view.findViewById(R.id.basket_items_fragment_table_name_tv);
        mSumPriceTv = view.findViewById(R.id.basket_items_fragment_sum_price_tv);
        mPayButton = view.findViewById(R.id.basket_items_fragment_print_order_btn);
        mBasketBtn = view.findViewById(R.id.basket_items_fragment_add_order_item_btn);

        setListeners(container);

        mTableOrderRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        mTableOrderRecyclerView.setHasFixedSize(false);
        mTableOrderAdapter = new TableOrderAdapter();
        mTableOrderRecyclerView.setAdapter(mTableOrderAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTableOrderViewModel = ViewModelProviders.of(this).get(TableOrderViewModel.class);
        mTableNameTv.setText("Τραπέζι - " + tableName);
    }

    private void setListeners(final ViewGroup container) {
        mPayButton.setOnClickListener(v -> {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(container.getContext());
            a_builder.setMessage("Πληρώθηκες από το τραπέζι?")
                    .setCancelable(false)
                    .setPositiveButton("Ναι", (dialog, which) -> {
                        mTableOrderViewModel.deleteOrderFromTableId(tableId);
                        mTableOrderAdapter.setOrderItemsList(null);
                        mTableOrderViewModel.updateTableAvailability(tableId,true);
                    })
                    .setNegativeButton("Όχι", (dialog, which) -> dialog.cancel());
            AlertDialog alert = a_builder.create();
            alert.setTitle("Πληρωμή");
            alert.show();
        });

        mBasketBtn.setOnClickListener(v ->
                mTableOrderFragmentListener.onGoToTableBasketClicked());


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedTableId = mTableOrderAdapter.getSwipedItemTableId(viewHolder.getAdapterPosition());
                int swipedItemsId = mTableOrderAdapter.getSwipedItemId(viewHolder.getAdapterPosition());
                mTableOrderAdapter.deleteSwipedItem(viewHolder.getAdapterPosition());
                if(mTableOrderAdapter.isListEmpty())
                    mTableOrderViewModel.updateTableIsOpen(swipedTableId,true);
                mTableOrderViewModel.deleteOrderItem(swipedItemsId);
                mSumPriceTv.setText(String.valueOf(mTableOrderAdapter.getBasketSum()));
            }
        });

        itemTouchHelper.attachToRecyclerView(mTableOrderRecyclerView);
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public void setTableName(String tableName){
        this.tableName = tableName;
    }

    private void subscribeForOrders() {
        mTableOrderViewModel.getOrdersForTable(tableId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<OrderItemsEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                       disposables.add(d);
                    }

                    @Override
                    public void onSuccess(List<OrderItemsEntity> orderItemsEntities) {
                        mTableOrderAdapter.setOrderItemsList(orderItemsEntities);
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
        mTableOrderViewModel.getSumPriceForTableOrder(tableId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(String s) {
                        mSumPriceTv.setText(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
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
        if(context instanceof TableOrderFragment.TableOrderFragmentListener){
            mTableOrderFragmentListener = (TableOrderFragment.TableOrderFragmentListener)context;
        }
        else
        {
            throw  new RuntimeException(context.toString()
                    + " must implement TableOrderFragment.TableOrderFragmentListener");
        }
    }
}
