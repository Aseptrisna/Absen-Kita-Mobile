package com.csiindonesia.id.server;
import com.csiindonesia.id.response.responseApi;
import com.csiindonesia.id.response.responseFile;
import com.csiindonesia.id.response.responseLogin;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiServices {
    @FormUrlEncoded
    @POST("users/signup")
    Call<responseApi> UserSignup(
            @Field("name") String name,
            @Field("telp") String telp,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("users/signin")
    Call<responseLogin> UserLogin(
            @Field("email") String email,
            @Field("password") String password
    );
    @Multipart
    @POST("files/upload")
    Call<responseFile> uploadFile(@Part MultipartBody.Part file,
                                  @Part("file") RequestBody name);


}
