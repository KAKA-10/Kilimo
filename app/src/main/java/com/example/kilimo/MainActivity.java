package com.example.kilimo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation topAnimation, bottomAnimation;
    TextView appName, ktda, Slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appName = findViewById(R.id.kilimo);
        ktda = findViewById(R.id.ktda);
        Slogan = findViewById(R.id.slo);

        //Animations

        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        // hooks connect animations to variables
        appName = findViewById(R.id.kilimo);
        ktda = findViewById(R.id.ktda);
        Slogan = findViewById(R.id.slo);

        appName.setAnimation(topAnimation);
        ktda.setAnimation(bottomAnimation);
        Slogan.setAnimation(bottomAnimation);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this,Introduction.class);
            startActivity(intent);
            finish();
        },5000);




    }
}