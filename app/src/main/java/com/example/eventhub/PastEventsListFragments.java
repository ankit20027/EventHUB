package com.example.eventhub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PastEventsListFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PastEventsListFragments extends Fragment implements RecyclerViewInterface {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<ClubsListModelClass> userList;
    Adapter adapter;
    View view;

    ArrayList<String> eventsList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PastEventsListFragments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PastEventsListFragments.
     */
    // TODO: Rename and change types and number of parameters
    public static PastEventsListFragments newInstance(String param1, String param2) {
        PastEventsListFragments fragment = new PastEventsListFragments();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return view = inflater.inflate(R.layout.fragment_past_events_list_fragments, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // Initializing the data for the recycler view.
        initializeData();
        initializeRecyclerView();
    }

    private void initializeData() {
        userList = new ArrayList<>();
        eventsList = new ArrayList<String>(Arrays.asList("Past Event 1", "Past Event 2", "Past Event 3" ,"Past Event 4",
                "Past Event 5", "Past Event 6", "Past Event 7", "Past Event 8", "Past Event 9", "Past Event 10", "Past Event 11", "Past Event 12"));
        for(String event : eventsList) {
            userList.add(new ClubsListModelClass(event, "---------------------"));
        }
    }

    private void initializeRecyclerView() {
        @SuppressLint("DiscouragedApi") int startView = getResources().getIdentifier("recyclerView", "id", requireContext().getPackageName());
        recyclerView = view.findViewById(startView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new Adapter(userList, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClickForListItem(int position) {
        Intent intent = new Intent(this.getActivity(), EventPage.class);
        ClubPage clubPage = (ClubPage) getActivity();
        intent.putExtra("CLUB_NAME", clubPage.getClubName());
        intent.putExtra("EVENT", eventsList.get(position));
        intent.putExtra("TYPE","past");
        startActivity(intent);
    }
}