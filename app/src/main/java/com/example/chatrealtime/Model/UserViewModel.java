package com.example.chatrealtime.Model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;

public class UserViewModel extends ViewModel {
    private final MutableLiveData<FirebaseUser> accounts = new MutableLiveData<>();

    public MutableLiveData<FirebaseUser> getAccounts() {
        return accounts;
    }

    public void setAccounts(FirebaseUser account){
        accounts.setValue(account);
    }
}
