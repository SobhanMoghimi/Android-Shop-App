package com.example.myshop.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.myshop.R;
import com.example.myshop.activities.CustomerHomePageActivity;
import com.example.myshop.dataBase.DataBaseHandler;
import com.example.myshop.model.Customer;
import com.example.myshop.model.Seller;

import java.util.List;

public class CustomerLoginFragment extends Fragment
{
    private AppCompatButton loginButton;
    private EditText emailEditText,passwordEditText;
    private TextView errorTextView,forgetPasswordTextView,registerTextView;
    public static Customer customer;
    DataBaseHandler db;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_customer_login,container,false);
        db = new DataBaseHandler(getActivity());
        loginButton=view.findViewById(R.id.customer_button_login);
        emailEditText=view.findViewById(R.id.edit_text_username);
        passwordEditText=view.findViewById(R.id.edit_text_password);
        errorTextView=view.findViewById(R.id.text_view_login_error);
        forgetPasswordTextView=view.findViewById(R.id.text_view_forgot_password);
        registerTextView=view.findViewById(R.id.text_view_register);
        List<Customer> allCustomers = db.getAllCustomers();
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Navigation.findNavController(view).navigate(R.id.action_customerLoginFragment_to_customerRegisterFragment);
            }
        });
        loginButton.setOnClickListener(v -> {

            boolean found = false;
            if (emailEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")) {
                errorTextView.setText("باید تمام فیلد ها را پر کنید!");
            }
            else {
                for (Customer customer1 : allCustomers) {
                    if (customer1.getEmail().equalsIgnoreCase(emailEditText.getText().toString()))
                    {
                        if (customer1.getPassword().equals(passwordEditText.getText().toString()))
                        {
                            customer = customer1;
                            found = true;
                            boolean updated = db.updateCustomerLogCount(customer,customer.getLoginCount()+1);
                            customer.setLoginCount(customer.getLoginCount()+1);
                            Customer.activeCustomer=customer;
                            Seller.activeSeller=null;
                            startActivity(new Intent(getActivity(), CustomerHomePageActivity.class));
                        }
                        else {
                            errorTextView.setText("رمز عبور اشتباه است!");
                            break;
                        }
                    }
                }
                if (!found){
                    errorTextView.setText("شما عضو نیستید!");
                }
            }

        });
        return view;
    }
}
