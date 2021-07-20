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
import com.example.myshop.activities.CustomerChangePass;
import com.example.myshop.activities.CustomerEditMyAccActivity;
import com.example.myshop.model.Customer;

public class CustomerMyAccFragment extends Fragment {

    private AppCompatButton editInfoBtn,changePass;
    private TextView name,email;
    Customer customer;


    public CustomerMyAccFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_customer_myacc, container, false);
        customer = Customer.activeCustomer;
        name = view.findViewById(R.id.customerName);
        email = view.findViewById(R.id.customerEmail);
        editInfoBtn = view.findViewById(R.id.editCustomerInfo);
        changePass = view.findViewById(R.id.changeCustomerPass);

        customer = Customer.activeCustomer;

        name.setText(customer.getFullName());
        email.setText(customer.getEmail());

        editInfoBtn.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), CustomerEditMyAccActivity.class));
        });

        changePass.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), CustomerChangePass.class));
        });

        return view;
    }
}
