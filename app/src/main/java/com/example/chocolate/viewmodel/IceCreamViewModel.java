package com.example.chocolate.viewmodel;

import android.app.Application;

import com.example.chocolate.model.entities.IceCreamEntity;
import com.example.chocolate.model.Repository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import io.reactivex.Maybe;

public class IceCreamViewModel extends AndroidViewModel {

    private Repository repository;

    public IceCreamViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public Maybe<List<IceCreamEntity>> getIceCreams() {
        return repository.getAllIceCreams();
    }
}
