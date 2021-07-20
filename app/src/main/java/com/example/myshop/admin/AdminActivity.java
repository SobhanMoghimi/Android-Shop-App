package com.example.myshop.admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myshop.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_admin, new AdminFragment()).commit();
    }

}