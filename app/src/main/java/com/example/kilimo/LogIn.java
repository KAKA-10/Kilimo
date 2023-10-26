package com.example.kilimo;
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogIn extends AppCompatActivity {

    TextView createAccount, forgotPassword;
    Button button;
    EditText FarmerNo, Password;
    final DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://k-tea-b79c7-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_log_in);

        createAccount = findViewById(R.id.createAccount);
        forgotPassword = findViewById(R.id.forgotPassword);
        FarmerNo = findViewById(R.id.inputFarmerNo);
        Password = findViewById(R.id.inputPassword);
        button = findViewById(R.id.btnLogin);

        createAccount.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SignUp.class);
            startActivity(intent);
            finish();
        });

        button.setOnClickListener(v -> {
            String Username = FarmerNo.getText().toString();
            String password = Password.getText().toString();

            if (Username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LogIn.this, "Please enter your Farmer Number and Password"
                        , Toast.LENGTH_SHORT).show();
            } else {
                databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(Username)) {
                            String getPassword = snapshot.child(Username).child("PASSWORD").getValue(String.class);

                            if (getPassword != null && getPassword.equals(password)) {
                                Toast.makeText(LogIn.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LogIn.this, Services.class));
                                finish();
                            } else {
                                Toast.makeText(LogIn.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LogIn.this, "Check Username and Password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        //failed to read from database
                        Log.w(TAG, "Failed to read from Database.",error.toException());
                    }
                });
            }
        });
    }
}
