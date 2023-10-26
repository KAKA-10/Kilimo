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


public class NewsFragment extends Fragment {

    private RecyclerView recyclerview;
    private ArrayList<News> newsArrayList;
    private String[] newsHeading;
    private int[] imageResourceID;
    private String[] newsDescription;

    String newsPageURL = "https://ktdateas.com/news-and-events/";

    public NewsFragment() {
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
        return inflater.inflate(R.layout.news_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        recyclerview = view.findViewById(R.id.recyclerView);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        NewsAdapter myAdapter = new NewsAdapter(getContext(),newsArrayList,newsPageURL);
        recyclerview.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }

    @SuppressLint("ResourceType")
    private void dataInitialize() {

        newsArrayList = new ArrayList<>();

        newsDescription = new String [] {
                getString(R.string.desc_1),
                getString(R.string.desc_2),
                getString(R.string.desc_3),
                getString(R.string.desc_4),
                getString(R.string.desc_5),
                getString(R.string.desc_6),
                getString(R.string.desc_7),
                getString(R.string.desc_8),
                getString(R.string.desc_9),


        };

        imageResourceID = new int[]{
                R.drawable.download,
                R.drawable.tea_cupr,
                R.drawable.ktda,
                R.drawable.download,
                R.drawable.eucalyptus,
                R.drawable.eucalyptus,
                R.drawable.ktda,
                R.drawable.download,
                R.drawable.apps

        };

        newsHeading = new String[] {
                getString(R.string.news_1),
                getString(R.string.news_2),
                getString(R.string.news_3),
                getString(R.string.news_4),
                getString(R.string.news_5),
                getString(R.string.news_6),
                getString(R.string.news_7),
                getString(R.string.news_8),
                getString(R.string.news_9),


        };

        for (int i=0; i< newsHeading.length;i++) {

            News news = new News(newsHeading[i],newsDescription[i], imageResourceID[i]);
            newsArrayList.add(news);
        }
    }


}
