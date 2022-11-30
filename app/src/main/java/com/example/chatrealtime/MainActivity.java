package com.example.chatrealtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Toast;

import com.example.chatrealtime.Adapter.RootAdapter;
import com.example.chatrealtime.Fragment.LoginFragment;
import com.example.chatrealtime.Fragment.PhoneFragment;
import com.example.chatrealtime.Fragment.SignUpFragment;
import com.example.chatrealtime.Transform.ZoomOutPageTransformer;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        viewPager2 = findViewById(R.id.view_pager_2_root);
        tabLayout = findViewById(R.id.tab_layout_root);
        RootAdapter adapter = new RootAdapter(getSupportFragmentManager(),getLifecycle(),getListFragment());
        viewPager2.setAdapter(adapter);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setOffscreenPageLimit(1);
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Login");
                    Toast.makeText(this, "ajshvdahsdahsdbj", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    tab.setText("Sign Up");

                    break;
            }
        }).attach();
    }

    @Override
    public void onBackPressed() {
        viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
        super.onBackPressed();
    }

    private List<Fragment> getListFragment(){
        List<Fragment> list = new ArrayList<>();
        list.add(new LoginFragment());
        list.add(new PhoneFragment());
        return list;
    }
}