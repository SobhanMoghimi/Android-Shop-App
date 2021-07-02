package com.example.myshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    private AppCompatButton registerButton;
    private EditText emailText,registerPassword,usernameText;
    private TextView forgetPasswordTextView,loginTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if(android.os.Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.primaryDark));
        }
        registerButton=findViewById(R.id.register_button);
        emailText=findViewById(R.id.register_email);
        registerPassword=findViewById(R.id.edit_text_password);
        forgetPasswordTextView=findViewById(R.id.text_view_forgot_password);
        loginTextView=findViewById(R.id.text_view_login);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }
}