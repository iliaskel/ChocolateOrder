package com.example.chocolate.model.daos;

import com.example.chocolate.model.entities.TablesEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TablesDao {

    @Query("SELECT * FROM tables")
    LiveData<List<TablesEntity>> getAllTables();

    @Insert
    void insertTables(List<TablesEntity> tablesEntities);

    @Query("UPDATE tables SET isOpen = :isOpen WHERE id LIKE :tableId")
    void updateTableIsOpen(int tableId ,boolean isOpen);

}
