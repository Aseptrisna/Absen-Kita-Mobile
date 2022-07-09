package com.csiindonesia.id.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.csiindonesia.id.R;
import com.csiindonesia.id.adapter.adapterAbsen;
import com.csiindonesia.id.localstorage.SharedPrefManager;
import com.csiindonesia.id.model.modelAbsen;
import com.csiindonesia.id.model.modelUnits;
import com.csiindonesia.id.model.modelUser;
import com.csiindonesia.id.service.service;
import com.csiindonesia.id.utils.user;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class listabsenView extends AppCompatActivity implements user {

    ViewFlipper v_flipper;
    GridLayoutManager llm;
    @BindView(R.id.List_absent)
    RecyclerView List;
    @BindView(R.id.FormList)
    LinearLayout FormList;
    Animation uptodown, downtoup,Fadein,FadeOut;
    ProgressDialog Loading;
    service services;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listabsen_view);
        ButterKnife.bind(this);
        List.setHasFixedSize(true);
        llm=new GridLayoutManager(this,1);
        List.setLayoutManager(llm);
        Fadein= AnimationUtils.loadAnimation(this, R.anim.fade_in);
        FormList.setAnimation(Fadein);
        Loading=new ProgressDialog(this);
        sharedPrefManager=new SharedPrefManager(this);
        services=new service(this);
        getdata();
    }

    private void getdata() {
        Loading.setMessage("..Loading...");
        Loading.setCancelable(true);
        Loading.show();
        services.GetAbsent(sharedPrefManager.getSP_Guid());
//        Toast.makeText(this, sharedPrefManager.getSP_Guid(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void Berhasil(String message, String Message) {

    }

    @Override
    public void Gagal(String Message) {
        Loading.dismiss();
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void No_Internet(String Message) {
        Loading.dismiss();
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void Berhasil(java.util.List<modelUser> datauser) {

    }

    @Override
    public void Unit(java.util.List<modelUnits> data) {

    }

    @Override
    public void succes(String message) {

    }

    @Override
    public void Succesgetdata(java.util.List<modelAbsen> data) {
        Loading.dismiss();
        adapterAbsen adapter = new adapterAbsen(this,data);
        List.setAdapter(adapter);

    }
    public void onBackPressed() {
        super.onBackPressed();
        goto_dashboar();
    }

    private void goto_dashboar() {
        Intent intent=new Intent(listabsenView.this,dashboardView.class);
        startActivity(intent);
        finish();
    }
}