package com.example.myshop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.myshop.R;
import com.example.myshop.dataBase.DataBaseHandler;
import com.example.myshop.model.Seller;

import java.util.List;

public class SellerChangePassword extends AppCompatActivity {

    private AppCompatButton button;
    private EditText prePass,newPass;
    private TextView error;
    private DataBaseHandler db;
    private List<Seller> allSellers;
    private Seller seller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_change_password);

        db = new DataBaseHandler(this);
        allSellers = db.getAllSellers();
        button = findViewById(R.id.buttonSellerChangePass);
        prePass = findViewById(R.id.prePassword);
        newPass = findViewById(R.id.newPassword);
        seller = Seller.getActiveSeller();
        button.setOnClickListener(v -> {
            boolean found=false;
            if (prePass.getText().toString().equals("") || newPass.getText().equals("")) {
                error.setText("تمام فیلد ها را پر کنید!");
            }
            else {
                for (Seller seller: allSellers) {
                    if (seller.getPassword().equals(prePass.getText().toString())) {
                        found=true;
                        if (db.updatePassSeller(seller,newPass.getText().toString()))
                        {
                            seller.setPassword(newPass.getText().toString());
                            Seller.activeSeller = seller;
                            startActivity(new Intent(this,SellerHomePageActivity.class));
                        }
                    }
                }
            }
            if (found==false) {
                error.setText("مشکلی پیش آمده!");
            }
        });
    }

}