package com.example.myshop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.myshop.R;
import com.example.myshop.dataBase.DataBaseHandler;
import com.example.myshop.model.Customer;

import java.util.List;

public class CustomerEditMyAccActivity extends AppCompatActivity {

    private AppCompatButton button;
    private EditText newName,newEmail;
    private TextView error;
    Customer customer;
    DataBaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_my_acc);

        customer = Customer.activeCustomer;
        db = new DataBaseHandler(this);
        newName = findViewById(R.id.changeName);
        newEmail = findViewById(R.id.changeEmail);
        button = findViewById(R.id.record);
        error = findViewById(R.id.editCustomerError);

        button.setOnClickListener(v -> {
            if (newEmail.getText().toString().equals("") || newName.getText().toString().equals("")) {
                error.setText("باید تمام فیلد ها را پر کنید!");
            }
            else if (checkEmail(newEmail.getText().toString())) {
                if (db.updateCustomer(customer,newName.getText().toString(),newEmail.getText().toString())) {
                    customer.setEmail(newEmail.getText().toString());
                    customer.setFullName(newName.getText().toString());
                    Customer.activeCustomer = customer;
                    startActivity(new Intent(this,CustomerHomePageActivity.class));
                }
                else {
                    error.setText("ویرایش اطلاعات با مشکل مواجه شده است!");
                }
            }
            else if (!checkEmail(newEmail.getText().toString())) {
                error.setText("این ایمیل قبلا انتخاب شده! ایمیل جدیدی انتخاب کنید!");
            }
        });
    }

    public boolean checkEmail(String email) {
        List<Customer> customers = db.getAllCustomers();

        for (Customer customer1 : customers) {
            if (customer1.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }
}