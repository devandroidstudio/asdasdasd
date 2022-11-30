package com.example.chatrealtime.Adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class RootAdapter  extends FragmentStateAdapter {
    List<androidx.fragment.app.Fragment> list;

    public RootAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<androidx.fragment.app.Fragment> list) {
        super(fragmentManager, lifecycle);
        this.list = list;
    }


    @NonNull
    @Override
    public androidx.fragment.app.Fragment createFragment(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        if (!list.isEmpty()) return list.size();
        return 0;
    }
}
