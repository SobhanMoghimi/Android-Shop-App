package com.example.myshop.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myshop.R;
import com.example.myshop.splash.Splash;

public class MainActivity extends AppCompatActivity
{
    private Button customerButton,sellerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        DataBaseHandler db = new DataBaseHandler(this);
//        db.deleteAllSellers();
//        db.deleteAllCustomers();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(android.os.Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.secondaryDark));
        }
        OnBackPressedCallback callback=new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed()
            {
                Toast.makeText(MainActivity.this,"کجا به این زودی =)  !؟",Toast.LENGTH_SHORT).show();
            }
        };
        MainActivity.this.getOnBackPressedDispatcher().addCallback(this,callback);


        if(getIntent().getBooleanExtra("EXIT",false))
        {
            finish();
            System.exit(0);
        }

        customerButton=findViewById(R.id.customer_button);
        sellerButton=findViewById(R.id.seller_button);
        customerButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CustomerLoginActivity.class)));
        sellerButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,SellerLoginActivity.class)));
    }
}