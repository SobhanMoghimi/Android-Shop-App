package com.example.myshop.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import com.example.myshop.R;
import com.example.myshop.activities.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private Button customerButton,sellerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customerButton=findViewById(R.id.customer_button);
        sellerButton=findViewById(R.id.seller_button);
        customerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }
}