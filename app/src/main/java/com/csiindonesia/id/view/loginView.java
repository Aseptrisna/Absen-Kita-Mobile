package com.csiindonesia.id.view;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.csiindonesia.id.R;
import com.csiindonesia.id.localstorage.SharedPrefManager;
import com.csiindonesia.id.model.modelAbsen;
import com.csiindonesia.id.model.modelUnits;
import com.csiindonesia.id.model.modelUser;
import com.csiindonesia.id.service.service;
import com.csiindonesia.id.utils.user;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class loginView extends AppCompatActivity implements user{
    @BindView(R.id.LoginEmail)
    TextInputEditText LoginEmail;
    @BindView(R.id.LoginPassword)
    TextInputEditText LoginPassword;
    @BindView(R.id.LoginForm)
    LinearLayout FormLogin;
    SharedPrefManager sharedPrefManager;
    ProgressDialog Loading;
    service services;
    Animation uptodown, downtoup,Fadein,FadeOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
        ButterKnife.bind(this);
        Loading=new ProgressDialog(this);
        services=new service(this);
        sharedPrefManager=new SharedPrefManager(this);
        Fadein = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        FormLogin.setAnimation(Fadein);
        ceksession();
    }

    private void ceksession() {
        SharedPrefManager sharedPrefManager=new SharedPrefManager(this);
        if (sharedPrefManager.getSudahLogin()){
            goto_dashboard();
        }
    }

    public void Login(View view) {
       Validasi();
    }

    private void Validasi() {
        if (LoginEmail.getText().toString().equals("") || LoginPassword.getText().toString().equals("")) {
            LoginEmail.setFocusable(false);
            LoginPassword.setFocusable(false);
            showSnackbar("Lengkapi data terlebih dahulu");
        } else {
            String usename=LoginEmail.getText().toString();
            String password= LoginPassword.getText().toString();
            RequestLogin(usename,password);
        }

    }

    private void RequestLogin(String usename, String password) {
        Loading.setMessage("..Loading...");
        Loading.setCancelable(true);
        Loading.show();
        services.UserLogin(usename,password);
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

    @Override
    public void Berhasil(String message, String Message) {
        Loading.dismiss();
        showSnackbar(Message);

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
        Log.d("User", String.valueOf(datauser));
        Loading.dismiss();
        String Guid=datauser.get(0).getGuid();
        String Email=datauser.get(0).getEmail();
        String Password=datauser.get(0).getPassword();
        String Telp= String.valueOf(datauser.get(0).getTelp());
        String Nama=datauser.get(0).getName();
        String Instansti=datauser.get(0).getInstansi();
        String Unit=datauser.get(0).getUnit();
        Log.d("User", Unit);
        Log.d("User", Instansti);
        sharedPrefManager.saveSPString(SharedPrefManager.SP_Guid, Guid);
        sharedPrefManager.saveSPString(SharedPrefManager.SP_Email, Email);
        sharedPrefManager.saveSPString(SharedPrefManager.SP_Password, Password);
        sharedPrefManager.saveSPString(SharedPrefManager.SP_Telp, Telp);
        sharedPrefManager.saveSPString(SharedPrefManager.SP_Nama, Nama);
        sharedPrefManager.saveSPString(SharedPrefManager.SP_Instansi,Instansti);
        sharedPrefManager.saveSPString(SharedPrefManager.SP_Units,Unit);
        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
        goto_dashboard();
    }

    @Override
    public void Unit(List<modelUnits> data) {

    }

    @Override
    public void succes(String message) {

    }

    @Override
    public void Succesgetdata(List<modelAbsen> data) {

    }

    public void showSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(FormLogin,""+message , Snackbar.LENGTH_INDEFINITE)
                .setAction("Ulangi", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(FormLogin, "Silahkan Ulangi", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                        LoginEmail.setFocusableInTouchMode(true);
                        LoginPassword.setFocusableInTouchMode(true);
                    }
                });
        snackbar.show();
    }
}