package com.example.myshop.activities;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.myshop.R;

public class SellerLoginActivity extends AppCompatActivity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);
        if(android.os.Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.secondaryDark));
        }
        OnBackPressedCallback callback=new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed()
            {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SellerLoginActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle("هشدار!");
                dialog.setMessage("بازگشت به صفحه اصلی؟" );
                dialog.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        startActivity(new Intent(SellerLoginActivity.this, MainActivity.class));
                    }
                }).setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                final AlertDialog alert=dialog.create();
                alert.show();
            }
        };
        SellerLoginActivity.this.getOnBackPressedDispatcher().addCallback(this,callback);
    }
}