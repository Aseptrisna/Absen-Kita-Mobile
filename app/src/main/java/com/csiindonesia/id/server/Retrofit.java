package com.csiindonesia.id.server;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {
    public static final String BASE_URL ="https://prakerin-online.pptik.id:5102/";
    private static Retrofit mInstance;
    private retrofit2.Retrofit retrofit;
    private Retrofit(){
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized Retrofit getInstance(){
        if (mInstance == null ){
            mInstance = new Retrofit();
        }
        return mInstance;
    }
    public ApiServices getApi(){
        return retrofit.create(ApiServices.class);
    }
    public static retrofit2.Retrofit getRetrofit() {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
