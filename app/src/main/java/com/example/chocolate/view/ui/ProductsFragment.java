package com.example.chocolate.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chocolate.R;
import com.example.chocolate.model.entities.ProductsEntity;
import com.example.chocolate.view.adapter.ProductsAdapter;
import com.example.chocolate.viewmodel.ProductsViewModel;

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

public class ProductsFragment extends Fragment implements ProductsAdapter.ProductsClickListener {

    public interface ProductsFragmentListener{
        void onProductClicked(ProductsEntity product);
    }

    private static final String TAG = "ProductsFragment";

    private ProductsFragmentListener mProductsFragmentListener;

    private ProductsAdapter mProductsAdapter;
    private RecyclerView mProductsRecyclerView;
    private ProductsViewModel mProductsViewModel;

    private CompositeDisposable disposables = new CompositeDisposable();

    private int mCategoryId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.products_fragment_layout,container,false);

        mProductsRecyclerView = view.findViewById(R.id.products_fragment_rv);
        mProductsRecyclerView.setLayoutManager(new GridLayoutManager(container.getContext(),4));
        mProductsRecyclerView.setHasFixedSize(false);

        mProductsAdapter = new ProductsAdapter(this);
        mProductsRecyclerView.setAdapter(mProductsAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mProductsViewModel = ViewModelProviders.of(this).get(ProductsViewModel.class);
    }

    private void subscribeForProducts() {
        mProductsViewModel.getProductsFromCategory(mCategoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<ProductsEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(List<ProductsEntity> productsEntities) {
                        mProductsAdapter.updateProductsList(productsEntities);
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
        subscribeForProducts();
    }

    @Override
    public void onStop() {
        super.onStop();
        disposables.clear();
    }

    public void setCategoryId(int categoryId) {
        mCategoryId = categoryId;
    }

    @Override
    public void onProductClicked(ProductsEntity product) {
        mProductsFragmentListener.onProductClicked(product);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof ProductsFragmentListener)
            mProductsFragmentListener = (ProductsFragmentListener) context;
        else
            throw new RuntimeException(context.toString() +
                    " must implement ProductsFragment.ProductsFragmentListener");

    }
}
