package com.example.myshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.myshop.R;
import com.example.myshop.fragments.SellerHomeFragment;
import com.example.myshop.fragments.SellerMyAccFragment;
import com.example.myshop.fragments.SellerPostFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class SellerHomePageActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_home_page);

        bottomNavigationView = findViewById(R.id.seller_nav_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
        frameLayout = findViewById(R.id.seller_frame_layout);
        getSupportFragmentManager().beginTransaction().replace(R.id.seller_frame_layout,new SellerHomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
            Fragment selected_fragment = null;
            switch (item.getItemId()) {
                case R.id.home:
                    selected_fragment = new SellerHomeFragment();
                    break;
                case R.id.post:
                    selected_fragment = new SellerPostFragment();
                    break;
                case R.id.account:
                    selected_fragment = new SellerMyAccFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.seller_frame_layout,selected_fragment).commit();

            return true;
        }
    };
}