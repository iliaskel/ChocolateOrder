package com.example.chocolate.model;

import android.content.Context;

import com.example.chocolate.model.daos.BasketItemDao;
import com.example.chocolate.model.daos.CategoriesDao;
import com.example.chocolate.model.daos.IceCreamDao;
import com.example.chocolate.model.daos.OrderItemsDao;
import com.example.chocolate.model.daos.ProductsDao;
import com.example.chocolate.model.daos.TablesDao;
import com.example.chocolate.model.entities.BasketItemEntity;
import com.example.chocolate.model.entities.CategoriesEntity;
import com.example.chocolate.model.entities.IceCreamEntity;
import com.example.chocolate.model.entities.OrderItemsEntity;
import com.example.chocolate.model.entities.ProductsEntity;
import com.example.chocolate.model.entities.TablesEntity;

import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {
        CategoriesEntity.class,
        OrderItemsEntity.class,
        ProductsEntity.class,
        TablesEntity.class,
        IceCreamEntity.class,
        BasketItemEntity.class
        },
        version = 1,
        exportSchema = false)
public abstract class ChocolateDatabase extends RoomDatabase {

    private static final String TAG = "ChocolateDatabase";

    private static ChocolateDatabase instance;

    abstract CategoriesDao categoriesDao();
    abstract ProductsDao productsDao();
    abstract TablesDao tablesDao();
    abstract OrderItemsDao orderItemsDao();
    abstract IceCreamDao iceCreamDao();
    abstract BasketItemDao basketItemDao();

    static ChocolateDatabase getInstance(Context context){
        if(instance==null){
            synchronized (ChocolateDatabase.class){
                instance = buildDatabase(context);
            }
        }
        return instance;
    }

    private static ChocolateDatabase buildDatabase(final Context context) {

        ChocolateDatabase db = Room.databaseBuilder(context,
                ChocolateDatabase.class,
                "chocolate-database")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(() -> {
                            getInstance(context).productsDao().insertProducts(ProductsEntity.populateProducts());
                            getInstance(context).categoriesDao().insertCategories(CategoriesEntity.populateCategories());
                            getInstance(context).tablesDao().insertTables(TablesEntity.populateTables());
                            //getInstance(context).orderItemsDao().addOrderItems(OrderItemsEntity.populateOrderItems());
                            getInstance(context).iceCreamDao().insertIceCreams(IceCreamEntity.populateIceCreams());
                        });
                    }
                })
                .fallbackToDestructiveMigration()
                .build();

        return db;
    }
}
