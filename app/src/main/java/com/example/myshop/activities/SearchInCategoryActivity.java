package com.example.myshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.myshop.R;
import com.example.myshop.model.Categories;
import com.example.myshop.model.Category;

public class SearchInCategoryActivity extends AppCompatActivity
{
    private AppCompatButton carButton,electronicsButton,fashionButton,bookButton,homeButton,beautyButton,allButton;
    private RadioButton ascending_rb, descending_rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_in_category);
        if(android.os.Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.primary));
        }
        carButton=findViewById(R.id.carsCatButton);
        electronicsButton=findViewById(R.id.elecCatButton);
        fashionButton=findViewById(R.id.fashionCatButton);
        bookButton=findViewById(R.id.bookCatButton);
        homeButton=findViewById(R.id.homeCatButton);
        beautyButton=findViewById(R.id.beautyCatButton);
        allButton=findViewById(R.id.allCatsButton);
        ascending_rb=findViewById(R.id.rb_cheapest);
        descending_rb=findViewById(R.id.rb_most_expensive);

        carButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Categories.setCategory("خودرو");
            }
        });


        electronicsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Categories.setCategory("لوازم الکترونیکی");
            }
        });


        fashionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Categories.setCategory("فشن");
            }
        });

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Categories.setCategory("کتاب");
            }
        });


        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Categories.setCategory("لوازم خانه");
            }
        });

        beautyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categories.setCategory("لوازم بهداشتی");
            }
        });



        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Categories.setCategory("همه ی کالاها");
            }
        });

    }

}