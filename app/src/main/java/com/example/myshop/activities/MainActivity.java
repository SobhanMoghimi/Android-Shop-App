package com.example.myshop.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myshop.R;
import com.example.myshop.dataBase.DataBaseHandler;

public class MainActivity extends AppCompatActivity
{
    DataBaseHandler db;
    private Button customerButton,sellerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DataBaseHandler(this);
        db.addAdmin();
        if(android.os.Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.secondaryDark));
        }
        customerButton=findViewById(R.id.customer_button);
        sellerButton=findViewById(R.id.seller_button);
        customerButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CustomerLoginActivity.class)));
        sellerButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,SellerLoginActivity.class)));
    }
}