package com.example.myshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.myshop.R;
import com.example.myshop.fragments.CustomerCategoriesFragment;
import com.example.myshop.fragments.CustomerHomeFragment;
import com.example.myshop.fragments.CustomerMyAccFragment;
import com.example.myshop.fragments.SellerHomeFragment;
import com.example.myshop.fragments.SellerMyAccFragment;
import com.example.myshop.fragments.SellerPostFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class CustomerHomePageActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home_page);

        bottomNavigationView = findViewById(R.id.customer_nav_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
        frameLayout = findViewById(R.id.customer_frame_layout);
        getSupportFragmentManager().beginTransaction().replace(R.id.customer_frame_layout,new CustomerHomeFragment()).commit();
    }

    @Override
    public void onBackPressed()
    {

    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

            Fragment selected_fragment = null;
            switch (item.getItemId()) {
                case R.id.home:
                    selected_fragment = new CustomerHomeFragment();
                    break;
                case R.id.categories:
                    selected_fragment = new CustomerCategoriesFragment();
                    break;
                case R.id.customer_account:
                    selected_fragment = new CustomerMyAccFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.customer_frame_layout, selected_fragment).commit();

            return true;
        }
    };
}