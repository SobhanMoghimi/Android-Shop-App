package com.example.myshop.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
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
        if(android.os.Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.secondaryDark));
        }
        customerButton=findViewById(R.id.customer_button);
        sellerButton=findViewById(R.id.seller_button);
        customerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomerLoginActivity.class));
            }
        });
        sellerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SellerLoginActivity.class));
            }
        });
    }
}