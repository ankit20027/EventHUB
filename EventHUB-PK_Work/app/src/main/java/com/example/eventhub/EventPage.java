package com.example.eventhub;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;
import android.Manifest;

import java.util.Calendar;

public class EventPage extends AppCompatActivity {

    private static final int REQUEST_CODE_SET_ALARM_PERMISSION = 1;
    RadioGroup radioGroup;
    RadioButton Before10, Before20, Custom;
    Button button;
    int time = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
        Button Reminder_Notification = findViewById(R.id.Reminder_Notification);
        Button button = findViewById(R.id.button);
//        radioGroup = findViewById(R.id.radioGroup);//        radioGroup = findViewById(R.layout.popup_reminder);
//        Before10 = findViewById(R.id.Before10);
//        Before20 = findViewById(R.id.Before20);
//        Custom = findViewById(R.id.Custom);
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


        Reminder_Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(EventPage.this, Manifest.permission.SET_ALARM)
                        == PackageManager.PERMISSION_DENIED) {
                    // Permission is not granted, request it
                    ActivityCompat.requestPermissions(EventPage.this, new String[]{Manifest.permission.SET_ALARM},
                            REQUEST_CODE_SET_ALARM_PERMISSION);
                    Log.d("REMINDER_SET_00", "ASKING FOR PERMISSION");
                } else {
                    // Permission already granted, proceed with setting the reminder
                    showPopupWindow(Reminder_Notification);
//                    setReminder(1, "REMINDER CHECK");
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCalendarEvent(v);
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_SET_ALARM_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with setting the reminder
            } else {
                // Permission denied, show a message or disable the feature that requires this permission
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void AddCalendarEvent(View view){
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2023, 5, 15, 10, 0);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2023, 5, 15, 11, 0);

        String title = "My Event";
        String description = "This is my event description";
        String location = "IIIT Delhi, Okhla Industrial Estate, Phase III, near Govind Puri Metro Station, New Delhi, Delhi 110020";

        Intent intent = new Intent(Intent.ACTION_EDIT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.DESCRIPTION, description)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, location);

        startActivity(intent);
    }


    public void showPopupWindow(View view) {
        // Inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_reminder, null);
        Calendar currentTime = Calendar.getInstance();

        //Get Event Timings here....To be changed
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        radioGroup = popupView.findViewById(R.id.radioGroup);
        Before10 = popupView.findViewById(R.id.Before10);
        Before20 = popupView.findViewById(R.id.Before20);
        Custom = popupView.findViewById(R.id.Custom);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == Before10.getId()){
//                    setReminder(10, "Event-10"); //changes to be made ....Getting Event time, Event name
                    time = 10;
                }
                else if(checkedId == Before20.getId()){
//                    setReminder(20, "Event-20");
                    time = 20;
                }

                else if(checkedId == Custom.getId()){
                    // Show the time picker
                    TimePickerDialog timePickerDialog = new TimePickerDialog(EventPage.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
                            // Calculate the difference between the selected time and event time in minutes
                            int h = Math.abs(hour - hourOfDay);
                            int m = Math.abs(minute - minutes);
                            time = (h * 60) + m;
//                            time /= 2;
                            Log.d("REMINDER_TIME", Integer.toString(time));
                        }
                    }, hour, minute, false);
                    timePickerDialog.show();
                }
            }
        });
        // Create the popup window
        int width = 950;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // Let taps outside the popup also dismiss it
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // Show the popup window
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // Dismiss the popup window when the close button is clicked
        Button buttonClose = popupView.findViewById(R.id.buttonClose);
        buttonClose.setOnClickListener(v -> {
            popupWindow.dismiss();
        });

        // Handle setting the reminder when the "Set Reminder" button is clicked
        Button buttonSetReminder = popupView.findViewById(R.id.buttonSetReminder);

        buttonSetReminder.setOnClickListener(v -> {

            // set the reminder using the input values
            setReminder(time, "EVENT_NAME"); //Event name needs to be inserted here

//            Log.d("REMINDER_SET1", Integer.toString((int) System.currentTimeMillis()));

            // Dismiss the popup window
            popupWindow.dismiss();
        });
    }

    public void setReminder(long milliseconds, String message) {
        // Get the AlarmManager and create a PendingIntent for the alarm
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, ReminderReceiver.class);
        intent.putExtra("message", message);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Set the alarm to go off at the specified time
        long timeInMillis = System.currentTimeMillis() + (milliseconds * 60 * 1000);
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);

        Toast.makeText(this, "Reminder set for " + milliseconds + " minutes from now", Toast.LENGTH_SHORT).show();
    }
}