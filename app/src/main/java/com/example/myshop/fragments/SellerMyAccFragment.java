package com.example.myshop.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.myshop.R;
import com.example.myshop.activities.SellerChangePassword;
import com.example.myshop.activities.SellerEditMyAccActivity;
import com.example.myshop.model.Seller;

public class SellerMyAccFragment extends Fragment {


    private AppCompatButton editButton,changePassButton;
    private TextView name,email,phoneNumber;
    private Seller seller;


    public SellerMyAccFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_seller_my_acc, container, false);
        editButton = view.findViewById(R.id.seller_edit);
        changePassButton = view.findViewById(R.id.seller_password_edit);
        name = view.findViewById(R.id.seller_myacc_name);
        email = view.findViewById(R.id.seller_myacc_email);
        phoneNumber = view.findViewById(R.id.seller_myacc_phone);

        seller = Seller.activeSeller;

        name.setText(seller.getFullName().toString());
        email.setText(seller.getEmail().toString());
        phoneNumber.setText(seller.getPhoneNumber().toString());

        editButton.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), SellerEditMyAccActivity.class));
        });

        changePassButton.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), SellerChangePassword.class));
        });
        return view;
    }
}