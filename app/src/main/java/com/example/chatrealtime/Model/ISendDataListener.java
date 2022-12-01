package com.example.chatrealtime.Model;

public interface ISendDataListener {
    void sendDataSignUp(Account account);
    void sendDataSignWithPhone(String phoneNumber);
    void sendDataSignInWithEmailPassword(Account account);
}
