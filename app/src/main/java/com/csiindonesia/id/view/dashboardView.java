package com.csiindonesia.id.view;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.csiindonesia.id.R;
import com.csiindonesia.id.localstorage.SharedPrefManager;
import com.csiindonesia.id.service.service;

import butterknife.BindView;
import butterknife.ButterKnife;

public class dashboardView extends AppCompatActivity {
    @BindView(R.id.Nama)
    TextView nama;
    SharedPrefManager sharedPrefManager;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    TextView Username,Role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_view);
        ButterKnife.bind(this);
        sharedPrefManager=new SharedPrefManager(this);
        nama.setText(sharedPrefManager.getSP_Nama());
        checklocationPermission();
    }

    public void Logout(View view) {
        dialog = new AlertDialog.Builder(this);
        String username=sharedPrefManager.getSP_Nama();
        String role=sharedPrefManager.getSP_Email();
        dialog = new AlertDialog.Builder(this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.list_profile, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.drawable.ic_profile);
        dialog.setTitle("Profile");
        Username=(TextView)dialogView.findViewById(R.id.Username);
        Role=(TextView)dialogView.findViewById(R.id.Role);
        Username.setText(username);
        Role.setText(role);
        dialog.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sharedPrefManager.saveSPBoolean(sharedPrefManager.SP_SUDAH_LOGIN,false);
                startActivity(new Intent(dashboardView.this, loginView.class));
                finish();
            }
        });

        dialog.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public void gotoMyabsent(View view) {
        goto_list();
    }

    public void gotoabsent(View view) {
        goto_absent();

    }

    public void goto_absent(){
        Intent intent=new Intent(dashboardView.this,absentView.class);
        startActivity(intent);
        finish();
    }
    public void goto_list(){
        Intent intent=new Intent(dashboardView.this,listabsenView.class);
        startActivity(intent);
        finish();
    }
    private void checklocationPermission() {
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}