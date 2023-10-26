package com.example.kilimo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class GreenFedhaFragment extends Fragment {

    private Spinner Insure_spin;
    private EditText first_Name, Sur_name, last_Name, farmer_No, tel_No, Mail, Amount, id_no;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://k-tea-b79c7-default-rtdb.firebaseio.com/");



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_green_fedha, container, false);

        // Initialize the UI elements
        Insure_spin = view.findViewById(R.id.insure_spin);
        TextView textView = view.findViewById(R.id.textView);
        first_Name = view.findViewById(R.id.first_Name);
        Sur_name = view.findViewById(R.id.sur_name);
        last_Name = view.findViewById(R.id.lastName);
        farmer_No = view.findViewById(R.id.inputFarmerNo);
        tel_No = view.findViewById(R.id.telephone_no);
        Mail = view.findViewById(R.id.email);
        id_no = view.findViewById(R.id.idno);
        Amount = view.findViewById(R.id.amount);
        Button button = view.findViewById(R.id.btnsubmit);

        // Initialize the Firebase Realtime Database reference
        databaseReference = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://k-tea-b79c7-default-rtdb.firebaseio.com/");

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.loan, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        Insure_spin.setAdapter(adapter);
        button.setOnClickListener(v -> {
            // get data from editTexts to String Variables.

            String FirstName = first_Name.getText().toString();
            String surname = Sur_name.getText().toString();
            String LastName = last_Name.getText().toString();
            String FarmerNo = farmer_No.getText().toString();
            String TelephoneNO = tel_No.getText().toString();
            String email = Mail.getText().toString();
            String ID = id_no.getText().toString();
            String Spinner = Insure_spin.getSelectedItem().toString();
            String amount = Amount.getText().toString();

            // check if all fields contain data before submission

            if (FirstName.isEmpty() || surname.isEmpty() || LastName.isEmpty() || FarmerNo.isEmpty() || TelephoneNO.isEmpty() ||
                    email.isEmpty() || ID.isEmpty() || Spinner.isEmpty()||amount.isEmpty()) {
                Toast.makeText(getContext(), "Please fill out all fields"
                        , Toast.LENGTH_SHORT).show();

            } else {

                databaseReference.child("GreenFedha Loans").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // check if user already exists before registration.
                        if (snapshot.hasChild(FarmerNo)) {
                            Toast.makeText(getContext(), "Farmer has an existing loan"
                                    , Toast.LENGTH_SHORT).show();
                        } else {
                            // Sending data to firebase Database.
                            // the Farmer Number is the unique key for every user.
                            databaseReference.child("GreenFedha Loans").child(FarmerNo).child("FIRST NAME").setValue(FirstName);
                            databaseReference.child("GreenFedha Loans").child(FarmerNo).child("SURNAME").setValue(surname);
                            databaseReference.child("GreenFedha Loans").child(FarmerNo).child("LAST NAME").setValue(LastName);
                            databaseReference.child("GreenFedha Loans").child(FarmerNo).child("FARMER NO").setValue(FarmerNo);
                            databaseReference.child("GreenFedha Loans").child(FarmerNo).child("TELEPHONE NO").setValue(TelephoneNO);
                            databaseReference.child("GreenFedha Loans").child(FarmerNo).child("EMAIL").setValue(email);
                            databaseReference.child("GreenFedha Loans").child(FarmerNo).child("ID NUMBER").setValue(ID);
                            databaseReference.child("GreenFedha Loans").child(FarmerNo).child("TYPE OF LOAN").setValue(Spinner);
                            databaseReference.child("GreenFedha Loans").child(FarmerNo).child("AMOUNT").setValue(amount);

                            Toast.makeText(getContext(), "Successful"
                                    , Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }


        });
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.loan, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Insure_spin.setAdapter(adapter1);
        Insure_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                // Do something with the selected item
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }}
