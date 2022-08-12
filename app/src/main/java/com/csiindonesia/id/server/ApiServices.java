package com.csiindonesia.id.server;
import com.csiindonesia.id.response.responseAbsen;
import com.csiindonesia.id.response.responseApi;
import com.csiindonesia.id.response.responseFile;
import com.csiindonesia.id.response.responseLogin;
import com.csiindonesia.id.response.responseUnit;

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
            @Field("password") String password,
           @Field("instansi") String instansi,
           @Field("unit") String unit
    );

    @FormUrlEncoded
    @POST("users/signin")
    Call<responseLogin> UserLogin(
            @Field("email") String email,
            @Field("password") String password
    );
    @Multipart
    @POST("img/upload")
    Call<responseFile> uploadFile(@Part MultipartBody.Part file,
                                  @Part("file") RequestBody name);
    @FormUrlEncoded
    @POST("users/instansi")
    Call<responseLogin> GetInstansi(
            @Field("page") String page,
            @Field("limit") String limit
    );
    @FormUrlEncoded
    @POST("aplications/getbyguid")
    Call<responseUnit> GetUnit(
            @Field("guid") String guid

    );

    @FormUrlEncoded
    @POST("absent/add")
    Call<responseApi> UserAbsen(
            @Field("user") String user,
            @Field("instansi") String instansi,
            @Field("unit") String unit,
            @Field("nama") String nama,
            @Field("gambar") String gambar,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("alamat") String alamat,
            @Field("deskripsi") String deskripsi,
            @Field("jenis") String jenis
    );
    @FormUrlEncoded
    @POST("absent/getbyuser")
    Call<responseAbsen> GetUAbsen(
            @Field("guid") String guid,
            @Field("page") String page,
            @Field("limit") String limit
    );

}
