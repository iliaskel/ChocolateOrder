package com.example.chocolate.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chocolate.R;
import com.example.chocolate.model.entities.IceCreamEntity;
import com.example.chocolate.view.adapter.MilkshakeAdapter;
import com.example.chocolate.viewmodel.IceCreamViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MilkshakeFragment extends Fragment {

    private static final String TAG = "MilkshakeFragment";

    private MilkshakeAdapter mMilkshakeAdapter;
    private RecyclerView mMilkshakeRecyclerView;
    private IceCreamViewModel mIceCreamViewModel;

    private CompositeDisposable disposables= new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.milkshake_fragment_layout,container,false);

        mMilkshakeRecyclerView = view.findViewById(R.id.milkshake_fragment_rv);
        mMilkshakeRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        mMilkshakeRecyclerView.setHasFixedSize(false);

        mMilkshakeAdapter = new MilkshakeAdapter();
        mMilkshakeRecyclerView.setAdapter(mMilkshakeAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mIceCreamViewModel = ViewModelProviders.of(this).get(IceCreamViewModel.class);
    }

    private void subscribeForIceCreams() {

        mIceCreamViewModel.getIceCreams()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<IceCreamEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(List<IceCreamEntity> iceCreamEntities) {
                        mMilkshakeAdapter.updateIceCreamList(iceCreamEntities);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        subscribeForIceCreams();
    }

    @Override
    public void onStop() {
        super.onStop();
        disposables.clear();
    }

    public String getMilkshakeFlavours() {

        return mMilkshakeAdapter.getSelectedFlavours();

    }
}
