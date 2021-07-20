package com.example.myshop.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.myshop.R;
import com.example.myshop.activities.SellerHomePageActivity;
import com.example.myshop.dataBase.DataBaseHandler;
import com.example.myshop.model.Product;
import com.example.myshop.model.Seller;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

public class SellerPostFragment extends Fragment {

    private Product product;
    SellerRegisterFragment sellerRegisterFragment;
    Spinner categorySpinner;
    String chosenCategory;
    EditText name,price,description;
    ImageView image;
    FloatingActionButton add;
    private Uri imageFilePath;
    Seller seller=Seller.getActiveSeller();
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
        seller = Seller.activeSeller;
        image = view.findViewById(R.id.imageView);
        name = view.findViewById(R.id.productName);
        price = view.findViewById(R.id.productPrice);
        post = view.findViewById(R.id.postButton);
        sellerRegisterFragment = new SellerRegisterFragment();
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

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (name.getText().toString().equals("") || price.getText().toString().equals("") || chosenCategory.equals(""))
                    {
                        //شبیه ارور تکست فیلد باید بذاری اینجا
                        Toast.makeText(getActivity(),"empty fields",Toast.LENGTH_SHORT).show();
                    }
                    else if (imageToStore==null) {
                        Toast.makeText(getActivity(),"null Image",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        product = new Product(name.getText().toString(),Integer.parseInt(price.getText().toString()),imageToStore,seller,description.getText().toString(), chosenCategory);
                        DataBaseHandler db = new DataBaseHandler(getActivity());
                        boolean success = db.addProduct(product);
                        seller.setPosts(seller.getPosts()+1);
                        db.addSellerPostCount(seller,seller.getPosts()+1);
                        Seller.setActiveSeller(seller);
                        if (success)
                        {
                            Toast.makeText(getActivity(),"کالای شما ثبت شد.",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getActivity(),"not successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    //ارور
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && resultCode==SellerHomePageActivity.RESULT_OK && data!=null && data.getData()!=null) {
            imageFilePath = data.getData();
            try {
                imageToStore = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imageFilePath);
            } catch (IOException e) {
                Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
            image.setImageBitmap(imageToStore);
        }
    }
}