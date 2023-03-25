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
        String eventType = intent.getStringExtra("TYPE");
        String event_name = intent.getStringExtra("EVENT");
        String organizer = intent.getStringExtra("CLUB_NAME");
        String date = "01 March, 2023";
        String venue = "ONLINE";

        String description = "ORGANIZER: " + organizer + "\n\n"  + "DATE: " + date + "\n\nVENUE: " + venue ;
        description += "\n\nABOUT THE EVENT: \n asdnaksjndfkasndkasjdkajsdjkajsd\n asdnaksjndfkasndkasjdkajsdjkajasdasdassd\n asdasdasdasd.";

        // Set text in views.
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView eventName = findViewById(R.id.eventName);
        eventName.setTypeface(null, Typeface.BOLD_ITALIC);
        eventName.setText(event_name);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView descriptionText = findViewById(R.id.description);
        descriptionText.setText(description);

    }
}