package com.example.myshop.fragments;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshop.model.Product;

import java.util.List;

public class CustomerHomeFragment extends Fragment
{
    List<Product> productList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
}
