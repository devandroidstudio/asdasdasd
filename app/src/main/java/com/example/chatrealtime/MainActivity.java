package com.example.chatrealtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chatrealtime.Adapter.RootAdapter;
import com.example.chatrealtime.Fragment.LoginFragment;
import com.example.chatrealtime.Fragment.PhoneFragment;
import com.example.chatrealtime.Fragment.SignInFragment;
import com.example.chatrealtime.Fragment.SignUpFragment;
import com.example.chatrealtime.Transform.ZoomOutPageTransformer;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignUp = findViewById(R.id.btn_sign_up);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout_root, new SignInFragment()).commit();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnSignUp.getText().toString().equals("Sign Up")){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_root, new SignUpFragment()).commit();
                        btnSignUp.setText("Sign In");
                }else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_root, new SignInFragment()).commit();
                    btnSignUp.setText("Sign Up");
                }


            }
        });
    }


//    @Override
//    public void onBackPressed() {
//        viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
//        super.onBackPressed();
//    }


}