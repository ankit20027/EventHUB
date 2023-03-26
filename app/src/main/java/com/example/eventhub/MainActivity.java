package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button login_student;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reff = FirebaseDatabase.getInstance().getReference("Clubs");
        login_student=findViewById(R.id.LoginAsStudent);
        login_student.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        });
        findViewById(R.id.LoginAsCoordinator).setOnClickListener(view -> {
            Intent i = new Intent(this, CoordLoginActivity.class);
            startActivity(i);
            // aise data push karna hai
//            ArrayList<Event> myEventArray = new ArrayList<>();
//            myEventArray.add(new Event("Ankit", "12-10-21 10:02:20", "C101", "Some Random Shit", true));
//            Club clb = new Club("My Club", myEventArray);
//            reff.push().setValue(clb);
        });
    }

}