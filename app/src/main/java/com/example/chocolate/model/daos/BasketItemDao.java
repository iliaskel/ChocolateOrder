package com.example.chocolate.model.daos;


import com.example.chocolate.model.entities.BasketItemEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Maybe;

@Dao
public interface BasketItemDao {

    @Query("SELECT * FROM basket WHERE table_id LIKE :tableId")
    Maybe<List<BasketItemEntity>> getBasketItemsForTable(int tableId);

    @Insert
    void addItemToBasket(BasketItemEntity basketItemEntity);

    @Query("DELETE FROM basket WHERE id LIKE :basketItemId")
    void deleteItemFromBasket(int basketItemId);

    @Delete
    void deleteBasketItems(List<BasketItemEntity> basketItemEntities);
}
