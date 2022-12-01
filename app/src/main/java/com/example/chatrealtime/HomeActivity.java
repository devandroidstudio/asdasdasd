package com.example.chatrealtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.chatrealtime.Adapter.HomeAdapter;
import com.example.chatrealtime.Fragment.Home.ChatFragment;
import com.example.chatrealtime.Model.UserViewModel;
import com.example.chatrealtime.Transform.ZoomOutPageTransformer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ViewPager2 viewPager2;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.MATCH_PARENT);
        init();
        action();

    }
    private void init(){
        viewPager2 = findViewById(R.id.viewPager2_home);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        HomeAdapter adapter = new HomeAdapter(getSupportFragmentManager(),getLifecycle(),Const.getListFragment());
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        viewPager2.setAdapter(adapter);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.setAccounts(FirebaseAuth.getInstance().getCurrentUser());

    }
    private void action() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bottom_chat:
                        viewPager2.setCurrentItem(0);

                        break;
                    case R.id.bottom_contact:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.bottom_apps:
                        viewPager2.setCurrentItem(2);
                        break;
                    case R.id.bottom_account:
                        viewPager2.setCurrentItem(3);
                        break;
                    default:
                        viewPager2.setCurrentItem(0);
                        break;
                }
                return true;
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.bottom_chat).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.bottom_contact).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.bottom_apps).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.bottom_account).setChecked(true);
                        break;
                }
            }
        });
    }


}