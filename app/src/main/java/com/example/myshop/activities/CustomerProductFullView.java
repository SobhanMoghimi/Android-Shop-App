package com.example.myshop.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myshop.R;
import com.example.myshop.dataBase.DataBaseHandler;
import com.example.myshop.model.Product;

public class CustomerProductFullView extends AppCompatActivity
{
    private ImageView product_pic;
    private TextView product_name, product_price, product_category, product_description,seller_name,seller_phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_fullview_customer);
        if(android.os.Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.secondaryLight));
        }

        DataBaseHandler  db= new DataBaseHandler(this);
        product_pic = findViewById(R.id.iv_product_customer_fullView_);
        product_name = findViewById(R.id.tv_product_name_customer_fullView);
        product_price = findViewById(R.id.tv_product_price_customer_fullView);
        product_category = findViewById(R.id.tv_product_category_customer_fullView);
        product_description = findViewById(R.id.tv_product_fullView_customer_description);
        seller_name = findViewById(R.id.tv_product_seller_name_customer_fullView);
        seller_phoneNumber=findViewById(R.id.tv_product_seller_number_customer_fullView);

        Product product= Product.getWorkingProduct();
        product_name.setText(product.getName());
        String price = product.getPrice() + "تومان";
        product_price.setText(price);
        product_description.setText(product.getDescription());
        product_category.setText(product.getCategoryString());
        product_pic.setImageBitmap(product.getImage());
        seller_phoneNumber.setText(product.getSeller().getPhoneNumber());
        seller_name.setText(product.getSeller().getFullName());

        seller_phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder dialog = new AlertDialog.Builder(CustomerProductFullView.this);
                dialog.setCancelable(false);
                dialog.setTitle("تماس با فروشنده!");
                dialog.setMessage("آیا میخواهید با این فروشنده تماس بگیرید؟" );
                dialog.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Intent intent=new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:"+product.getSeller().getPhoneNumber()));
                        startActivity(intent);
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
        });
    }
}