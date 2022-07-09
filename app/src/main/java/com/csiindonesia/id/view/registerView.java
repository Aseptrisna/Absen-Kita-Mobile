package com.csiindonesia.id.view;


import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.csiindonesia.id.R;
import com.csiindonesia.id.model.modelAbsen;
import com.csiindonesia.id.model.modelUnits;
import com.csiindonesia.id.model.modelUser;
import com.csiindonesia.id.service.service;
import com.csiindonesia.id.utils.user;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
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
    @BindView(R.id.RegisterInstansi)
    Spinner RegisterInstansi;
    @BindView(R.id.RegisterUnit)
    Spinner RegisterUnit;
    ProgressDialog Loading;
    service services;
    String Instansi;
    String Unit;
    Animation uptodown, downtoup,Fadein,FadeOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);
        ButterKnife.bind(this);
        Loading=new ProgressDialog(this);
        services=new service(this);
        Fadein = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        RegisterForm.setAnimation(Fadein);
        services.GetIsntasni();
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
            String instansi=Instansi;
            String unit=Unit;
            RequestRegister(nama,telp,email,password,instansi,unit);
        }

    }

    private void RequestRegister(String nama, String telp, String email, String password,String instansi,String unit) {
        Loading.setMessage("..Loading...");
        Loading.setCancelable(true);
        Loading.show();
        services.UserRegister(nama,telp,email,password,instansi,unit);
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
        List<String> intansi = new ArrayList<>();
        intansi.add(0, getString(R.string.txt_please_slct_instansi));
        for (int i = 0; i < datauser.size(); i++)
            intansi.add(datauser.get(i).getName());
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(registerView.this,
                android.R.layout.simple_spinner_item, intansi);
        RegisterInstansi.setAdapter(adapter);
        RegisterInstansi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!RegisterInstansi.getSelectedItem().toString().equals(getString(R.string.txt_please_slct_instansi))) {
                    Instansi = datauser.get(position - 1).getGuid();
                    services.GetUnits(Instansi);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void Unit(List<modelUnits> data) {
        List<String> units = new ArrayList<>();
        units.add(0, getString(R.string.txt_please_slct_unit));
        for (int i = 0; i < data.size(); i++)
            units.add(data.get(i).getName());
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(registerView.this,
                android.R.layout.simple_spinner_item, units);
        RegisterUnit.setAdapter(adapter);
        RegisterUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try{
                    if (!RegisterUnit.getSelectedItem().toString().equals(getString(R.string.txt_please_slct_unit))) {
                        Unit = data.get(position - 1).getGuid();
//                    services.GetUnits(Instansi);
                    }
                }catch (Exception e){

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @Override
    public void succes(String message) {

    }

    @Override
    public void Succesgetdata(List<modelAbsen> data) {

    }

    public void onBackPressed() {
        super.onBackPressed();
        goto_login();
    }
}