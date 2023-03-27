package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class RegisterEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_event);
        findViewById(R.id.registerEventButton).setOnClickListener(view -> {
            Intent i = new Intent(this,AddEventActivity.class);
            i.putExtra("clubName", getIntent().getStringExtra("clubName"));
            startActivity(i);
        });
    }
}