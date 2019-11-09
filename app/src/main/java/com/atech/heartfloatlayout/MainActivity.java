package com.atech.heartfloatlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import atech.com.heartfloat.HeartFloatLayout;

public class MainActivity extends AppCompatActivity {

    Button btn;
    HeartFloatLayout heartFloatLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = findViewById(R.id.btn);
        heartFloatLayout = findViewById(R.id.heart);
        heartFloatLayout.setFloatSpeed(new DecelerateInterpolator());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                heartFloatLayout.launchHeart();

            }
        });

    }
}
