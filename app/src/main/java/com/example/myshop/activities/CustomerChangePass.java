package com.example.myshop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.myshop.R;
import com.example.myshop.dataBase.DataBaseHandler;
import com.example.myshop.model.Customer;

public class CustomerChangePass extends AppCompatActivity {

    private AppCompatButton button;
    private EditText prePass,newPass;
    private TextView error;
    Customer customer;
    DataBaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_change_pass);
        db = new DataBaseHandler(this);
        customer = Customer.activeCustomer;
        button = findViewById(R.id.buttonChangePass);
        prePass = findViewById(R.id.customerPrePassword);
        newPass = findViewById(R.id.customerNewPassword);
        error = findViewById(R.id.customerChangePassError);

        button.setOnClickListener(v -> {
            if (prePass.getText().toString().equals("") || newPass.getText().toString().equals("")) {
                error.setText("باید تمام فیلد ها را پر کنید!");
            }
            else {
                if (db.updatePassCustomer(customer,newPass.getText().toString())) {
                    customer.setPassword(newPass.getText().toString());
                    Customer.activeCustomer = customer;
                    Toast.makeText(this,"رمز شما با موفقیت تغییر کرد!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this,CustomerHomePageActivity.class));
                }
                else{
                    error.setText("مشکلی در تغییر رمز پیش آمده!");
                }
            }
        });
    }
}