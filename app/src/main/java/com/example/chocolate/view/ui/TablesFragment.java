package com.example.chocolate.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chocolate.R;
import com.example.chocolate.view.adapter.TablesAdapter;
import com.example.chocolate.viewmodel.TablesViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TablesFragment extends Fragment implements TablesAdapter.TablesClickListener {

    public interface TablesFragmentListener{
        void onTableClicked(int tableId, String tableName);
    }

    private static final String TAG = "TablesFragment";

    TablesFragmentListener tablesFragmentListener;

    private TablesViewModel tablesViewModel;
    private TablesAdapter tablesAdapter;
    private RecyclerView tablesRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tables_fragment_layout,container,false);

        tablesRecyclerView = view.findViewById(R.id.tables_fragment_tables_rv);
        tablesRecyclerView.setLayoutManager(new GridLayoutManager(container.getContext(),4));
        tablesRecyclerView.setHasFixedSize(false);
        tablesAdapter = new TablesAdapter(getContext(),this);
        tablesRecyclerView.setAdapter(tablesAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tablesViewModel = ViewModelProviders.of(this).get(TablesViewModel.class);
        observeViewModel();
    }

    private void observeViewModel() {
        tablesViewModel.getAllTables().observe(this,
                tablesEntities -> tablesAdapter.updateTableList(tablesEntities));
    }

    @Override
    public void onTableClicked(int tableId,String tableName) {
        tablesFragmentListener.onTableClicked(tableId,tableName);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof TablesFragment.TablesFragmentListener){
            tablesFragmentListener =(TablesFragment.TablesFragmentListener)context;
        }
        else {
            throw new RuntimeException(context.toString()
                    + " must implement TablesFragment.TablesFragmentListener");
        }
    }
}
