package com.example.chocolate.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chocolate.model.entities.TablesEntity;
import com.example.chocolate.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TablesAdapter extends RecyclerView.Adapter<TablesAdapter.TablesViewHolder> {

    public interface TablesClickListener{
        void onTableClicked(int tableId,String tableName);
    }

    private Context mContext;

    private List<TablesEntity> listOfTables = new ArrayList<>();
    private TablesClickListener mTablesClickListener;

    public TablesAdapter(Context context,TablesClickListener tablesClickListener)
    {
        mContext = context;
        mTablesClickListener = tablesClickListener;
    }

    @NonNull
    @Override
    public TablesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.tables_fragment_table_list_item,parent,false);

        return new TablesViewHolder(mTablesClickListener,view);
    }

    @Override
    public void onBindViewHolder(@NonNull TablesViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return listOfTables.size();
    }

    public void updateTableList(List<TablesEntity> tablesEntities) {
        listOfTables = tablesEntities;
        notifyDataSetChanged();
    }


    class TablesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String TAG = "TablesViewHolder";

        private CardView mTableCardView;
        private TextView mTableTextView;
        private TablesClickListener mTablesClickListener;

        TablesViewHolder(TablesClickListener tablesClickListener,@NonNull View itemView) {
            super(itemView);
            mTableCardView = itemView.findViewById(R.id.tables_fragment_recycler_view_table_cv);
            mTableTextView = itemView.findViewById(R.id.tables_fragment_recycler_view_table_tv);
            this.mTablesClickListener = TablesAdapter.this.mTablesClickListener;
            itemView.setOnClickListener(this);
        }

        void bind(int position) {
            setTableColor(position);
            setTableName(position);
        }


        private void setTableColor(int adapterPosition) {
            if (listOfTables.get(adapterPosition).isOpen()){
                mTableCardView.setBackgroundColor(
                        mContext.getResources().getColor(R.color.materialGreen));
            }
            else{
                mTableCardView.setBackgroundColor(
                        mContext.getResources().getColor(R.color.materialRed));
            }
        }

        private void setTableName(int adapterPosition) {
            //Calculating table's row and column to set the name of the table.

            int tableRow;
            int tableColumn;
            if(adapterPosition<4){
                tableRow=1;
                tableColumn = adapterPosition+1;
            }
            else if(adapterPosition<8){
                tableRow=2;
                tableColumn=adapterPosition-3;
            }
            else if(adapterPosition<12){
                tableRow=3;
                tableColumn=adapterPosition-7;
            }
            else if(adapterPosition<16){
                tableRow=4;
                tableColumn = adapterPosition-11;
            }
            else if(adapterPosition<20){
                tableRow=5;
                tableColumn=adapterPosition-15;
            }
            else{
                tableRow=6;
                tableColumn = adapterPosition-19;
            }

            switch (tableColumn){
                case 1:
                    mTableTextView.setText("Α " + tableRow);
                    break;
                case 2:
                    mTableTextView.setText("Β " +tableRow);
                    break;
                case 3:
                    mTableTextView.setText("Γ " + tableRow);
                    break;
                case 4:
                    mTableTextView.setText("Δ " + tableRow);
                    break;
            }
        }

        @Override
        public void onClick(View v) {
            mTablesClickListener.onTableClicked(listOfTables.get(getAdapterPosition()).getId(),String.valueOf(mTableTextView.getText()));
        }
    }
}
