package com.example.kilimo;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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

public class SignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView haveAccount;
    EditText firstName, Surname, lastName, FarmerNumber, TelephoneNumber,Email , IDNumber, Password;
    Spinner Spin;
    Button button;
    final DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://k-tea-b79c7-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_sign_up);

        haveAccount = findViewById(R.id.haveAccount);
        firstName = findViewById(R.id.firstName);
        Surname = findViewById(R.id.surname);
        lastName = findViewById(R.id.lastName);
        FarmerNumber = findViewById(R.id.inputFarmerNo);
        TelephoneNumber = findViewById(R.id.telephone_no);
        Email = findViewById(R.id.email);
        IDNumber = findViewById(R.id.idno);
        Password = findViewById(R.id.inputPassword2);
        Spin = findViewById(R.id.spin);
        button = findViewById(R.id.btnRegister);


        haveAccount.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),LogIn.class);

            startActivity(intent);
            finish();
        });

        button.setOnClickListener(v -> {

            // get data from editTexts to String Variables.

            String FirstName = firstName.getText().toString();
            String surname = Surname.getText().toString();
            String LastName = lastName.getText().toString();
            String FarmerNo = FarmerNumber.getText().toString();
            String TelephoneNO = TelephoneNumber.getText().toString();
            String email = Email.getText().toString();
            String ID = IDNumber.getText().toString();
            String Spinner = Spin.getSelectedItem().toString();
            String password = Password.getText().toString();

            // check if all fields contain data before submission

            if(FirstName.isEmpty()||surname.isEmpty()||LastName.isEmpty()||FarmerNo.isEmpty()||TelephoneNO.isEmpty()||
                    email.isEmpty() ||ID.isEmpty()||Spinner.isEmpty()||password.isEmpty()){
                Toast.makeText(SignUp.this,"Please fill out all fields"
                        ,Toast.LENGTH_SHORT).show();

            } else {

                databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // check if user already exists before registration.
                        if (snapshot.hasChild(FarmerNo)){
                            Toast.makeText(SignUp.this,"User exists"
                                    ,Toast.LENGTH_SHORT).show();
                        } else {
                            // Sending data to firebase Database.
                            // the Farmer Number is the unique key for every user.
                            databaseReference.child("Users").child(FarmerNo).child("FIRST NAME").setValue(FirstName);
                            databaseReference.child("Users").child(FarmerNo).child("SURNAME").setValue(surname);
                            databaseReference.child("Users").child(FarmerNo).child("LAST NAME").setValue(LastName);
                            databaseReference.child("Users").child(FarmerNo).child("FARMER NO").setValue(FarmerNo);
                            databaseReference.child("Users").child(FarmerNo).child("TELEPHONE NO").setValue(TelephoneNO);
                            databaseReference.child("Users").child(FarmerNo).child("EMAIL").setValue(email);
                            databaseReference.child("Users").child(FarmerNo).child("ID NUMBER").setValue(ID);
                            databaseReference.child("Users").child(FarmerNo).child("CENTER").setValue(Spinner);
                            databaseReference.child("Users").child(FarmerNo).child("PASSWORD").setValue(password);

                            Toast.makeText(SignUp.this,"User registered successfully"
                                    ,Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(SignUp.this,LogIn.class);
                            startActivity(intent);
                            finish();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }


        });


        @SuppressLint("CutPasteId") Spinner spinner = findViewById(R.id.spin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Tea_Centers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}