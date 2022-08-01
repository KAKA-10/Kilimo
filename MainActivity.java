package com.example.k_tea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3500;

    // variables
    Animation topAnim,bottomAnim;
    ImageView logo;
    TextView slogan, karibu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        // Hooks
        logo = findViewById(R.id.logo);
        slogan = findViewById(R.id.slogan);
        karibu = findViewById(R.id.karibu);

        logo.setAnimation(topAnim);
        slogan.setAnimation(bottomAnim);
        karibu.setAnimation(bottomAnim);

      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              Intent intent = new Intent(MainActivity.this,LogIn.class);
              startActivity(intent);
              finish();
          }
      },SPLASH_SCREEN);
    }
}