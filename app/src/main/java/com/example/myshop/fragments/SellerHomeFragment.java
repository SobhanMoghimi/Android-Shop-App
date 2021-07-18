package com.example.myshop.fragments;

import android.database.DatabaseErrorHandler;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myshop.R;
import com.example.myshop.dataBase.DataBaseHandler;
import com.example.myshop.dataBase.RecyclerViewAdapter;
import com.example.myshop.model.Product;
import com.example.myshop.model.Seller;

import java.util.List;

public class SellerHomeFragment extends Fragment
{

    List<Product> productList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public SellerHomeFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        DataBaseHandler DB = new DataBaseHandler(getActivity());

        productList=DB.getAllProductsOfSeller(Seller.activeSeller);
        View view=inflater.inflate(R.layout.fragment_seller_home, container, false);
        recyclerView = view.findViewById(R.id.seller_products_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerViewAdapter(productList,getActivity());
        recyclerView.setAdapter(mAdapter);
        return view;
    }
}