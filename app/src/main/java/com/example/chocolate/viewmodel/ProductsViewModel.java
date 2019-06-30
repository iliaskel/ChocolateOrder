package com.example.chocolate.viewmodel;

import android.app.Application;

import com.example.chocolate.model.entities.ProductsEntity;
import com.example.chocolate.model.Repository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import io.reactivex.Maybe;

public class ProductsViewModel extends AndroidViewModel {

    private Repository repository;

    public ProductsViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public Maybe<List<ProductsEntity>> getProductsFromCategory(int categoryId){
        return repository.getProductsFromCategory(categoryId);
    }

}
