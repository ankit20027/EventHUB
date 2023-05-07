package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddEventActivity extends AppCompatActivity {
    Button submitButton;
    EditText nameTxt, venueTxt, descTxt, orgTxt, timeTxt;
    CheckBox pastChkBox;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        ConstraintLayout constraintLayout = findViewById(R.id.constraint_ID);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5500);
        animationDrawable.start();

        String clubName = getIntent().getStringExtra("clubName");
        reff = FirebaseDatabase.getInstance().getReference();
        submitButton = findViewById(R.id.submitButton);
        nameTxt = findViewById(R.id.nameText);
        descTxt = findViewById(R.id.descText);
        venueTxt = findViewById(R.id.venueText);
        orgTxt = findViewById(R.id.organizerText);
        timeTxt = findViewById(R.id.timeText);
        submitButton.setOnClickListener(view -> {
            String  name = nameTxt.getText().toString(),
                    desc = descTxt.getText().toString(),
                    venue = venueTxt.getText().toString(),
                    org = orgTxt.getText().toString(),
                    time = timeTxt.getText().toString();
            reff.child("Clubs").child(clubName).push().setValue(new Event(name,time,venue,desc,org,false));
            reff.child("ClubsName").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    List<String> clubNames = new ArrayList<>(); // fetch club list
                    boolean hasClub = false;
                    for (DataSnapshot ds: snapshot.getChildren()) {
                        String c_name = ds.getValue(String.class);
                        if (c_name.equals(clubName)) {
                            hasClub = true;
                        }
                        clubNames.add(c_name);
                    }
                    if (!hasClub) {
                        clubNames.add(clubName);
                        reff.child("ClubsName").setValue(clubNames);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed");
                }
            });
        Toast.makeText(this, "Successfully added the event.", Toast.LENGTH_SHORT).show();
            onDestroy();
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        return ;
    }
}