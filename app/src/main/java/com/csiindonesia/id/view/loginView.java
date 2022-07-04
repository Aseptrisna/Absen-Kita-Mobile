package com.csiindonesia.id.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.csiindonesia.id.R;

public class loginView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
    }

    public void Login(View view) {
        goto_dashboard();
    }

    public void Register(View view) {
        goto_register();
    }
    public void goto_register(){
        Intent intent=new Intent(loginView.this,registerView.class);
        startActivity(intent);
        finish();
    }
    public void goto_dashboard(){
        Intent intent=new Intent(loginView.this,dashboardView.class);
        startActivity(intent);
        finish();
    }
}