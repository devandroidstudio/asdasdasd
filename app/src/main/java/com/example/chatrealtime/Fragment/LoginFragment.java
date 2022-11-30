package com.example.chatrealtime.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.utils.Utils;
import com.example.chatrealtime.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }

    TextInputLayout inputEditEmail,inputEditPassword;
    TextInputEditText editEmail, editPassword;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        inputEditEmail = view.findViewById(R.id.edit_email_login);
        inputEditPassword = view.findViewById(R.id.edit_password_login);
        editEmail = view.findViewById(R.id.text_edit_email);
        editPassword = view.findViewById(R.id.text_edit_password);
        boolean check = false;
        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+")){
                    inputEditEmail.setError("Email is not matches (example:a@gmail.com");
                }
                else {
                    inputEditEmail.setErrorEnabled(false);
                    inputEditEmail.setError(null);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {



            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() < inputEditPassword.getCounterMaxLength()){
                    inputEditPassword.setError("Max character is length <" + inputEditPassword.getCounterMaxLength());
                }
                else {
                    inputEditPassword.setError(null);
                    inputEditPassword.setErrorEnabled(false);
                }
            }
        });
        return view;
    }
}