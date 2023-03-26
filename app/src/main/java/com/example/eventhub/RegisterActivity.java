package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private final String TAG = "activitylog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        Button signupButton = findViewById(R.id.signupButton);
        signupButton.setOnClickListener(view -> {
            EditText emailTxt = (EditText)findViewById(R.id.emailText), passwdTxt = (EditText)findViewById(R.id.passwordText), confirmPasswordTxt = (EditText)findViewById(R.id.confirmPasswordText);
            String email = emailTxt.getText().toString(), passwd =passwdTxt.getText().toString();
            Log.i(TAG, email+" || "+passwd);
            if (email.isEmpty()) {
                emailTxt.setError("Enter the valid email");
                emailTxt.requestFocus();
            } else if (passwd.length()<6) {
                passwdTxt.setError("Enter password longer than 6 chars");
                passwdTxt.requestFocus();
            } else if (!confirmPasswordTxt.getText().toString().equals(passwd)) {
                confirmPasswordTxt.setError("Confirm password and Password");
                confirmPasswordTxt.requestFocus();
            } else {
                auth.createUserWithEmailAndPassword(email, passwd).addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.i(TAG, "successful signup");
                        this.finish();
                    } else {
                        // make user already exist error prompt
                        emailTxt.setError("Enter the valid email");
                        emailTxt.requestFocus();
                    }
                });
            }
        });
        AppCompatButton loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(view -> {
            this.finish();
        });
    }
}