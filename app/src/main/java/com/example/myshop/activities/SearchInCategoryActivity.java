package com.example.myshop.activities;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshop.R;
import com.example.myshop.dataBase.DataBaseHandler;
import com.example.myshop.dataBase.RecyclerViewAdapter;
import com.example.myshop.model.Categories;
import com.example.myshop.model.Category;
import com.example.myshop.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class SearchInCategoryActivity extends AppCompatActivity
{
    private List<Product> productList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private EditText search_bar;
    private TextView tv_category;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_in_category);
        DataBaseHandler db = new DataBaseHandler(SearchInCategoryActivity.this);
        if(android.os.Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.sky_blue));
        }
        productList=searchProducts(Categories.getCategory(),Categories.isIsDescending(),Categories.isIsAscending());
        search_bar=findViewById(R.id.search_bar_in_category);
        tv_category=findViewById(R.id.tv_category_name_inCategory);
        tv_category.setText(Categories.getCategory());
        recyclerView=findViewById(R.id.inCategory_recycler_view);

        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                searchText(s.toString());
            }
        });

        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(SearchInCategoryActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter=new RecyclerViewAdapter(productList,SearchInCategoryActivity.this);
        recyclerView.setAdapter(mAdapter);


    }


    private List<Product> searchProducts (String category,boolean isDescending,boolean isAscending)
    {
        List<Product> firstList=new ArrayList<>();
        DataBaseHandler db = new DataBaseHandler(SearchInCategoryActivity.this);
        if(category.equals("همه ی کالاها"))
            firstList=db.getAllProducts();
        else
        {
            Category CategoryAsCategory = Product.getCategoryType(category);
            if(CategoryAsCategory==null)
            {
                firstList=db.getAllProducts();
            }
            else
            {
                firstList=db.getProductsByCategory(CategoryAsCategory);
            }
        }
        if(isAscending)
        {
            Collections.sort(firstList,productAscendingComparator);
        }
        if(isDescending)
        {
            Collections.sort(firstList,productDescendingComparator);
        }
        return firstList;
    }

    private static Comparator<Product> productAscendingComparator = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.getPrice()-(p2.getPrice());
        }
    };
    private static Comparator<Product> productDescendingComparator = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return p2.getPrice()-(p1.getPrice());
        }
    };


    private void searchText(String input)
    {
        ArrayList<Product> searchedList=new ArrayList<>();

        for(Product prod:productList)
        {
            if(prod.getName().toLowerCase().contains(input.toLowerCase()));
                searchedList.add(prod);
        }
        mAdapter.filterList(searchedList);
    }

}