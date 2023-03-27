package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class CoordLoginActivity extends AppCompatActivity {
    Button submitButton;
    EditText emailTxt,passwdTxt;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coord_login);
        submitButton = findViewById(R.id.submitButton);
        auth = FirebaseAuth.getInstance();
        emailTxt = findViewById(R.id.emailText);
        passwdTxt = findViewById(R.id.passwordText);
        submitButton.setOnClickListener(view -> {
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
                        Toast.makeText(getApplicationContext(),"Ho gaya login Coord ka", Toast.LENGTH_LONG).show();
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