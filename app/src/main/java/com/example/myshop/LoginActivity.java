package com.example.myshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private AppCompatButton customerButton,sellerButton;
    private EditText emailEditText,passwordEditText;
    private TextView errorTextView,forgetPasswordTextView,registerTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(android.os.Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.primaryDark));
        }
        customerButton=findViewById(R.id.customer_button);
        emailEditText=findViewById(R.id.edit_text_username);
        passwordEditText=findViewById(R.id.edit_text_password);
        errorTextView=findViewById(R.id.text_view_login_error);
        forgetPasswordTextView=findViewById(R.id.text_view_forgot_password);
        registerTextView=findViewById(R.id.text_view_register);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        customerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"Later maybe!",Toast.LENGTH_LONG).show();
            }
        });
    }
}