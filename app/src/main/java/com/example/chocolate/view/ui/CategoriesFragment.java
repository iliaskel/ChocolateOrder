package com.example.chocolate.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chocolate.R;
import com.example.chocolate.view.adapter.CategoriesAdapter;
import com.example.chocolate.viewmodel.CategoriesViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriesFragment extends Fragment implements CategoriesAdapter.CategoriesClickListener {

    public interface CategoriesFragmentClickListener{
        void onCategoryClicked(int categoryId);
    }

    private static final String TAG = "CategoriesFragment";

    private CategoriesFragmentClickListener mCategoriesFragmentClickListener;

    private CategoriesViewModel categoriesViewModel;
    private CategoriesAdapter categoriesAdapter;
    private RecyclerView categoriesRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.categories_fragment_layout,container,false);

        categoriesRecyclerView = view.findViewById(R.id.categories_fragment_rv);
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(container.getContext(),4));
        categoriesRecyclerView.setHasFixedSize(false);

        categoriesAdapter = new CategoriesAdapter(this);
        categoriesRecyclerView.setAdapter(categoriesAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        categoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        observeViewModel();
    }

    private void observeViewModel() {
        categoriesViewModel.getAllCategories().observe(this,
                categoriesEntities -> categoriesAdapter.setCategoriesList(categoriesEntities));
    }

    @Override
    public void onCategoryClicked(int categoryId) {
        mCategoriesFragmentClickListener.onCategoryClicked(categoryId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof CategoriesFragmentClickListener){
            mCategoriesFragmentClickListener = (CategoriesFragment.CategoriesFragmentClickListener)context;
        }
        else{
            throw new RuntimeException(context.toString()
                    +
                    "must implement CategoriesFragment.CategoriesFragmentClickListener");
        }
    }
}
