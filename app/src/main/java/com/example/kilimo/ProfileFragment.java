package com.example.kilimo;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private TextView Name ,ID,Farm , Center , PhoneNumber,Email;
    private static final String USERS = "Users";
    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://k-tea-b79c7-default-rtdb.firebaseio.com/");

    FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Name = view.findViewById(R.id.first_name);
        ID = view.findViewById(R.id.id_number);
        Farm = view.findViewById(R.id.farm_number);
        Center= view.findViewById(R.id.center);
        PhoneNumber = view.findViewById(R.id.phone);
        Email = view.findViewById(R.id.mail);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(USERS);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren() ){
                    if(ds.child("FARMER NO").getValue().equals(Farm));
                    Name.setText(ds.child("SURNAME").getValue(String.class));
                    ID.setText(ds.child("ID NUMBER").getValue(String.class));
                    Farm.setText(ds.child("FARMER NO").getValue(String.class));
                    Center.setText(ds.child("CENTER").getValue(String.class));
                    PhoneNumber.setText(ds.child("TELEPHONE NO").getValue(String.class));
                    Email.setText(ds.child("EMAIL").getValue(String.class));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}