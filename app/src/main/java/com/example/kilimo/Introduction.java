package com.example.kilimo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

public class Introduction extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_introduction);

        button = findViewById(R.id.getStarted);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),LogIn.class);
            startActivity(intent);
            finish();
        });
    }
}