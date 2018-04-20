package com.ray100.reidha;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by ray100 on 17/04/18.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passField;
    private Button loginBtn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAutStateListener;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        emailField = (EditText) findViewById(R.id.input_email);
        passField = (EditText) findViewById(R.id.input_password);
        loginBtn = (Button) findViewById(R.id.btn_login);

        mAutStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(LoginActivity.this, ProfilActivity.class));
                }
            }
        };

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });

        final TextView register = (TextView) findViewById(R.id.link_signup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAutStateListener);
    }

    private void handleLogin(){
        String email = emailField.getText().toString().trim();
        String password = passField.getText().toString().trim();

       if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
           Toast.makeText(LoginActivity.this, "Fields are Empty.",
                   Toast.LENGTH_SHORT).show();
       }else {
           mAuth.createUserWithEmailAndPassword(email, password)
                   .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (!task.isSuccessful()) {
                               // Sign in success, update UI with the signed-in user's information
                               Toast.makeText(LoginActivity.this, "Authentication failed.",
                                       Toast.LENGTH_SHORT).show();

                           }

                           // ...
                       }
                   });
       }
    }


}
