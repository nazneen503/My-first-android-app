package com.poohkidslearning;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class splash extends AppCompatActivity {
TextView textv1,textv2;
RelativeLayout splash;
Animation blink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        textv1=findViewById(R.id.textv1);
        textv2=findViewById(R.id.textv2);
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#D1F7FA"));
        }
        splash=findViewById(R.id.splash);
        blink=AnimationUtils.loadAnimation(this,R.anim.blink);
        textv1.setAnimation(blink);
        textv2.setAnimation(blink);
        new Handler().postDelayed(
                new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(com.poohkidslearning.splash.this,profile.class);
                startActivity(i);
                finishAffinity();
            }
        },3000);
    }
}
