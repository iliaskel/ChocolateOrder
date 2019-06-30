package com.example.chocolate.model.entities;


import android.util.Log;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "order_items")
public class OrderItemsEntity {

    private static final String TAG = "OrderItemsEntity";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int table_id;
    private String product_name;
    private float product_price;
    private String comment;

    public OrderItemsEntity(int table_id, String product_name, float product_price, String comment) {
        this.table_id = table_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public float getProduct_price() {
        return product_price;
    }

    public void setProduct_price(float product_price) {
        this.product_price = product_price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static OrderItemsEntity[] populateOrderItems(){
        Log.d(TAG, "populateOrderItems: entered");
        return new OrderItemsEntity[]{

            new OrderItemsEntity(1,"product1",3.6f,"comment1"),
            new OrderItemsEntity(1,"product1",3.6f,"comment1"),
            new OrderItemsEntity(1,"product1",3.6f,"comment1"),
            new OrderItemsEntity(2,"product1",3.6f,"comment1"),
            new OrderItemsEntity(2,"product1",3.6f,"comment1"),
            new OrderItemsEntity(1,"product1",3.6f,"comment1"),
            new OrderItemsEntity(1,"product1",3.6f,"comment1"),
            new OrderItemsEntity(5,"product1",3.6f,"comment1"),

        };
    }

    @Override
    public String toString() {
        return "OrderItemsEntity{" +
                "id=" + id +
                ", table_id=" + table_id +
                ", product_name='" + product_name + '\'' +
                ", product_price=" + product_price +
                ", comment='" + comment + '\'' +
                '}';
    }
}

