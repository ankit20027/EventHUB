package com.example.eventhub;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class Calendar_ListView extends AppCompatActivity {
    public ArrayList<String> Events_Name = new ArrayList<String>();
    public ArrayList<String> temporary = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_list_view);


        RelativeLayout relativeLayout = findViewById(R.id.ListId);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3500);
        animationDrawable.setExitFadeDuration(5500);
        animationDrawable.start();
        ListView listview = findViewById(R.id.listview);
        Fetch_EventNames();

        Log.d("FETCH_ON_CREATE", Events_Name.toString());
        Log.d("FETCH_ON_CREATE", temporary.toString());
        Log.d("LISTVIEW", Events_Name.toString());

        ArrayAdapter arrayAdapter = new ArrayAdapter(Calendar_ListView.this, R.layout.listview_custom_itemview, temporary);

        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Calendar_ListView.this);
                builder.setMessage("Do you want to delete this reminder?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Perform delete action here
                                Utility utility = new Utility();
                                utility.deleteEventByTitle(Calendar_ListView.this, Events_Name.get(position));
                                Log.d("FETCH", Events_Name.toString());
                                Log.d("FETCH", temporary.toString());
                                arrayAdapter.remove(arrayAdapter.getItem(position));
//                                Events_Name.remove(position);
//                                temporary.remove(position);
//                                arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    public void Fetch_EventNames(){
        Utility utility = new Utility();
        utility.readCalendarEvent(Calendar_ListView.this);
        Events_Name.clear();
        temporary.clear();
        ArrayList<String> titles = utility.nameOfEvent;

        ArrayList<String> myEvents = new ArrayList<String>();
        for(String str : titles){
            if(str != null && str.length() > 3 && str.substring(str.length() - 3).equals("_eh")){
                myEvents.add(str);
            }
        }
        Events_Name = myEvents;
        for(String str : Events_Name){
            temporary.add(str.substring(0, str.length()-3));
        }
//        Log.d("REMINDER_MY_EVENTS", myEvents.toString());
    }
}
