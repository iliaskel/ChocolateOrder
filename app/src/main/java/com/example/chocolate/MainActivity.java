package com.example.chocolate;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.chocolate.model.entities.BasketItemEntity;
import com.example.chocolate.model.entities.ProductsEntity;
import com.example.chocolate.view.ui.BasketItemsFragment;
import com.example.chocolate.view.ui.CategoriesFragment;
import com.example.chocolate.view.ui.CommentFragment;
import com.example.chocolate.view.ui.IceCreamFragment;
import com.example.chocolate.view.ui.MilkshakeFragment;
import com.example.chocolate.view.ui.ProductLabelFragment;
import com.example.chocolate.view.ui.ProductsFragment;
import com.example.chocolate.view.ui.SpiritsFragment;
import com.example.chocolate.view.ui.SugarAddOnsFragment;
import com.example.chocolate.view.ui.TableOrderFragment;
import com.example.chocolate.view.ui.TablesFragment;
import com.example.chocolate.viewmodel.BasketItemsViewModel;

import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity
        implements
        TablesFragment.TablesFragmentListener,
        TableOrderFragment.TableOrderFragmentListener,
        CategoriesFragment.CategoriesFragmentClickListener,
        ProductsFragment.ProductsFragmentListener,
        BasketItemsFragment.BasketItemsListener,
        CommentFragment.CommentFragmentListener

