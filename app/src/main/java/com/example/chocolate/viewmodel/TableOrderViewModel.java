package com.example.chocolate.viewmodel;

import android.app.Application;

import com.example.chocolate.model.entities.OrderItemsEntity;
import com.example.chocolate.model.Repository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import io.reactivex.Maybe;

public class TableOrderViewModel extends AndroidViewModel {

    private static final String TAG = "TableOrderViewModel";

    Repository repository;

    public TableOrderViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public Maybe<List<OrderItemsEntity>> getOrdersForTable(int tableId) {
        return repository.getOrdersFromTableId(tableId);
    }

    public Maybe<String> getSumPriceForTableOrder(int tableId){
        return repository.getOrdersFromTableId(tableId)
                .map(orderItemsEntities -> {
                    float sum = 0.0f;
                    for (OrderItemsEntity orderItemsEntity : orderItemsEntities)
                        sum += orderItemsEntity.getProduct_price();

                    String sumString = String.valueOf(sum);
                    if(sumString.length()>4)
                        sumString = sumString.substring(0,4);

                    return sumString;
                });
    }

    public void deleteOrderFromTableId(int tableId){
        repository.deleteOrderFromTable(tableId);
    }

    public void updateTableIsOpen(int tableId,boolean isOpen){
        repository.updateTableAvailability(tableId,isOpen);
    }

    public void deleteOrderItem(int orderItemId){
        repository.deleteOrderItem(orderItemId);
    }

    public void updateTableAvailability(int tableId, boolean isOpen) {
        repository.updateTableAvailability(tableId,isOpen);
    }
}
