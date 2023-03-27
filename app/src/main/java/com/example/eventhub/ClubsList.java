package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ClubsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs_list);

        // Placing fragment.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new NonTechClubsFragmentList());
        TextView textView = findViewById(R.id.clubListHeading);
        textView.setText("Non-Technical Clubs");
        fragmentTransaction.commit();
    }

    public void nonTechButton(View view){
        NonTechClubsFragmentList nonTechClubsFragmentList = new NonTechClubsFragmentList();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, nonTechClubsFragmentList);
        TextView textView = findViewById(R.id.clubListHeading);
        textView.setText("Non-Technical Clubs");
        fragmentTransaction.commit();
    }

    public void techButton(View view){
        TechClubsListFragment techClubsListFragment = new TechClubsListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,techClubsListFragment);
        TextView textView = findViewById(R.id.clubListHeading);
        textView.setText("Technical Clubs");
        fragmentTransaction.commit();
    }
}