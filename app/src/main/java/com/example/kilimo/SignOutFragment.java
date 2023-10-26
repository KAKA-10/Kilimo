package com.example.kilimo;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignOutFragment extends Fragment {

    Button button, cancel;
    FirebaseAuth mAuth;

    public SignOutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_out, container, false);

        mAuth = FirebaseAuth.getInstance();

        Button button = view.findViewById(R.id.button);
        Button cancel = view.findViewById(R.id.btncancel);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOutUser();
            }

        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Services.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void signOutUser(){
        mAuth.signOut();
        Toast.makeText(getContext(), "User Signed Out", Toast.LENGTH_SHORT).show();

        // Start the login activity
        Intent intent = new Intent(getActivity(), LogIn.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        // Finish the current fragment
        getActivity().finish();
    }
}
