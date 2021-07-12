package com.example.myshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.myshop.R;

public class CustomerRegisterFragment extends Fragment
{
    private AppCompatButton registerButton;
    private EditText emailText,registerPassword,usernameText,registerPasswordRepeat;
    private TextView loginTextView;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_customer_register,container,false);
        registerPasswordRepeat=view.findViewById(R.id.customer_register_password_repeat);
        registerButton=view.findViewById(R.id.customer_register_button);
        emailText=view.findViewById(R.id.custom_register_email);
        registerPassword=view.findViewById(R.id.customer_register_password);
        loginTextView=view.findViewById(R.id.customer_text_view_login);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Navigation.findNavController(view).navigate(R.id.action_customerRegisterFragment_to_customerLoginFragment);
            }
        });
        return view;
    }
}