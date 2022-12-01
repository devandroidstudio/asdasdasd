package com.example.chatrealtime.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.utils.Utils;
import com.example.chatrealtime.Model.Account;
import com.example.chatrealtime.Model.ISendDataListener;
import com.example.chatrealtime.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }

    TextInputLayout inputEditEmail,inputEditPassword;
    TextInputEditText editEmail, editPassword;
    Button button;
    ISendDataListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        inputEditEmail = view.findViewById(R.id.edit_email_login);
        inputEditPassword = view.findViewById(R.id.edit_password_login);
        editEmail = view.findViewById(R.id.text_edit_email);
        editPassword = view.findViewById(R.id.text_edit_password);
        button = view.findViewById(R.id.btn_login);
        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!Patterns.EMAIL_ADDRESS.matcher(charSequence.toString()).matches()){
                    inputEditEmail.setError("Email is not matches (example:a@gmail.com");
                }
                else {
                    inputEditEmail.setErrorEnabled(false);
                    inputEditEmail.setError(null);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() < 1){
                    inputEditEmail.setErrorEnabled(false);
                    inputEditEmail.setError(null);

                }
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
                    inputEditPassword.setError("Max character is length >" + inputEditPassword.getCounterMaxLength());
                }
                else {
                    inputEditPassword.setError(null);
                    inputEditPassword.setErrorEnabled(false);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account = new Account(editEmail.getText().toString(),editPassword.getText().toString());
                listener.sendDataSignInWithEmailPassword(account);
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (ISendDataListener) getActivity();
    }
}