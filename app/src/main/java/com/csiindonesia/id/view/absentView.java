package com.csiindonesia.id.view;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.csiindonesia.id.R;
import com.csiindonesia.id.response.responseApi;
import com.csiindonesia.id.server.ApiServices;
import com.csiindonesia.id.server.InitRetrofit;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import com.csiindonesia.id.service.getCurrentLocation;
import com.csiindonesia.id.service.uploadFile;
import com.csiindonesia.id.utils.file;

public class absentView extends AppCompatActivity implements file{
    @BindView(R.id.IdProf)
    ImageView IDProf;
    @BindView(R.id.UploadBtn)
    Button Upload_Btn;
    File file;
    uploadFile uploadFiles;
    Bitmap bitmap;
    String imagePath;
    String mediaPath, mediaPath1;
    private Uri fileUri;


    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absent_view);
        IDProf=(ImageView)findViewById(R.id.IdProf);
        Upload_Btn=(Button)findViewById(R.id.UploadBtn);
        uploadFiles=new uploadFile(this);
        ButterKnife.bind(this);
        checkSelfPermission();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                IDProf.setImageBitmap(thumbnail);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                Random rand = new Random();
                double rand_dub1 = rand.nextDouble();
                File destination = new File(Environment.getExternalStorageDirectory(),rand_dub1+".jpg");
                FileOutputStream fo;
                try {
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imagePath=destination.getAbsolutePath();
            }

        }catch (Exception e){
        }

        }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void SelectImage(View view) {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        }
        else
        {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }
    private void UploadImage(){
        File photo = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), photo);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", photo.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), photo.getName());
        uploadFiles.UploadFile(fileToUpload,filename);

    }

    public void sendAbsent(View view) {
        UploadImage();
    }

    @Override
    public void Berhasil(String Message, String File) {
        Toast.makeText(this, Message+"-"+File, Toast.LENGTH_SHORT).show();
        getCurrentLocation getCurrentLocation=new getCurrentLocation();
        getCurrentLocation.getLatLong();
        Toast.makeText(this, getCurrentLocation.getLatLong(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void Gagal(String Message) {

    }

    @Override
    public void No_Internet(String Message) {

    }
   private void checkSelfPermission(){
       if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
       } else {
           ActivityCompat.requestPermissions(this,
                   new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

       }
   }

}
