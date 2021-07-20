package com.example.myshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshop.R;
import com.example.myshop.dataBase.DataBaseHandler;
import com.example.myshop.dataBase.RecyclerViewAdapter;
import com.example.myshop.model.Product;
import com.example.myshop.model.Seller;

import java.util.List;

public class CustomerHomeFragment extends Fragment
{
    List<Product> productList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        DataBaseHandler DB = new DataBaseHandler(getActivity());

        productList=DB.getAllProducts();

        View view=inflater.inflate(R.layout.fragment_customer_home,container,false);

        recyclerView = view.findViewById(R.id.customer_home_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerViewAdapter(productList,getActivity());
        recyclerView.setAdapter(mAdapter);
        return view;
    }
}
