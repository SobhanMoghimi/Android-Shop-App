package com.example.myshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myshop.R;
import com.example.myshop.dataBase.DataBaseHandler;
import com.example.myshop.dataBase.RecyclerViewAdapter;

public class CustomerCategoriesFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        DataBaseHandler DB = new DataBaseHandler(getActivity());
        View view=inflater.inflate(R.layout.fragment_customer_categories,container,false);

        return view;
    }
}
