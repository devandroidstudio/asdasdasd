package com.example.chatrealtime.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.chatrealtime.Model.Account;

import com.example.chatrealtime.Model.ISendDataListener;
import com.example.chatrealtime.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


public class SignUpFragment extends Fragment {



    public SignUpFragment() {
        // Required empty public constructor
    }
    TextInputLayout inputEditEmail,inputEditPassword,inputLayoutFullName;
    TextInputEditText editEmail, editPassword;
    Button btnSignUp;
    ISendDataListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        inputEditEmail = view.findViewById(R.id.edit_email_sign_up);
        inputEditPassword = view.findViewById(R.id.edit_password_sing_up);
        editEmail = view.findViewById(R.id.text_edit_email_sign_up);
        editPassword = view.findViewById(R.id.text_edit_password_sign_up);
        inputLayoutFullName = view.findViewById(R.id.edit_full_name_sign_up);
        btnSignUp = view.findViewById(R.id.btn_sign_up);
        action();
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (ISendDataListener) getActivity();
    }

    private void action(){
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
                else if (editable.length() == 0){
                    inputEditPassword.setError("Password is not null");
                }
                else {
                    inputEditPassword.setError(null);
                    inputEditPassword.setErrorEnabled(false);
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalDateTime localDate = LocalDateTime.now();

                if (!inputEditEmail.isErrorEnabled() && !inputEditPassword.isErrorEnabled() && !inputEditEmail.getEditText().getText().toString().isEmpty() && !inputEditPassword.getEditText().getText().toString().isEmpty()){
                    Account account = new Account(Objects.requireNonNull(inputLayoutFullName.getEditText()).getText().toString(),editEmail.getText().toString(),editPassword.getText().toString(),localDate,true);
                    listener.sendDataSignUp(account);
                }
                else {
                    Toast.makeText(getContext(), "Please check your information again", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}