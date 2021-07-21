package com.example.myshop.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.myshop.R;
import com.example.myshop.dataBase.DataBaseHandler;
import com.example.myshop.model.Customer;
import com.example.myshop.model.Product;
import com.example.myshop.model.Seller;

import java.util.ArrayList;
import java.util.List;

public class AdminFragment extends Fragment {

    private AppCompatButton listOfProductsBtn, sellersBtn, sumBtn, topSellerBtn, sellersLogCountBtn, isPinProducts, customersLogCount;
    DataBaseHandler db;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;


    public AdminFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin, container, false);

        listOfProductsBtn = view.findViewById(R.id.admin_name_of_products_btn);
        sellersBtn = view.findViewById(R.id.admin_name_of_sellers_btn);
        sumBtn = view.findViewById(R.id.admin_sum_of_prices_btn);
        topSellerBtn = view.findViewById(R.id.admin_top_seller_btn);
        customersLogCount = view.findViewById(R.id.admin_customers_login_count_btn);
        isPinProducts = view.findViewById(R.id.admin_prioritize_products_btn);
        sellersLogCountBtn = view.findViewById(R.id.admin_sellers_login_count_btn);
        db = new DataBaseHandler(getActivity());

        listOfProductsBtn.setOnClickListener(v -> {
            showNameOfProductsDialog();
        });

        sellersBtn.setOnClickListener(v -> {
            showNameOfSellersDialog();
        });

        sumBtn.setOnClickListener(v -> {
            showSumOfPricesDialog();
        });

        topSellerBtn.setOnClickListener(v -> {
            showTopSellerDialog();
        });

        sellersLogCountBtn.setOnClickListener(v -> {
            showSellersLoginCountDialog();
        });

        customersLogCount.setOnClickListener(v -> {
            showCustomersLoginCountDialog();
        });

        isPinProducts.setOnClickListener(v -> {

        });
        return view;

    }

    public void showNameOfProductsDialog() {
        final View showNameOfProductsPopup = getLayoutInflater().inflate(R.layout.popup_products_list, null);
        builder = new AlertDialog.Builder(getActivity());
        ImageView back_btn = showNameOfProductsPopup.findViewById(R.id.name_of_products_back_icon);
        ListView listView = showNameOfProductsPopup.findViewById(R.id.lv_name_of_products);
        TextView TotalOfProducts_tv = showNameOfProductsPopup.findViewById(R.id.total_num_of_products);
        List<Product> allProducts = db.getAllProducts();

        if (allProducts.isEmpty())
            TotalOfProducts_tv.setText("هیچ کالایی موجود نیست");
        else {
            TotalOfProducts_tv.setText("تعداد کالا ها:" + allProducts.size());

            ArrayList<String> productsInformation = new ArrayList<>();
            for (Product product : allProducts)
                productsInformation.add(product.toString());

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, productsInformation);
            listView.setAdapter(arrayAdapter);
        }

        builder.setView(showNameOfProductsPopup);
        dialog = builder.create();
        dialog.show();

        back_btn.setOnClickListener(v -> dialog.dismiss());
    }

        public void showNameOfSellersDialog() {
        final View showNameOfSellersPopup = getLayoutInflater().inflate(R.layout.popup_sellers_name, null);
        builder = new AlertDialog.Builder(getActivity());
        ImageView back_btn = showNameOfSellersPopup.findViewById(R.id.name_of_sellers_back_icon);
        ListView listView = showNameOfSellersPopup.findViewById(R.id.lv_name_of_sellers);
        TextView totalOfSellers_tv = showNameOfSellersPopup.findViewById(R.id.total_num_of_sellers);
        List<Seller> allSellers = db.getAllSellers();

        if (allSellers.isEmpty())
            totalOfSellers_tv.setText("هیچ فروشنده‌ای یافت نشد");

        else {
            totalOfSellers_tv.setText("کل فروشنده‌ها:"+ allSellers.size());

            ArrayList<String> sellersInformation = new ArrayList<>();
            for (Seller seller : allSellers)
                sellersInformation.add(seller.toString());

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, sellersInformation);
            listView.setAdapter(arrayAdapter);
        }

        builder.setView(showNameOfSellersPopup);
        dialog = builder.create();
        dialog.show();

        back_btn.setOnClickListener(v -> dialog.dismiss());
    }

    public void showSumOfPricesDialog() {
        final View showSumOfPricesPopup = getLayoutInflater().inflate(R.layout.popup_prices_sum, null);
        builder = new AlertDialog.Builder(getActivity());
        ImageView back_btn = showSumOfPricesPopup.findViewById(R.id.sum_of_prices_back_icon);
        TextView textView = showSumOfPricesPopup.findViewById(R.id.sum_of_prices_tv);
        int sum = 0;

        List<Product> AllProducts = db.getAllProducts();
        for(Product product : AllProducts)
            sum += product.getPrice();
        textView.setText("ارزش تمام کالا ها:" +"\n"+sum);
        builder.setView(showSumOfPricesPopup);
        dialog = builder.create();
        dialog.show();

        back_btn.setOnClickListener(v -> dialog.dismiss());
    }

    private void showTopSellerDialog() {
        final View showTopSellerPopup = getLayoutInflater().inflate(R.layout.popup_top_seller, null);
        builder = new AlertDialog.Builder(getActivity());
        ImageView back_btn = showTopSellerPopup.findViewById(R.id.top_seller_back_icon);
        TextView textView = showTopSellerPopup.findViewById(R.id.top_seller_tv);
        List<Seller> allSellers = db.getAllSellers();
        Seller topSeller = allSellers.get(0);
        if (allSellers.isEmpty())
            textView.setText("هیچ فروشنده‌ای نیست");
        else {
            for (Seller seller:allSellers) {
                if (seller.getPosts()>topSeller.getPosts())
                    topSeller = seller;
            }
            textView.setText("فروشنده برتر: "+"\n" + topSeller.getFullName() + "\n"+ topSeller.getEmail()+"\n"+topSeller.getPhoneNumber());
        }
        builder.setView(showTopSellerPopup);
        dialog = builder.create();
        dialog.show();

        back_btn.setOnClickListener(v -> dialog.dismiss());
    }

    public void showSellersLoginCountDialog() {
        final View showSellersLoginCountPopup = getLayoutInflater().inflate(R.layout.popup_sellers_log_count, null);
        builder = new AlertDialog.Builder(getActivity());
        ImageView back_btn = showSellersLoginCountPopup.findViewById(R.id.login_count_back_icon);
        ListView listView = showSellersLoginCountPopup.findViewById(R.id.lv_login_count);
        List<Seller> allSellers = db.getAllSellers();

        if (allSellers.isEmpty()){
            ArrayList<String> text = new ArrayList<>();
            text.add("هیچ فروشنده‌ای نیست");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.text_no_users, text);
            listView.setAdapter(arrayAdapter);
        }
        else {

            ArrayList<String> SellersLoginCount = new ArrayList<>();
            for (Seller seller : allSellers)
                SellersLoginCount.add(String.valueOf(seller.getLoginCount())+ " \t "+ seller.getEmail());

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, SellersLoginCount);
            listView.setAdapter(arrayAdapter);
        }

        builder.setView(showSellersLoginCountPopup);
        dialog = builder.create();
        dialog.show();

        back_btn.setOnClickListener(v -> dialog.dismiss());
    }

    public void showCustomersLoginCountDialog() {
        final View showCustomersLoginCountPopup = getLayoutInflater().inflate(R.layout.popup_customers_log_count, null);
        builder = new AlertDialog.Builder(getActivity());
        ImageView back_btn = showCustomersLoginCountPopup.findViewById(R.id.login_count_back_icon);
        ListView listView = showCustomersLoginCountPopup.findViewById(R.id.lv_login_count);
        List<Customer> allCustomers = db.getAllCustomers();

        if (allCustomers.isEmpty()){
            ArrayList<String> text = new ArrayList<>();
            text.add("هیچ فروشنده‌ای نیست");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.text_no_users, text);
            listView.setAdapter(arrayAdapter);
        }
        else {

            ArrayList<String> customersLoginCount = new ArrayList<>();
            for (Customer customer : allCustomers)
                customersLoginCount.add(String.valueOf(customer.getLoginCount())+ " \t "+ customer.getEmail());

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, customersLoginCount);
            listView.setAdapter(arrayAdapter);
        }

        builder.setView(showCustomersLoginCountPopup);
        dialog = builder.create();
        dialog.show();

        back_btn.setOnClickListener(v -> dialog.dismiss());
    }
}