package com.example.myshop.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myshop.R;
import com.example.myshop.activities.CustomerHomePageActivity;
import com.example.myshop.activities.SearchInCategoryActivity;
import com.example.myshop.dataBase.DataBaseHandler;
import com.example.myshop.dataBase.RecyclerViewAdapter;
import com.example.myshop.model.Categories;

public class CustomerCategoriesFragment extends Fragment
{
    private AppCompatButton carButton,electronicsButton,fashionButton,bookButton,homeButton,beautyButton,allButton;
    private RadioButton ascending_rb, descending_rb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        DataBaseHandler DB = new DataBaseHandler(getActivity());
        View view=inflater.inflate(R.layout.fragment_customer_categories,container,false);



        carButton=view.findViewById(R.id.carsCatButton);
        electronicsButton=view.findViewById(R.id.elecCatButton);
        fashionButton=view.findViewById(R.id.fashionCatButton);
        bookButton=view.findViewById(R.id.bookCatButton);
        homeButton=view.findViewById(R.id.homeCatButton);
        beautyButton=view.findViewById(R.id.beautyCatButton);
        allButton=view.findViewById(R.id.allCatsButton);
        ascending_rb=view.findViewById(R.id.rb_cheapest);
        descending_rb=view.findViewById(R.id.rb_most_expensive);




        carButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(ascending_rb.isChecked())
                {
                    Categories.setIsAscending(true);
                    Categories.setIsDescending(false);
                }
                if(descending_rb.isChecked())
                {
                    Categories.setIsDescending(true);
                    Categories.setIsAscending(false);
                }
                Categories.setCategory("خودرو");
                startActivity(new Intent(getActivity(), SearchInCategoryActivity.class));
            }
        });


        electronicsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(ascending_rb.isChecked())
                {
                    Categories.setIsAscending(true);
                    Categories.setIsDescending(false);
                }
                if(descending_rb.isChecked())
                {
                    Categories.setIsDescending(true);
                    Categories.setIsAscending(false);
                }
                Categories.setCategory("لوازم الکترونیکی");
                startActivity(new Intent(getActivity(), SearchInCategoryActivity.class));

            }
        });


        fashionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(ascending_rb.isChecked())
                {
                    Categories.setIsAscending(true);
                    Categories.setIsDescending(false);
                }
                if(descending_rb.isChecked())
                {
                    Categories.setIsDescending(true);
                    Categories.setIsAscending(false);
                }
                Categories.setCategory("فشن");
                startActivity(new Intent(getActivity(), SearchInCategoryActivity.class));
            }
        });

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(ascending_rb.isChecked())
                {
                    Categories.setIsAscending(true);
                    Categories.setIsDescending(false);
                }
                if(descending_rb.isChecked())
                {
                    Categories.setIsDescending(true);
                    Categories.setIsAscending(false);
                }
                Categories.setCategory("کتاب");
                startActivity(new Intent(getActivity(), SearchInCategoryActivity.class));
            }
        });


        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(ascending_rb.isChecked())
                {
                    Categories.setIsAscending(true);
                    Categories.setIsDescending(false);
                }
                if(descending_rb.isChecked())
                {
                    Categories.setIsDescending(true);
                    Categories.setIsAscending(false);
                }
                Categories.setCategory("لوازم خانه");
                startActivity(new Intent(getActivity(), SearchInCategoryActivity.class));
            }
        });

        beautyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ascending_rb.isChecked())
                {
                    Categories.setIsAscending(true);
                    Categories.setIsDescending(false);
                }
                if(descending_rb.isChecked())
                {
                    Categories.setIsDescending(true);
                    Categories.setIsAscending(false);
                }
                Categories.setCategory("لوازم بهداشتی");
                startActivity(new Intent(getActivity(), SearchInCategoryActivity.class));
            }
        });



        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(ascending_rb.isChecked())
                {
                    Categories.setIsAscending(true);
                    Categories.setIsDescending(false);
                }
                if(descending_rb.isChecked())
                {
                    Categories.setIsDescending(true);
                    Categories.setIsAscending(false);
                }
                Categories.setCategory("همه ی کالاها");
                startActivity(new Intent(getActivity(), SearchInCategoryActivity.class));
            }
        });









        return view;
    }
}
