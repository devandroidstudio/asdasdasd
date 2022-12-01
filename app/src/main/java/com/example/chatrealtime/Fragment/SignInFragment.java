package com.example.chatrealtime.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatrealtime.Adapter.RootAdapter;
import com.example.chatrealtime.R;
import com.example.chatrealtime.Transform.ZoomOutPageTransformer;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class SignInFragment extends Fragment {

    public SignInFragment() {
        // Required empty public constructor
    }

    ViewPager2 viewPager2;
    TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        viewPager2 = view.findViewById(R.id.view_pager_2_root);
        tabLayout = view.findViewById(R.id.tab_layout_root);
        RootAdapter adapter = new RootAdapter(getParentFragmentManager(),getLifecycle(),getListFragment());
        viewPager2.setAdapter(adapter);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setOffscreenPageLimit(1);
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Login");
                    break;
                case 1:
                    tab.setText("Phone number");
                    
                    break;
            }
        }).attach();

    }


    private List<Fragment> getListFragment(){
        List<Fragment> list = new ArrayList<>();
        list.add(new LoginFragment());
        list.add(new PhoneFragment());
        return list;
    }
}