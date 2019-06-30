package com.example.chocolate.viewmodel;

import android.app.Application;

import com.example.chocolate.model.entities.BasketItemEntity;
import com.example.chocolate.model.entities.OrderItemsEntity;
import com.example.chocolate.model.Repository;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import io.reactivex.Maybe;

public class BasketItemsViewModel extends AndroidViewModel {
    private static final String TAG = "BasketItemsViewModel";

    private Repository repository;

    public BasketItemsViewModel(@NonNull Application application) {
        super(application);

        repository = Repository.getInstance(application);
    }

    public Maybe<List<BasketItemEntity>> getBasketItemsForTable(int tableId){
        return repository.getBasketItemsForTable(tableId);
    }

    public Maybe<String> getSumPriceForTableOrder(int tableId){
        return repository.getBasketItemsForTable(tableId)
                .map(basketItemEntities -> {
                    float sum = 0.0f;
                    for (BasketItemEntity basketItemEntity : basketItemEntities)
                        sum += basketItemEntity.getProductPrice();

                    String sumString = String.valueOf(sum);
                    if(sumString.length()>4)
                        sumString = sumString.substring(0,4);

                    return sumString;
                });
    }

    public void addItemToBasket(BasketItemEntity basketItem) {
        repository.addItemToBasket(basketItem);
    }

    public void deleteItemFromBasket(int basketItemId){
        repository.deleteItemFromBasket(basketItemId);
    }

    public void addOrderItems(List<BasketItemEntity> basketList) {
        //Transferring the order from the basket to the table's complete order should separate
        //the order items that have quantity>1.
        //This is happening because the client wants to be able to get paid from each item separately

        List<OrderItemsEntity> orderItemsEntities = new ArrayList<>();
        for(BasketItemEntity basketItemEntity : basketList){
            if (basketItemEntity.getQuantity()>1){
                float dividedPrice = basketItemEntity.getProductPrice()/basketItemEntity.getQuantity();
                for(int i = 1; i<=basketItemEntity.getQuantity() ;i++){
                    orderItemsEntities.add(
                            new OrderItemsEntity(
                                    basketItemEntity.getTable_id(),
                                    basketItemEntity.getProductName(),
                                    dividedPrice,
                                    basketItemEntity.getComment()
                            ));
                }
            }
            else{
                orderItemsEntities.add(
                        new OrderItemsEntity(
                                basketItemEntity.getTable_id(),
                                basketItemEntity.getProductName(),
                                basketItemEntity.getProductPrice(),
                                basketItemEntity.getComment()
                        ));
            }
        }

       repository.addOrderItems(orderItemsEntities);
    }

    public void deleteBasketItems(List<BasketItemEntity> basketItemEntities){
        repository.deleteBasketItems(basketItemEntities);
    }

    public void updateTableAvailability(int tableId, boolean isOpen) {
        repository.updateTableAvailability(tableId,isOpen);
    }
}
