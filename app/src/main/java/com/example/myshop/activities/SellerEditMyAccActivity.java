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
import com.example.myshop.model.Seller;

import java.util.List;

public class SellerEditMyAccActivity extends AppCompatActivity {

    private AppCompatButton button;
    private EditText newName,newEmail,newPhone;
    private TextView error;
    private Seller seller;
    private DataBaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_edit_my_acc);
        db = new DataBaseHandler(this);
        button = findViewById(R.id.buttonPress);
        newName = findViewById(R.id.editTextPersonName);
        newEmail = findViewById(R.id.editTextEmail);
        error = findViewById(R.id.errorEditSeller);
        newPhone = findViewById(R.id.editTextNumber);
        seller = Seller.activeSeller;
        button.setOnClickListener(v -> {
            if (newName.getText().toString().equals("") || newEmail.getText().toString().equals("")  || newPhone.getText().toString().equals("")) {
                error.setText("تمام فیلد ها را پر کنید!");
            }
            else if (!checkEmail(newEmail.getText().toString())) {
                error.setText("فرد دیگری با این ایمیل موجود است!");
            }

            else if (!checkNumber(newPhone.getText().toString())) {
                error.setText("فرد دیگری با این شماره موجود است!" );
            }
            else {
                db.updateSeller(seller,newName.getText().toString(),newEmail.getText().toString(), newPhone.getText().toString());
                Seller.activeSeller = db.getSellerById(seller.getId());
                Toast.makeText(this,"تغییرات با موفقیت اعمال شد",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,SellerHomePageActivity.class));
            }
        });

    }

    public boolean checkEmail(String email) {
        List<Seller> sellerList = db.getAllSellers();
        for (Seller seller : sellerList) {
            if (email.equalsIgnoreCase(seller.getEmail())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkNumber(String phone) {
        List<Seller> sellerList = db.getAllSellers();
        for (Seller seller : sellerList) {
            if (phone.equals(seller.getPhoneNumber())) {
                return false;
            }
        }
        return true;
    }
}