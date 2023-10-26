package com.example.kilimo;


import android.annotation.SuppressLint;
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


public class TendersFragment extends Fragment {

    private RecyclerView recyclerview;
    private ArrayList<Tenders> tenderArrayList;
    private String[] tenderName;
    private int[] imageResourceID;
    private String[] tenderDescription;

    String tenderPageUrl = "https://ktdateas.com/tenders/";

    public TendersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tenders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        recyclerview = view.findViewById(R.id.recyclerView);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        TendersAdapter myAdapter = new TendersAdapter(getContext(),tenderArrayList, tenderPageUrl);
        recyclerview.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }

    @SuppressLint("ResourceType")
    private void dataInitialize() {

        tenderArrayList = new ArrayList<>();

        tenderDescription = new String [] {
                getString(R.string.desc_a),
                getString(R.string.desc_b),
                getString(R.string.desc_c)


        };

        imageResourceID = new int[]{
                R.drawable.eucalyptus,
                R.drawable.ktda,
                R.drawable.ktda

        };

        tenderName = new String[] {
                getString(R.string.tender_a),
                getString(R.string.tender_b),
                getString(R.string.tender_c)


        };

        for (int i=0; i< tenderName.length;i++) {

            Tenders tenders = new Tenders(tenderName[i],tenderDescription[i], imageResourceID[i]);
            tenderArrayList.add(tenders);
        }
    }


}