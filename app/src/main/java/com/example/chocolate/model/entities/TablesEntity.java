package com.example.chocolate.model.entities;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tables")
public class TablesEntity {

    private static final String TAG = "TablesEntity";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private boolean isOpen;

    public TablesEntity() {
        isOpen = true;
    }

    @Ignore
    public TablesEntity(boolean isOpen){
        this.isOpen = isOpen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public static List<TablesEntity> populateTables(){
        List<TablesEntity> tablesEntityList = new ArrayList<>();
        Log.d(TAG, "populateTables: ");
        for(int i=0; i<24; i++){
            tablesEntityList.add(new TablesEntity());
        }
        return tablesEntityList;
    }

    @Override
    public String toString() {
        return "TablesEntity{" +
                "id=" + id +
                ", isOpen=" + isOpen +
                '}';
    }
}
