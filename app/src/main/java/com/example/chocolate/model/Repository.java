package com.example.chocolate.model;

import android.app.Application;
import android.util.Log;

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

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    private static final String TAG = "Repository";

    private static Repository instance;

    private CategoriesDao categoriesDao;
    private OrderItemsDao orderItemsDao;
    private ProductsDao productsDao;
    private TablesDao tablesDao;
    private IceCreamDao iceCreamDao;
    private BasketItemDao basketItemDao;

    private LiveData<List<TablesEntity>> allTables;
    private LiveData<List<CategoriesEntity>> allCategories;


    private Repository(Application application){
        ChocolateDatabase chocolateDatabase =ChocolateDatabase.getInstance(application);
        categoriesDao = chocolateDatabase.categoriesDao();
        orderItemsDao = chocolateDatabase.orderItemsDao();
        productsDao = chocolateDatabase.productsDao();
        tablesDao = chocolateDatabase.tablesDao();
        iceCreamDao = chocolateDatabase.iceCreamDao();
        basketItemDao = chocolateDatabase.basketItemDao();

        allTables = tablesDao.getAllTables();
        allCategories = categoriesDao.getAllCategories();
    }

    public static Repository getInstance(Application application){

        if(instance == null){
            synchronized (Repository.class){
                instance = new Repository(application);
            }
        }
        return instance;
    }

    /**
     *TablesFragment
     */
    public LiveData<List<TablesEntity>> getAllTables(){
        return allTables;
    }

    public void updateTableAvailability(int tableId,boolean isOpen){
        Completable.fromAction(()->tablesDao.updateTableIsOpen(tableId,isOpen))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    /**
     * TableOrderFragment
     */
     public Maybe<List<OrderItemsEntity>> getOrdersFromTableId(int tableID){
        return orderItemsDao.getOrderItemsFromTable(tableID);
    }

     public void deleteOrderFromTable(int tableId){
        Completable.fromAction(
                () -> orderItemsDao.deleteOrderFromTable(tableId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void deleteOrderItem(int orderItemId){
         Completable.fromAction(
                 ()->orderItemsDao.deleteOrderItem(orderItemId))
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe();
    }

    public void addOrderItems(List<OrderItemsEntity> orderItemsEntities){
         Completable.fromAction(
                 ()-> orderItemsDao.addOrderItems(orderItemsEntities))
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe();
    }

    /**
     * CategoriesFragment
     */

    public LiveData<List<CategoriesEntity>> getAllCategories(){
        return allCategories;
    }

    /**
     * ProductsFragment
     */

    public Maybe<List<ProductsEntity>> getProductsFromCategory(int categoryId){
        return productsDao.getProductsFromCategory(categoryId);

    }

    /**
     * IceCreamFragment
     */
    public Maybe<List<IceCreamEntity>> getAllIceCreams(){
       return iceCreamDao.getAllIceCreams();
    }

    /**
     * BasketItemFragment
     */
    public Maybe<List<BasketItemEntity>> getBasketItemsForTable(int tableId){
        return basketItemDao.getBasketItemsForTable(tableId);
    }

    public void addItemToBasket(BasketItemEntity basketItem) {
        Completable.fromAction(()->basketItemDao.addItemToBasket(basketItem))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void deleteItemFromBasket(int basketItemId){
        Completable.fromAction(()->basketItemDao.deleteItemFromBasket(basketItemId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void deleteBasketItems(List<BasketItemEntity> basketItemEntities){
        Completable.fromAction(()->basketItemDao.deleteBasketItems(basketItemEntities))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
