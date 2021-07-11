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

import com.example.myshop.R;
import com.example.myshop.activities.CustomerLoginActivity;
import com.example.myshop.activities.RegisterActivity;

public class CustomerLoginFragment extends Fragment
{
    private AppCompatButton customerButton,sellerButton;
    private EditText emailEditText,passwordEditText;
    private TextView errorTextView,forgetPasswordTextView,registerTextView;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_customer_login,container,false);

        customerButton=view.findViewById(R.id.customer_button_login);
        emailEditText=view.findViewById(R.id.edit_text_username);
        passwordEditText=view.findViewById(R.id.edit_text_password);
        errorTextView=view.findViewById(R.id.text_view_login_error);
        forgetPasswordTextView=view.findViewById(R.id.text_view_forgot_password);
        registerTextView=view.findViewById(R.id.text_view_register);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getActivity(), RegisterActivity.class));
            }
        });
        customerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Later maybe!",Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
