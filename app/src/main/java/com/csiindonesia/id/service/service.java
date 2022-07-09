package com.csiindonesia.id.service;

import android.util.Log;

import com.csiindonesia.id.model.modelAbsen;
import com.csiindonesia.id.model.modelUnits;
import com.csiindonesia.id.model.modelUser;
import com.csiindonesia.id.response.responseAbsen;
import com.csiindonesia.id.response.responseApi;
import com.csiindonesia.id.response.responseLogin;
import com.csiindonesia.id.response.responseUnit;
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
    public void UserRegister(String name, String telp, String email, String password,String instansi, String unit){
        Call<responseApi> call = InitRetrofit.getInstance().getApi().UserSignup(name,telp,email,password,instansi,unit);
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
    public void  GetIsntasni(){
        ApiServices api = InitRetrofit.getInstance().getApi();
        Call<responseLogin> menuCall = api.GetInstansi("1","100");
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
//                            users.Berhasil(message, message);
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
    public void  GetUnits(String Guid){
        ApiServices api = InitRetrofit.getInstance().getApi();
        Call<responseUnit> menuCall = api.GetUnit(Guid);
        menuCall.enqueue(new Callback<responseUnit>() {
            @Override
            public void onResponse(Call<responseUnit> call, Response<responseUnit> response) {
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    List<modelUnits> data = response.body().getData();
                    try {
                        boolean status = response.body().getStatus();
                        String message = response.body().getMessage();
                        if (status) {
                            try {
                                users.Unit(data);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {
                            try {
                                users.Gagal(message);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    } catch (Exception e) {

                    }
                }
            }
            @Override
            public void onFailure(Call<responseUnit> call, Throwable t) {
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
    public void Absent(String user, String instansi, String unit, String nama, String gambar, String lat, String longs, String alamat, String deskripsi) {
        Call<responseApi> call = InitRetrofit.getInstance().getApi().UserAbsen(user,instansi,unit,nama,gambar,lat,longs,alamat,deskripsi);
        call.enqueue(new Callback<responseApi>() {
            @Override
            public void onResponse(Call<responseApi> call, Response<responseApi> response) {
                if (response.isSuccessful()){
                    boolean status = response.body().getStatus();
                    String message=response.body().getMessage();
                    if (status){
                        Log.d("response api", message.toString());
                        users.succes(message);
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

    public void GetAbsent(String Guid){
        ApiServices api = InitRetrofit.getInstance().getApi();
        Call<responseAbsen> menuCall = api.GetUAbsen(Guid,"1","10");
        menuCall.enqueue(new Callback<responseAbsen>() {
            @Override
            public void onResponse(Call<responseAbsen> call, Response<responseAbsen> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<modelAbsen> data= response.body().getData();
                    boolean status = response.body().getStatus();
                    String message=response.body().getMessage();
                    if (status){
                        try {
                            users.Succesgetdata(data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        try {
                            users.Gagal(message);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<responseAbsen> call, Throwable t) {
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
