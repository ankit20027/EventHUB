package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = "activitylog";
    Button signup, loginButton;
    EditText emailTxt,passwdTxt;
    boolean passwordVisible;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        emailTxt = findViewById(R.id.emailText);
        passwdTxt = findViewById(R.id.passwordText);
        signup=findViewById(R.id.signup);
        signup.setOnClickListener(view -> {
            Intent j = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(j);
        });
        loginButton = findViewById(R.id.submitButton);
        loginButton.setOnClickListener(view -> {
            String email = emailTxt.getText().toString(), passwd = passwdTxt.getText().toString();
            if (email.isEmpty()) {
                emailTxt.setError("Enter the valid email");
                emailTxt.requestFocus();
            } else if (passwd.length()<6) {
                passwdTxt.setError("Enter password longer than 6 chars");
                passwdTxt.requestFocus();
            } else {
                auth.signInWithEmailAndPassword(email,passwd).addOnCompleteListener(this, task-> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(this, ClubsList.class);
                        startActivity(intent);
                        Log.i(TAG, "successful login");
                    } else {
                        // make user already exist error prompt
                        emailTxt.setError("Enter the valid email");
                        emailTxt.requestFocus();
                    }
                });
            }
        });
        passwdTxt.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if(event.getRawX()>=passwdTxt.getRight()-passwdTxt.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = passwdTxt.getSelectionEnd();
                        if(passwordVisible){
                            passwdTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_visibility_off_24,0);
                            passwdTxt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        }
                        else {
                            passwdTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_visibility_24,0);
                            passwdTxt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        passwdTxt.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
    }
}