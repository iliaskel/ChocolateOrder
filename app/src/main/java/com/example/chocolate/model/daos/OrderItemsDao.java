package com.example.chocolate.model.daos;


import com.example.chocolate.model.entities.OrderItemsEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Maybe;

@Dao
public interface OrderItemsDao {

    @Query("SELECT * FROM order_items WHERE table_id LIKE :tableId")
    Maybe<List<OrderItemsEntity>> getOrderItemsFromTable(int tableId);

    @Query("DELETE FROM order_items WHERE table_id LIKE :tableId")
    void deleteOrderFromTable(int tableId);

    @Insert
    void addOrderItems(List<OrderItemsEntity> orderItemsEntities);

    @Query("DELETE FROM order_items WHERE id LIKE :orderItemId")
    void deleteOrderItem(int orderItemId);

}
