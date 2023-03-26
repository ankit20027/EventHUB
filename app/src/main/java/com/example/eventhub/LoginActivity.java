package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = "activitylog";
    Button signup, loginButton;
    EditText emailTxt,passwdTxt;
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
//                        Log.i(TAG, "successful login");
                        Toast.makeText(getApplicationContext(),"Ho gaya login", Toast.LENGTH_LONG).show();
                    } else {
                        // make user already exist error prompt
                        emailTxt.setError("Enter the valid email");
                        emailTxt.requestFocus();
                    }
                });
            }
        });
    }
}