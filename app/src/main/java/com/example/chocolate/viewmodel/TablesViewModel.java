package com.example.chocolate.viewmodel;

import android.app.Application;

import com.example.chocolate.model.entities.TablesEntity;
import com.example.chocolate.model.Repository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TablesViewModel extends AndroidViewModel {

    Repository repository;
    LiveData<List<TablesEntity>> allTables;

    public TablesViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
        allTables = repository.getAllTables();
    }

    public LiveData<List<TablesEntity>> getAllTables(){
        return allTables;
    }


}
