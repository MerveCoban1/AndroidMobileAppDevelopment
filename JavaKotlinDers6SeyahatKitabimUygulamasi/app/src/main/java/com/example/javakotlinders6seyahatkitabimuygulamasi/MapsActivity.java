package com.example.javakotlinders6seyahatkitabimuygulamasi;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    static SQLiteDatabase database; //farklı yerlerden ulaşabilmek için static oluşturduk.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent intent = getIntent();
        String info = intent.getStringExtra("info");
        if (info.matches("new")) {
            //bunu yazdığımız sürece onMapLongClickListener metodu düzgün çalışacaktır.
            mMap.setOnMapLongClickListener(this);
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    SharedPreferences sharedPreferences = MapsActivity.this.getSharedPreferences("com.example.javakotlinders6seyahatkitabimuygulamasi", MODE_PRIVATE);
                    boolean firstTimeCheck = sharedPreferences.getBoolean("notFirstTime", false);
                    if (!firstTimeCheck) {
                        //kullanıcı bu api ilk kez açıyosa bu işlemler olsun sonrasında olmasın.
                        //yani konumumuzu sürekli alıyoruz 0,0 dediğimiz için ama değişiklikleri göstermek istemiyoruz.
                        LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));
                        System.out.println(location);
                        sharedPreferences.edit().putBoolean("notFirstTime", true).apply();
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };
            if (Build.VERSION.SDK_INT >= 23) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    //EĞER İZİN YOKSA,izin istiyoruz.
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                } else {
                    //kullanıcının konumunu almaya başla,minTime milisaniye istiyo.minDistance metre olarak veriliyor.Geneldee 50metre veriliyormuş.
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                    mMap.clear();
                    Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (lastLocation != null) {
                        LatLng lastUserLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 15));
                    }
                }
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (lastLocation != null) {
                    LatLng lastUserLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 15));
                }
            }
        } else {
            //eski yeri gösterecek.
            mMap.clear();
            int position = intent.getIntExtra("position", 0);
            LatLng location = new LatLng(MainActivity.locations.get(position).latitude, MainActivity.locations.get(position).longitude);
            String placeName = MainActivity.names.get(position);

            mMap.addMarker(new MarkerOptions().title(placeName).position(location));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0) {
            if (requestCode == 1) {
                //contextCompat build versiondakiyle aynı . Ama bu sefer de bunu kullanmak istedik.
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                    Intent intent = getIntent();
                    String info = intent.getStringExtra("info");
                    if (info.matches("new")) {
                        Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (lastLocation != null) {
                            LatLng lastUserLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 15));
                        }
                    } else {
                        mMap.clear();
                        int position = intent.getIntExtra("position", 0);
                        LatLng location = new LatLng(MainActivity.locations.get(position).latitude, MainActivity.locations.get(position).longitude);
                        String placeName = MainActivity.names.get(position);

                        mMap.addMarker(new MarkerOptions().title(placeName).position(location));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        //kullanıcı uzun süre tıklayınca olacak şeyler.Sadece bunun olması için onMapReady altında
        // mapi buna eşlemeyi unutmamalıyız.
        //geocoder:adresleri ve bizim verdiğimiz enlem boylamları eşleştiriyor.
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        String adress = "";
        try {
            List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addressList != null && addressList.size() > 0) {
                if (addressList.get(0).getThoroughfare() != null) {
                    adress += addressList.get(0).getThoroughfare();

                    //subthoroughfare cadde numarası gibi bişey.
                    if (addressList.get(0).getSubThoroughfare() != null) {
                        adress += addressList.get(0).getSubThoroughfare();
                    }
                }
            } else {
                adress = "No Address";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMap.addMarker(new MarkerOptions().title(adress).position(latLng));
        //listemizde veritabanına kaydettiğimiz verinin hemen güncellenmesi görünmesi için
        MainActivity.names.add(adress);
        MainActivity.locations.add(latLng);
        MainActivity.arrayAdapter.notifyDataSetChanged();
        //şimdi markerlarını  da gösterdik artık veritabanına adreslerimizi kaydedebiliriz.
        veritababinaKaydet(latLng, adress);
    }

    public void veritababinaKaydet(LatLng latLng, String adress) {
        try {
            Double l1 = latLng.latitude;
            Double l2 = latLng.longitude;

            String coord1 = l1.toString();
            String coord2 = l2.toString();

            database = this.openOrCreateDatabase("Places", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS places(name VARCHAR,latitude VARCHAR,longitude VARCHAR)");

            String toCompile = "INSERT INTO places(name,latitude,longitude) VALUES(?,?,?)";
            SQLiteStatement statement = database.compileStatement(toCompile);
            statement.bindString(1, adress);
            statement.bindString(2, coord1);
            statement.bindString(3, coord2);

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
