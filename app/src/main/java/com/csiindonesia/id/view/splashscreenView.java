package com.csiindonesia.id.view;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.csiindonesia.id.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class splashscreenView extends AppCompatActivity {
    @BindView(R.id.logo)
    ImageView icon;
    Animation uptodown, downtoup,Fadein,FadeOut;
//    @BindView(R.id.splashScreen)
//    LinearLayout SplashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen_view);
        ButterKnife.bind(this);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        Fadein = AnimationUtils.loadAnimation(this, R.anim.to_left);
        FadeOut= AnimationUtils.loadAnimation(this, R.anim.to_right);
        icon.setAnimation(uptodown);
//        SplashScreen.setAnimation(Fadein);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goto_login();
            }
        }, 5000);
    }

    private void goto_login() {
        Intent intent=new Intent(splashscreenView.this,loginView.class);
        startActivity(intent);
        finish();
    }
}