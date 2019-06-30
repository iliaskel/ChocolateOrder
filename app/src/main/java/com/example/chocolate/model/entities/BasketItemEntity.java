package com.example.chocolate.model.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "basket")
public class BasketItemEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int table_id;
    private String productName;
    private float productPrice;
    private String comment;
    private int quantity;

    public BasketItemEntity(int table_id, String productName,float productPrice, String comment, int quantity) {
        this.table_id = table_id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.comment = comment;
        this.quantity = quantity;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "BasketItemEntity{" +
                "id=" + id +
                ", table_id=" + table_id +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", comment='" + comment + '\'' +
                ", quantity=" + quantity +
                '}';
    }


}
