package com.example.chocolate.model.daos;


import com.example.chocolate.model.entities.IceCreamEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Maybe;

@Dao
public interface IceCreamDao {

    @Query("SELECT * FROM ice_cream")
    Maybe<List<IceCreamEntity>> getAllIceCreams();

    @Insert
    void insertIceCreams(IceCreamEntity... iceCreamEntities);

}
