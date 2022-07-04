package com.csiindonesia.id.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.csiindonesia.id.R;

public class registerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);
    }

    public void Login(View view) {
        goto_login();
    }

    public void Register(View view) {
    }
    public void goto_login(){
        Intent intent=new Intent(registerView.this,loginView.class);
        startActivity(intent);
        finish();
    }
}