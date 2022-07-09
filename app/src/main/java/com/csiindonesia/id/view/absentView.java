package com.csiindonesia.id.view;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.csiindonesia.id.R;
import com.csiindonesia.id.localstorage.SharedPrefManager;
import com.csiindonesia.id.model.modelAbsen;
import com.csiindonesia.id.model.modelUnits;
import com.csiindonesia.id.model.modelUser;
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
import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import com.csiindonesia.id.service.GPSTracker;
import com.csiindonesia.id.service.service;
import com.csiindonesia.id.service.uploadFile;
import com.csiindonesia.id.utils.file;
import com.csiindonesia.id.utils.user;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

public class absentView extends AppCompatActivity implements file, user {
    @BindView(R.id.IdProf)
    ImageView IDProf;
    @BindView(R.id.UploadBtn)
    Button Upload_Btn;
    @BindView(R.id.FormAbsen)
    LinearLayout FormAbsen;
    @BindView(R.id.Latitude)
    EditText Latitude;
    @BindView(R.id.Longitude)
    EditText Longitude;
    @BindView(R.id.Alamat)
    EditText Alamat;
    @BindView(R.id.Deskripsi)
    EditText Deskripsi;
    @BindView(R.id.ShowHide)
    LinearLayout Form;
    uploadFile uploadFiles;
    String imagePath;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    FusedLocationProviderClient mFusedLocationClient;
    Geocoder geocoder;
    List<Address> addresses;
    SharedPrefManager sharedPrefManager;
    ProgressDialog Loading;
    service services;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absent_view);
        IDProf = (ImageView) findViewById(R.id.IdProf);
        Upload_Btn = (Button) findViewById(R.id.UploadBtn);
        uploadFiles = new uploadFile(this);
        Loading=new ProgressDialog(this);
        services=new service(this);
        sharedPrefManager=new SharedPrefManager(this);
        ButterKnife.bind(this);
        checkSelfPermission();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();
        Form.setVisibility(View.GONE);


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checklocationPermission() {
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }else {
                getLastLocation();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                IDProf.setImageBitmap(thumbnail);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                Random rand = new Random();
                double rand_dub1 = rand.nextDouble();
                File destination = new File(Environment.getExternalStorageDirectory(), rand_dub1 + ".jpg");
                FileOutputStream fo;
                try {
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imagePath = destination.getAbsolutePath();
                Form.setVisibility(View.VISIBLE);
            }

        } catch (Exception e) {
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void SelectImage(View view) {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        } else {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }

    private void UploadImage() {
        String deskripsi=Deskripsi.getText().toString();
        if(deskripsi.equals("")){
            showSnackbar("Masukan Deskripsi");
        }else{
            Loading.setMessage("..Loading...");
            Loading.setCancelable(true);
            Loading.show();
            File photo = new File(imagePath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), photo);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", photo.getName(), requestBody);
            RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), photo.getName());
            uploadFiles.UploadFile(fileToUpload, filename);
        }
    }

    public void sendAbsent(View view) {
        UploadImage();
    }

    @Override
    public void Berhasil(String Message, String Filename) {
        String user=sharedPrefManager.getSP_Guid();
        String instansi=sharedPrefManager.getSP_Instansi();
        String unit=sharedPrefManager.getSP_Units();
        String nama=sharedPrefManager.getSP_Nama();
        String gambar=Filename;
        String lat=Latitude.getText().toString();
        String longs=Longitude.getText().toString();
        String alamat=Alamat.getText().toString();
        String deskripsi=Deskripsi.getText().toString();
        services.Absent(user,instansi,unit,nama,gambar,lat,longs,alamat,deskripsi);
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

    }

    @Override
    public void Unit(List<modelUnits> data) {

    }

    @Override
    public void succes(String message) {
        Loading.dismiss();
        showSnackbar(message);
        goto_dashboar();
    }

    @Override
    public void Succesgetdata(List<modelAbsen> data) {

    }

    private void goto_dashboar() {
        Intent intent=new Intent(absentView.this,dashboardView.class);
        startActivity(intent);
        finish();

    }

    private void checkSelfPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getLastLocation() {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            checklocationPermission();
                        } else {
                            Latitude.setEnabled(false);
                            Longitude.setEnabled(false);
                            Latitude.setText(Double.toString(location.getLatitude()));
                            Longitude.setText(Double.toString(location.getLongitude()));
                            try {
                                getAddress(location.getLatitude(),location.getLongitude());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
    }

    private void getAddress(double latitude, double longitude) throws IOException {
        geocoder = new Geocoder(absentView.this, Locale.getDefault());
        addresses = geocoder.getFromLocation(latitude, longitude, 1);
        String address = addresses.get(0).getAddressLine(0);
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName();
        Alamat.setEnabled(false);
        Alamat.setText(address);

    }
    public void showSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(FormAbsen,""+message , Snackbar.LENGTH_INDEFINITE)
                .setAction("Ulangi", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(FormAbsen, "Silahkan Ulangi", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                        Deskripsi.setFocusableInTouchMode(true);
//                        LoginPassword.setFocusableInTouchMode(true);
                    }
                });
        snackbar.show();
    }
    public void onBackPressed() {
        super.onBackPressed();
        goto_dashboar();
    }
}
