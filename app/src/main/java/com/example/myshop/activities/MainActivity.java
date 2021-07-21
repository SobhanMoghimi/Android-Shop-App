package com.example.myshop.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myshop.R;

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

        customerButton=findViewById(R.id.customer_button);
        sellerButton=findViewById(R.id.seller_button);
        customerButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CustomerLoginActivity.class)));
        sellerButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,SellerLoginActivity.class)));
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle("هشدار!");
        dialog.setMessage("آیا میخواهید از برنامه خارج شوید؟!" );
        dialog.setPositiveButton("بله", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                finish();
                System.exit(0);
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
}