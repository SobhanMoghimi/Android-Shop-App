package com.example.myshop.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.myshop.R;
import com.example.myshop.dataBase.DataBaseHandler;
import com.example.myshop.model.Admin;
import com.example.myshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class AdminPinProductsFragment extends Fragment {

    private Admin admin;
    private ListView productsListView;
    private List<Product> allProduct;
    private DataBaseHandler db;
    private List<Product> selectedProducts = new ArrayList<>();

    public AdminPinProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_pin_products, container, false);
        admin = Admin.getActiveAdmin();
        db = new DataBaseHandler(getActivity());
        productsListView = view.findViewById(R.id.productsAdminList);
        allProduct = db.getAllProducts();
        List<Product> pined = db.getPinedProducts();
        return view;
    }
}