{

    private static final String TAG = "MainActivity";

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;

    TablesFragment tablesFragment = new TablesFragment();
    TableOrderFragment tableOrderFragment = new TableOrderFragment();
    CategoriesFragment categoriesFragment = new CategoriesFragment();
    ProductsFragment productsFragment = new ProductsFragment();
    CommentFragment commentFragment = new CommentFragment();
    SugarAddOnsFragment sugarAddOnsFragment = new SugarAddOnsFragment();
    IceCreamFragment iceCreamFragment = new IceCreamFragment();
    MilkshakeFragment milkshakeFragment = new MilkshakeFragment();
    SpiritsFragment spiritsFragment = new SpiritsFragment();
    ProductLabelFragment productLabelFragment = new ProductLabelFragment();
    BasketItemsFragment basketItemsFragment = new BasketItemsFragment();

    private BasketItemsViewModel basketItemsViewModel;

    //used for the print job
    private WebView mWebView;

    private int tableId;
    private String tableName;
    private ProductsEntity product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        basketItemsViewModel = ViewModelProviders.of(this).get(BasketItemsViewModel.class);

        showTablesFragment();

    }

    /**
     * Tables fragment
     */
    private void showTablesFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.full_screen_fl,tablesFragment);
        fragmentTransaction.addToBackStack(TablesFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }

    @Override
    public void onTableClicked(int tableId, String tableName) {
        showTableOrderFragment(tableId,tableName);
    }

    /**
     * Table order fragment
     */
    private void showTableOrderFragment(int tableId,String tableName){
        Log.d(TAG, "onTableClicked: table's id :: " + tableId);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.full_screen_fl,tableOrderFragment);
        fragmentTransaction.addToBackStack(TableOrderFragment.class.getSimpleName());
        fragmentTransaction.commit();

        tableOrderFragment.setTableId(tableId);
        tableOrderFragment.setTableName(tableName);

        this.tableId = tableId;
        this.tableName = tableName;
    }

    /**
     * Basket items fragment
     */
    @Override
    public void onGoToTableBasketClicked() {
        basketItemsFragment.setSourceFragment(TableOrderFragment.class.getSimpleName());
        showBasketFragment();
    }

    private void showBasketFragment(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.full_screen_fl,basketItemsFragment);
        fragmentTransaction.addToBackStack(BasketItemsFragment.class.getSimpleName());
        basketItemsFragment.setTableId(tableId);
        basketItemsFragment.setTableName(tableName);
        fragmentTransaction.commit();
    }

    @Override
    public void basketIsEmpty() {
        //if basket is empty, the screen is skipped straight to categories fragment.
        //TODO: Find a better way to handle this case
        showCategoriesFragment();
    }

    /**
     * Categories fragment
     */
    private void showCategoriesFragment(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.full_screen_fl,categoriesFragment);
        fragmentTransaction.addToBackStack(CategoriesFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }

    @Override
    public void onCategoryClicked(int categoryId) {
        showProductsFragment(categoryId);
    }


    /**
     * Products fragment
     */
    private void showProductsFragment(int categoryId){
        Log.d(TAG, "onCategoryClicked: ");
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.full_screen_fl,productsFragment);
        fragmentTransaction.addToBackStack(ProductsFragment.class.getSimpleName());
        fragmentTransaction.commit();

        productsFragment.setCategoryId(categoryId);
    }

    @Override
    public void onProductClicked(ProductsEntity product) {
        showProductDetailsScreen(product);
    }

    private void showProductDetailsScreen(ProductsEntity product){
        this.product = product;
        Log.d(TAG, "onProductClicked: product :: " + this.product.getName());
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(productsFragment);
        fragmentTransaction.add(R.id.half_screen_bottom,commentFragment);
        fragmentTransaction.addToBackStack(CommentFragment.class.getSimpleName());

        fragmentTransaction.add(R.id.product_label_fragment_fl,productLabelFragment);
        fragmentTransaction.addToBackStack(ProductLabelFragment.class.getSimpleName());
        productLabelFragment.setProductLabel(this.product.getName());

        if(product.getCategory_id() == 1){
            fragmentTransaction.add(R.id.half_screen_top,sugarAddOnsFragment);
            fragmentTransaction.addToBackStack(SugarAddOnsFragment.class.getSimpleName());
        }
        else if(product.getCategory_id() == 5){
            fragmentTransaction.add(R.id.half_screen_top,spiritsFragment);
            fragmentTransaction.addToBackStack(SpiritsFragment.class.getSimpleName());
            spiritsFragment.setProductName(product.getName());
        }
        else if(product.getCategory_id() == 6){
            fragmentTransaction.add(R.id.half_screen_top,milkshakeFragment);
            fragmentTransaction.addToBackStack(MilkshakeFragment.class.getSimpleName());
        }
        else if(product.getCategory_id()==8 || product.getCategory_id()==9 || product.getCategory_id()==10){
            fragmentTransaction.add(R.id.half_screen_top,iceCreamFragment);
            fragmentTransaction.addToBackStack(IceCreamFragment.class.getSimpleName());
        }

        fragmentTransaction.commit();
    }

    @Override
    public void onAddProductClicked() {
        showCategoriesFragment();
    }

    @Override
    public void onAddItemToBasketClicked(String comment,int quantity) {

        String productName = getProductName();
        float productPrice = getProductPrice(quantity);

        addItemToBasket(productName,productPrice,comment,quantity);
        fragmentManager.popBackStack(BasketItemsFragment.class.getSimpleName(),0);
    }

    private float getProductPrice(int quantity) {
        float productPrice = 0.0f;
        if(product.getCategory_id() == 8 || product.getCategory_id() == 9){
            productPrice = (product.getPrice()+iceCreamFragment.getIceCreamPriceForSiropiasta())*quantity;
            Log.d(TAG, "getProductPrice: " + product.getPrice() + " " + iceCreamFragment.getIceCreamPriceForSiropiasta() + " " + quantity);
        }
        else if(product.getCategory_id() == 10){
            productPrice = (product.getPrice()+iceCreamFragment.getIceCreamPrice())*quantity;
        }
        else {
            productPrice = product.getPrice()*quantity;
        }
        return productPrice;
    }

    private String getProductName() {
        StringBuilder productNameBuilder = new StringBuilder(product.getName());

        if(product.getCategory_id()== 1){
            productNameBuilder.append(" (")
                    .append(sugarAddOnsFragment.getSugarDetails())
                    .append(")");
        }
        else if(product.getCategory_id() == 5){
            productNameBuilder
                    .append(spiritsFragment.getSpiritsDetails());
        }
        else if(product.getCategory_id() == 6){
            productNameBuilder.append(" (")
                    .append(milkshakeFragment.getMilkshakeFlavours())
                    .append(")");
        }
        else if(product.getCategory_id() == 8|| product.getCategory_id() == 9 || product.getCategory_id()==10){
            productNameBuilder
                    .append(iceCreamFragment.getIceCreamFlavours());
        }

        return String.valueOf(productNameBuilder);
    }

    private void addItemToBasket(String productName, float productPrice, String comment, int quantity){

        BasketItemEntity basketItem = new BasketItemEntity(this.tableId,productName,productPrice,comment,quantity);
        basketItemsViewModel.addItemToBasket(basketItem);
    }

    /**
     *
     */

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: table's id :: " + tableId);
        Log.d(TAG, "onStart: table's name :: " + tableName);
        if(product !=null)
            Log.d(TAG, "onStart: product's name :: " + product.getName());
    }

    @Override
    public void onBackPressed() {
        if(categoriesFragment.isVisible())
            basketItemsFragment.setSourceFragment(CategoriesFragment.class.getSimpleName());
        if (fragmentManager.getBackStackEntryCount()<2){
            finish();
        }
        else{
            fragmentManager.popBackStack();
        }
    }


    /**
     * Print job
     */

    @Override
    public void printOrder(List<BasketItemEntity> basketItemEntities) {
        String htmlOrderDocument = getHtmlOrderDocumentFromOrderList(basketItemEntities);
        doWebViewPrint(htmlOrderDocument);
        fragmentManager.popBackStack(TablesFragment.class.getSimpleName(),0);
    }

    private String getHtmlOrderDocumentFromOrderList(List<BasketItemEntity> basketItemEntities) {
        StringBuilder htmlDocument = new StringBuilder("<html><body>");
        double totalSum = 0.0;
        htmlDocument.append("<h2>")
            .append(getTableName(basketItemEntities.get(0).getTable_id()))
            .append("</h2>")
            .append("</br>");
        for(BasketItemEntity basketItemEntity: basketItemEntities){

            totalSum += basketItemEntity.getProductPrice();

            htmlDocument.append("<p>")
            .append(basketItemEntity.getProductName())
            .append(" ")
            .append(basketItemEntity.getComment())
            .append(" ")
            .append(" (")
            .append(basketItemEntity.getProductPrice())
            .append(" €)")
            .append("</p>");
        }
        htmlDocument.append("</br>");
        htmlDocument.append("<p><h3>Σύνολο: ");
        htmlDocument.append(totalSum);
        htmlDocument.append("</p></h3></br>");
        htmlDocument.append("</body></html>");
        return htmlDocument.toString();
    }

    private String getTableName(int table_id) {
        int tableRow;
        int tableColumn;
        int adapterPosition = table_id-1;
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
                return ("Τραπέζι Α " + tableRow);
            case 2:
                return ("Τραπέζι Β " +tableRow);
            case 3:
                return ("Τραπέζι Γ " + tableRow);
            case 4:
                return ("Τραπέζι Δ " + tableRow);
        }

        return "Αγνωστο τραπέζι";
    }

    private void doWebViewPrint(String htmlOrderDocument) {
        // Create a WebView object specifically for printing
        WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "page finished loading " + url);
                createWebPrintJob(view);
                mWebView = null;
            }
        });

        webView.loadDataWithBaseURL(null, htmlOrderDocument, "text/HTML", "UTF-8", null);

        // Keep a reference to WebView object until you pass the PrintDocumentAdapter
        // to the PrintManager
        mWebView = webView;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void createWebPrintJob(WebView webView) {

        // Get a PrintManager instance
        PrintManager printManager = (PrintManager)MainActivity.this
                .getSystemService(Context.PRINT_SERVICE);

        String jobName = getString(R.string.app_name) + " Document";

        // Get a print adapter instance
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(jobName);

        // Create a print job with name and adapter instance
        PrintJob printJob = printManager.print(jobName, printAdapter,
                new PrintAttributes.Builder().build());

    }
}
