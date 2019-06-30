package com.example.chocolate.model.daos;

import com.example.chocolate.model.entities.CategoriesEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CategoriesDao {

    @Query("SELECT * FROM categories")
    LiveData<List<CategoriesEntity>> getAllCategories();

    @Insert
    void insertCategories(CategoriesEntity... categoriesEntities);

}
