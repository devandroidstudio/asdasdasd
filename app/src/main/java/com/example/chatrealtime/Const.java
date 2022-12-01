package com.example.chatrealtime;

import androidx.fragment.app.Fragment;

import com.example.chatrealtime.Fragment.Home.ChatFragment;
import com.example.chatrealtime.Fragment.Home.ContactFragment;
import com.example.chatrealtime.Fragment.Home.DiscoverFragment;
import com.example.chatrealtime.Fragment.Home.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class Const {
    public static List<Fragment> getListFragment(){
        List<Fragment> list = new ArrayList<>();
        list.add(new ChatFragment());
        list.add(new ContactFragment());
        list.add(new DiscoverFragment());
        list.add(new UserFragment());
        return list;
    }
}
