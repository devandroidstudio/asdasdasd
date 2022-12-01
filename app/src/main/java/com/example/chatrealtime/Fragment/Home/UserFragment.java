package com.example.chatrealtime.Fragment.Home;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.chatrealtime.Model.UserViewModel;
import com.example.chatrealtime.R;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


public class UserFragment extends Fragment {


    public UserFragment() {
        // Required empty public constructor
    }


    CircleImageView imageView;
    MaterialCardView cardViewGallery;
    UserViewModel userViewModel;
    Uri uri;
    String name;
    LinearLayout linearLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        imageView = view.findViewById(R.id.profile_image);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        userViewModel.getAccounts().observe(requireActivity(), item -> {
                uri = item.getPhotoUrl();
                name = item.getDisplayName();
        });
        linearLayout = view.findViewById(R.id.layout_dark_mode);
        Picasso.get().load(uri).placeholder(R.drawable.ic_baseline_person_pin_24).error(R.drawable.ic_baseline_error_24).into(imageView);
        cardViewGallery = view.findViewById(R.id.card_view_gallery);
        cardViewGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDarkMode();
            }
        });
        return view;
    }

    private void showDialogDarkMode() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setTitle("Please enter information login");
        dialog.setContentView(R.layout.dialog_dark_mode);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        RadioGroup radioGroup = dialog.findViewById(R.id.rbtn_dark_mode);
        RadioButton rBtnTurnOff = dialog.findViewById(R.id.rbtn_turn_off);
        RadioButton rBtnTurnOn = dialog.findViewById(R.id.rbtn_turn_on);
        RadioButton rBtnSystem = dialog.findViewById(R.id.rbtn_system);
        rBtnTurnOff.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.getCheckedRadioButtonId() == R.id.rbtn_turn_on){
                    Toast.makeText(getContext(), "asjkdhsd", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();

    }
}