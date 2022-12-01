package com.example.chatrealtime.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatrealtime.Model.ISendDataListener;
import com.example.chatrealtime.R;
import com.google.android.material.textfield.TextInputEditText;
import com.hbb20.CountryCodePicker;


public class PhoneFragment extends Fragment {


    public PhoneFragment() {
        // Required empty public constructor
    }


    CountryCodePicker countryCodePicker;
    TextInputEditText editTextPhone;
    ImageView imageView;
    TextView textView;
    String codePhone;
    AppCompatButton button;
    ISendDataListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phone, container, false);
        countryCodePicker = view.findViewById(R.id.code_phone);
        editTextPhone = view.findViewById(R.id.text_edit_phone);
        imageView = view.findViewById(R.id.image_country);
        textView = view.findViewById(R.id.txt_code_phone);
        countryCodePicker.setImageViewFlag(imageView);
        button = view.findViewById(R.id.btn_login_phone);
        codePhone = countryCodePicker.getTextView_selectedCountry().getText().toString();

//        countryCodePicker.setTextView_selectedCountry(textView);
        textView.setText(countryCodePicker.getSelectedCountryName());



        editTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() < 10 || editable.length() > 10){
                    editTextPhone.setHintTextColor(Color.RED);
                }
            }
        });
        Action();
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        listener = (ISendDataListener) getActivity();
        super.onAttach(context);

    }

    private void Action() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.sendDataSignWithPhone(editTextPhone.getText().toString());
            }
        });
    }
}