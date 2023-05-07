package com.example.eventhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DiscussionActivity extends AppCompatActivity {
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        LinearLayout parent = findViewById(R.id.discLayout);
        Intent intent = getIntent();
        String club_name = intent.getStringExtra("CLUB_NAME"), key = intent.getStringExtra("KEY");
        reff = FirebaseDatabase.getInstance().getReference()
                .child("Clubs")
                .child(club_name).child(key)
                .child("discLs");
        findViewById(R.id.submitCommentBtn).setOnClickListener(view -> {
            String comment = ((EditText)findViewById(R.id.commentTxt)).getText().toString();
            reff.push().setValue(new DiscussionDataClass(LoginActivity.userEmail, comment));
        });
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ArrayList<DiscussionDataClass> ddcLs = new ArrayList<>();
                    for (DataSnapshot ds: snapshot.getChildren()) {
                        ddcLs.add(ds.getValue(DiscussionDataClass.class));
                    }
                    for (int i = 0; i < ddcLs.size(); i++) {
                        DiscussionDataClass ddc = ddcLs.get(i);
                        TextView txt = new TextView(getApplicationContext());
                        txt.setPadding(0,0,0,10);
                        txt.setText(ddc.getEmail() + "\n" + ddc.getTxt()+"\n\n");
                        parent.addView(txt);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed");
            }
        });
    }
}