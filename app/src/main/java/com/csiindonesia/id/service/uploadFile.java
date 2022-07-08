package com.csiindonesia.id.service;
import android.util.Log;

import com.csiindonesia.id.response.responseApi;
import com.csiindonesia.id.response.responseFile;
import com.csiindonesia.id.server.InitRetrofit;
import com.csiindonesia.id.utils.file;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class uploadFile {
    final file files;
    public uploadFile(file files) {
        this.files = files;
    }
    public void UploadFile(MultipartBody.Part fileToUpload, RequestBody filename){
        Call<responseFile> call = InitRetrofit.getInstance().getApi().uploadFile(fileToUpload,filename);
        call.enqueue(new Callback<responseFile>() {
            @Override
            public void onResponse(Call<responseFile> call, Response<responseFile> response) {
                if (response.isSuccessful()){
                    boolean status = response.body().getStatus();
                    String message=response.body().getMessage();
                    String file=response.body().getFilename();
                    if (status){
                        Log.d("response api", message.toString());
                        files.Berhasil(message,file);
                    } else {
                        try {
                            Log.d("response api", message.toString());
                            files.Gagal(message);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    try {
                        String error_message ="Ada Masalah Internet";
                        files.No_Internet(error_message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<responseFile> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                try {
                    String error_message ="Server Tidak Merespon";
                    files.No_Internet(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
