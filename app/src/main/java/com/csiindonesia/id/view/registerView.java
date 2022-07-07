package com.csiindonesia.id.view;


import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.csiindonesia.id.R;
import com.csiindonesia.id.model.modelUser;
import com.csiindonesia.id.service.service;
import com.csiindonesia.id.utils.user;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class registerView extends AppCompatActivity implements user {
    @BindView(R.id.RegisterEmail)
    EditText Email;
    @BindView(R.id.RegisterTelp)
    EditText Telp;
    @BindView(R.id.RegisterNama)
    EditText Nama;
    @BindView(R.id.RegisterPassword)
    EditText Password;
    @BindView(R.id.RegisterForm)
    LinearLayout RegisterForm;
    ProgressDialog Loading;
    service services;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);
        ButterKnife.bind(this);
        Loading=new ProgressDialog(this);
        services=new service(this);
    }
    private void Validasi() {
        if (Email.getText().toString().equals("") || Password.getText().toString().equals("")||
        Telp.getText().toString().equals("") || Nama.getText().toString().equals("")
          ) {
            Email.setFocusable(false);
            Password.setFocusable(false);
            Telp.setFocusable(false);
            Nama.setFocusable(false);
            showSnackbar("Lengkapi data terlebih dahulu");
        } else {
            String email=Email.getText().toString();
            String password= Password.getText().toString();
            String nama=Nama.getText().toString();
            String telp= Telp.getText().toString();
            RequestRegister(nama,telp,email,password);
        }

    }

    private void RequestRegister(String nama, String telp, String email, String password) {
        Loading.setMessage("..Loading...");
        Loading.setCancelable(true);
        Loading.show();
        services.UserRegister(nama,telp,email,password);
    }

    public void Login(View view) {
        goto_login();
    }

    public void Register(View view) {
        Validasi();
    }
    public void goto_login(){
        Intent intent=new Intent(registerView.this,loginView.class);
        startActivity(intent);
        finish();
    }
    public void showSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(RegisterForm,""+message , Snackbar.LENGTH_INDEFINITE)
                .setAction("Ulangi", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(RegisterForm, "Silahkan Ulangi", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                        Telp.setFocusableInTouchMode(true);
                        Nama.setFocusableInTouchMode(true);
                        Email.setFocusableInTouchMode(true);
                        Password.setFocusableInTouchMode(true);
                    }
                });
        snackbar.show();
    }

    @Override
    public void Berhasil(String message, String Message) {
        Loading.dismiss();
        showSnackbar(Message);
        goto_login();
    }

    @Override
    public void Gagal(String Message) {
        Loading.dismiss();
        showSnackbar(Message);
    }

    @Override
    public void No_Internet(String Message) {
        Loading.dismiss();
        showSnackbar(Message);

    }

    @Override
    public void Berhasil(List<modelUser> datauser) {

    }
}