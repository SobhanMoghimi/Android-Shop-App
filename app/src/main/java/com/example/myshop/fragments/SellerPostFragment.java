package com.example.myshop.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myshop.R;
import com.example.myshop.activities.SellerHomePageActivity;
import com.example.myshop.dataBase.DataBaseHandlerProduct;
import com.example.myshop.model.Product;
import com.example.myshop.model.Seller;
import com.example.myshop.fragments.SellerRegisterFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.Calendar;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class SellerPostFragment extends Fragment {

    private Product product;
    Seller seller;
    SellerRegisterFragment sellerRegisterFragment;
    Spinner categorySpinner;
    String chosenCategory;
    EditText name,price,description;
    ImageView image;
    FloatingActionButton add;
    private Uri imageFilePath;
    private Bitmap imageToStore;
    AppCompatButton post;

    public SellerPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seller_post,container,false);
        categorySpinner = view.findViewById(R.id.category);
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        image = view.findViewById(R.id.imageView);
        name = view.findViewById(R.id.productName);
        price = view.findViewById(R.id.productPrice);
        sellerRegisterFragment = new SellerRegisterFragment();
        seller = sellerRegisterFragment.getSeller();
        description = view.findViewById(R.id.description);
        add = view.findViewById(R.id.addingPhotoButton);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.categories, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);


        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int
                    i, long l) {
                chosenCategory = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent();
                    intent.setType("image/*");

                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent,100);
                } catch (Exception e) {
                    Toast.makeText(getActivity(),e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

//    public void chooseImage(View view) {
//        Intent intent = new Intent();
//        intent.setType("image/");
//
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent,100);
//    }

    public void post(View view) {
        try {
            if (name.getText().toString().equals("") || price.getText().toString().equals("")) {
                //شبیه ارور تکست فیلد باید بذاری اینجا
                Toast.makeText(getActivity(),"error",Toast.LENGTH_SHORT).show();

            }
            else {
                product = new Product(name.getText().toString(),Integer.parseInt(price.getText().toString()),imageToStore,seller,description.getText().toString(), Calendar.getInstance().getTime(), chosenCategory);
                DataBaseHandlerProduct db = new DataBaseHandlerProduct(getActivity());

                boolean success = db.addProduct(product);
                if (success) {
                    Toast.makeText(getActivity(),"added",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(),"not successful", Toast.LENGTH_SHORT).show();
                }

            }
        } catch (Exception e) {
            //ارور
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && resultCode== SellerHomePageActivity.RESULT_OK && data!=null && data.getData()!=null) {
            imageFilePath=data.getData();
            try {
                imageToStore = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imageFilePath);
            } catch (IOException e) {
                Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
            image.setImageBitmap(imageToStore);
        }
    }
}