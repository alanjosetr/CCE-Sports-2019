package org.codecse.ccesports2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.Random;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        final Intent intent = new Intent(SplashScreenActivity.this,
                MainActivity.class);
        Random random = new Random();
        int randomInteger = random.nextInt(10);
        View decorView=getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        long duration;
        if(randomInteger>3)
            duration=1000;
        else if(randomInteger>7)
            duration=3000;
        else
            duration=5000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        },duration);
    }
}



