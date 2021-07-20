package com.example.myshop.fragments;

import android.content.Intent;
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
import com.example.myshop.activities.SellerHomePageActivity;
import com.example.myshop.dataBase.DataBaseHandler;
import com.example.myshop.model.Seller;

import java.util.List;

public class SellerLoginFragment extends Fragment
{
    private AppCompatButton loginButton;
    private EditText emailEditText,passwordEditText;
    private TextView errorTextView,forgetPasswordTextView,registerTextView;
    DataBaseHandler db;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_seller_login,container,false);
        loginButton=view.findViewById(R.id.seller_button_login);
        emailEditText=view.findViewById(R.id.edit_text_seller_login_email);
        passwordEditText=view.findViewById(R.id.edit_text_seller_login_password);
        errorTextView=view.findViewById(R.id.text_view_login_error);
        forgetPasswordTextView=view.findViewById(R.id.text_view_seller_forgot_password);
        registerTextView=view.findViewById(R.id.text_view_seller_register);
        db = new DataBaseHandler(getActivity());
        List<Seller> allSellers = db.getAllSellers();

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Navigation.findNavController(view).navigate(R.id.action_sellerLoginFragment_to_sellerRegisterFragment);
            }
        });



        loginButton.setOnClickListener(v -> {

            boolean found = false;
            if (emailEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals(""))
            {
                errorTextView.setText("تمامی اطلاعات را وارد کنید!");
            }
            else if (){
                for(Seller seller : allSellers) {
                    if (seller.getEmail().equalsIgnoreCase(emailEditText.getText().toString()))
                    {
                        if (seller.getPassword().equals(passwordEditText.getText().toString()))
                        {
                            found = true;
                            boolean isUpdated = db.updateSellerLogCount(seller,seller.getLoginCount()+1);
                            seller.setLoginCount(seller.getLoginCount()+1);
                            seller.setId(seller.getId());
                            Seller.setActiveSeller(seller);
                            startActivity(new Intent(getActivity(),SellerHomePageActivity.class));
                            break;
                        }
                        else {
                            errorTextView.setText("رمز عبور اشتباه است!");
                            break;
                        }
                    }
                }
                if (!found) {
                    errorTextView.setText("شما عضو نیستید!");
                }
            }
        });

        return view;
    }
}
