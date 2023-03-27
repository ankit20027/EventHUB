package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button login_student;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reff = FirebaseDatabase.getInstance().getReference();
        login_student=findViewById(R.id.LoginAsStudent);
        login_student.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        });
        findViewById(R.id.LoginAsCoordinator).setOnClickListener(view -> {
            Intent i = new Intent(this, CoordLoginActivity.class);
            startActivity(i);
            // fetching events
//            List<Event> eventList = new ArrayList<>();
//            reff.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot snapshot) {
//                    eventList.clear();
//                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {
//                        Event event = postSnapshot.getValue(Event.class);
//                        eventList.add(event);
//                        Toast.makeText(getApplicationContext(),"Event Venue is: "+event.getVenue(), Toast.LENGTH_LONG).show();
//                    }
//                }
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    System.out.println("The read failed");
//                }
//            });

        });
    }

}