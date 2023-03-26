package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EventPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        // Fetching Events detail from prev component.
        Intent intent = getIntent();
        String event_name = intent.getStringExtra("EVENT");
        String organizer = intent.getStringExtra("CLUB_NAME");
        String desc = intent.getStringExtra("DESCRIPTION");
        String date = intent.getStringExtra("TIME");
        String venue = intent.getStringExtra("VENUE");

        String description = "ORGANIZER: " + organizer + "\n\n"  + "DATE: " + date + "\n\nVENUE: " + venue ;
        description += "\n\n" + desc;

        // Set text in views.
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView eventName = findViewById(R.id.eventName);
        eventName.setTypeface(null, Typeface.BOLD_ITALIC);
        eventName.setText(event_name);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView descriptionText = findViewById(R.id.description);
        descriptionText.setText(description);

    }
}