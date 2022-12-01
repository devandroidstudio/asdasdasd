package com.example.chatrealtime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.chatrealtime.Adapter.RootAdapter;
import com.example.chatrealtime.Fragment.LoginFragment;
import com.example.chatrealtime.Fragment.PhoneFragment;
import com.example.chatrealtime.Fragment.SignInFragment;
import com.example.chatrealtime.Fragment.SignUpFragment;
import com.example.chatrealtime.Model.Account;
import com.example.chatrealtime.Model.ISendDataListener;
import com.example.chatrealtime.Transform.ZoomOutPageTransformer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements ISendDataListener {
    Button btnSignUp;
    FirebaseAuth mAuth;
    FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        btnSignUp = findViewById(R.id.btn_show_sign_up);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout_root, new SignInFragment()).commit();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnSignUp.getText().toString().equals("Sign Up")){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_root, new SignUpFragment()).commit();
                        btnSignUp.setText("Sign In");
                }else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_root, new SignInFragment()).commit();
                    btnSignUp.setText("Sign Up");
                }
            }
        });
        database = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            startActivity(getIntent());
        }else {
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
            finishAffinity();
        }
    }

    @Override
    public void sendDataSignUp(Account account) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(account.getFullName())
                        .build();
        mAuth.createUserWithEmailAndPassword(account.getEmail(),account.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user !=null){
                        user.updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(MainActivity.this, "Verification email has been sent", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(MainActivity.this,VerificationActivity.class);
                                                intent.putExtra("code_verify_login",account.getPassword());
                                                startActivity(intent);
                                                finishAffinity();
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                        InsertUser(account);

                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void InsertUser(Account account) {
        database.collection("Users").document(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).set(account).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    System.out.println("Insert user is success");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void sendDataSignWithPhone(String phoneNumber) {


    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

    }

    @Override
    public void sendDataSignInWithEmailPassword(Account account) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(account.getEmail(),account.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    overridePendingTransition(R.anim.sile_in_right,R.anim.sile_out_right);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //    @Override
//    public void onBackPressed() {
//        viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
//        super.onBackPressed();
//    }


}