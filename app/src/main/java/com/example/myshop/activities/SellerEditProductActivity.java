package com.example.myshop.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myshop.R;
import com.example.myshop.dataBase.DataBaseHandler;
import com.example.myshop.model.Product;
import com.example.myshop.model.Seller;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

public class SellerEditProductActivity extends AppCompatActivity
{
    private TextView product_name,product_price,product_description;
    private ImageView product_image;
    private AppCompatButton submitEditButton;
    private FloatingActionButton addPhotoButton,deleteProductButton;
    private Uri editImageFilePath;
    private Bitmap editImageToStore;

    public SellerEditProductActivity()
    {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_edit_product);
        product_name=findViewById(R.id.edit_product_productName);
        product_description=findViewById(R.id.edit_product_description);
        product_price=findViewById(R.id.edit_product_Price);
        addPhotoButton=findViewById(R.id.edit_product_addPhotoButton);
        submitEditButton=findViewById(R.id.edit_product_submitButton);
        product_image=findViewById(R.id.edit_product_imageView);
        Product product= Product.getWorkingProduct();
        product_name.setText(product.getName(),TextView.BufferType.EDITABLE);
        product_price.setText(Integer.toString(product.getPrice()),TextView.BufferType.EDITABLE);
        product_description.setText(product.getDescription(),TextView.BufferType.EDITABLE);
        product_image.setImageBitmap(product.getImage());

        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent,100);
                } catch (Exception e) {
                    Toast.makeText(SellerEditProductActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        submitEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try {
                    if (product_name.getText().toString().equals("") || product_price.getText().toString().equals(""))
                    {
                        //شبیه ارور تکست فیلد باید بذاری اینجا
                        Toast.makeText(SellerEditProductActivity.this,"empty fields",Toast.LENGTH_SHORT).show();
                    }
                    else if (editImageToStore==null) {
                        editImageToStore = product.getImage();
                    }
                    else
                        {
                        DataBaseHandler db = new DataBaseHandler(SellerEditProductActivity.this);
                        boolean success = db.updateProduct(product,product_name.getText().toString(),Integer.parseInt(product_price.getText().toString()),editImageToStore,product_description.getText().toString());
                        if (success)
                        {
                            Toast.makeText(SellerEditProductActivity.this,"کالای شما به روز رسانی شد.",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(SellerEditProductActivity.this, SellerHomePageActivity.class);
                            SellerEditProductActivity.this.startActivity(intent);
                        }
                        else
                            {
                            Toast.makeText(SellerEditProductActivity.this,"کالای مورد نظر به روز رسانی نشد!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e)
                {
                    Toast.makeText(SellerEditProductActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && resultCode==SellerHomePageActivity.RESULT_OK && data!=null && data.getData()!=null) {
            editImageFilePath = data.getData();
            try {
                editImageToStore = MediaStore.Images.Media.getBitmap(SellerEditProductActivity.this.getContentResolver(),editImageFilePath);
            } catch (IOException e) {
                Toast.makeText(SellerEditProductActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
            product_image.setImageBitmap(editImageToStore);
        }
    }
}