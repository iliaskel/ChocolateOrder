package com.example.chocolate.viewmodel;

import android.app.Application;

import com.example.chocolate.model.entities.CategoriesEntity;
import com.example.chocolate.model.Repository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CategoriesViewModel extends AndroidViewModel {

    Repository repository;
    LiveData<List<CategoriesEntity>> mCategoriesList;

    public CategoriesViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
        mCategoriesList = repository.getAllCategories();
    }

    public LiveData<List<CategoriesEntity>> getAllCategories(){
        return mCategoriesList;
    }
}
