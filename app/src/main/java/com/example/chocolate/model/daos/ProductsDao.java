package com.example.chocolate.model.daos;

import com.example.chocolate.model.entities.ProductsEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Maybe;

@Dao
public interface ProductsDao {

    @Query("SELECT * FROM products WHERE category_id LIKE :categoryId")
    Maybe<List<ProductsEntity>> getProductsFromCategory(int categoryId);

    @Insert
    void insertProducts(ProductsEntity... productsEntities);

}
