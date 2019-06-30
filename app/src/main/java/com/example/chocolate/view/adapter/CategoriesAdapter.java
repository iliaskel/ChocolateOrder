package com.example.chocolate.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chocolate.R;
import com.example.chocolate.model.entities.CategoriesEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    public interface CategoriesClickListener{
        void onCategoryClicked(int categoryId);
    }

    private List<CategoriesEntity> mAllCategoriesList = new ArrayList<>();
    private CategoriesClickListener mCategoriesClickListener;

    public CategoriesAdapter(CategoriesClickListener categoriesClickListener){
        mCategoriesClickListener = categoriesClickListener;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.categories_fragment_category_list_item,parent,false);

        return new CategoriesViewHolder(view,mCategoriesClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mAllCategoriesList.size();
    }

    public void setCategoriesList(List<CategoriesEntity> categoriesEntities) {
        mAllCategoriesList = categoriesEntities;
        notifyDataSetChanged();
    }

    class CategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mCategoryTv;
        private CategoriesClickListener mCategoriesClickListener;

        CategoriesViewHolder(@NonNull View itemView, CategoriesClickListener categoriesClickListener) {
            super(itemView);
            mCategoryTv = itemView.findViewById(R.id.categories_fragment_recycler_view_category_tv);
            this.mCategoriesClickListener = categoriesClickListener;
            itemView.setOnClickListener(this);
        }

        void bind(int position) {
            mCategoryTv.setText(mAllCategoriesList.get(position).getName());
        }

        @Override
        public void onClick(View v) {
            mCategoriesClickListener.onCategoryClicked(
                    mAllCategoriesList.get(getAdapterPosition())
                    .getId());
        }
    }
}
