package com.csiindonesia.id.service;

import android.util.Log;

import com.csiindonesia.id.model.modelUser;
import com.csiindonesia.id.response.responseApi;
import com.csiindonesia.id.response.responseLogin;
import com.csiindonesia.id.server.ApiServices;
import com.csiindonesia.id.server.InitRetrofit;
import com.csiindonesia.id.utils.user;


import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class service {
    final user users;
    public service(user users) {
        this.users = users;
    }
    public void UserRegister(String name, String telp, String email,String password){
        Call<responseApi> call = InitRetrofit.getInstance().getApi().UserSignup(name,telp,email,password);
        call.enqueue(new Callback<responseApi>() {
            @Override
            public void onResponse(Call<responseApi> call, Response<responseApi> response) {
                if (response.isSuccessful()){
                    boolean status = response.body().getStatus();
                    String message=response.body().getMessage();
                    if (status){
                        Log.d("response api", message.toString());
                        users.Berhasil(message, message);
                    } else {
                        try {
                            Log.d("response api", message.toString());
                            users.Gagal(message);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    try {
                        String error_message ="Ada Masalah Internet";
                        users.No_Internet(error_message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<responseApi> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                try {
                    String error_message ="Server Tidak Merespon";
                    users.No_Internet(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void  UserLogin(String Email,String Password){
        ApiServices api = InitRetrofit.getInstance().getApi();
        Call<responseLogin> menuCall = api.UserLogin(Email,Password);
        menuCall.enqueue(new Callback<responseLogin>() {
            @Override
            public void onResponse(Call<responseLogin> call, Response<responseLogin> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<modelUser> datauser= response.body().getUser();
                    boolean status = response.body().getStatus();
                    String message=response.body().getMessage();
                    if (status){
                        try {
                            users.Berhasil(message, message);
                            users.Berhasil(datauser);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        try {
//                            String Message="Login Gagal";
                            users.Gagal(message);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<responseLogin> call, Throwable t) {
                try {
                    String Message="Tidak Ada Internet";
                    users.No_Internet(Message);
                    t.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
