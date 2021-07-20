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

import java.util.List;

public class CustomerRegisterFragment extends Fragment
{
    private AppCompatButton registerButton;
    private EditText emailText,registerPassword,usernameText,registerPasswordRepeat;
    private TextView loginTextView,errorField;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_customer_register,container,false);
        registerPasswordRepeat=view.findViewById(R.id.customer_register_password_repeat);
        registerButton=view.findViewById(R.id.customer_register_button);
        errorField = view.findViewById(R.id.text_view_customer_register_error);
        usernameText = view.findViewById(R.id.customer_register_username);
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

        registerButton.setOnClickListener(v -> {
            try {
                if (registerPassword.getText().toString().equals("") || emailText.getText().toString().equals("") || registerPasswordRepeat.getText().toString().equals("") || usernameText.getText().toString().equals("")) {
                    errorField.setText("اطلاعات وارد شده کافی نیست!");
                }

                else if (!registerPassword.getText().toString().equals(registerPasswordRepeat.getText().toString())) {
                    errorField.setText("رمز عبور مطابقت ندارد!");
                }
                else if(!checkEmail(emailText.getText().toString()))
                {
                    errorField.setText("کاربر با این ایمیل موجود است !");
                }
                else if (emailPatternCheck(emailText.getText().toString().trim())){
                    DataBaseHandler db = new DataBaseHandler(getActivity());
                    Customer customer = new Customer(usernameText.getText().toString().trim(), emailText.getText().toString().trim(), registerPassword.getText().toString());
                    boolean success = db.addCustomer(customer);
                    if (success) {
                        Customer.setActiveCustomer(customer);
                        //Seller.activeSeller=null;
                        Toast.makeText(getActivity(),"خوش آمدید",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), CustomerHomePageActivity.class));
                    }
                    else {
                        errorField.setText("ثبت نام با خطا مواجه شده است!");
                    }
                }
                else {
                    errorField.setText("ایمیل معتبر نیست!");
                }
            } catch (Exception e) {
                errorField.setText(e.getMessage().toString());
            }
        });
        return view;
    }

    public boolean checkEmail(String email) {
        DataBaseHandler db = new DataBaseHandler(getActivity());
        List<Customer> customers = db.getAllCustomers();
        for (Customer customer1 : customers) {
            if (customer1.getEmail().equalsIgnoreCase(emailText.getText().toString())) {
                return false;
            }
        }
        return true;
    }

    public boolean emailPatternCheck(String email) {
        if (email.matches(emailPattern))
            return true;
        return false;
    }

}