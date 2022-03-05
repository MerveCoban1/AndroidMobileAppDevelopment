package com.example.javakotlinders5haritalar;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
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

/*
* google_maps_api.xml dosyasındaki linke girdik internetten.Proje oluşturduk.API oluşturduk
 api key'i kopyaladık.google_maps_api.xml de your api key yazan yere yapıştırdık.
*androidin yeni güncelemelerine göre manifestte application taginin içine
 <uses-library android:name="org.apache.http.legacy" android:required="false"></uses-library>
ekliyoruz. Haritaların android pie ve sonrasında çalışabilmesi için.
 *
*
*
*
* */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //onMapReady harita hazır olduğunda yapılacak işlemler demektir.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //kullanıcının konumunu bulacağız.
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                /*mMap.clear();//harita üzerinde değişiklik yaptığımızda eskisini siliyor.
                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(userLocation).title("your location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));
            */
                //burası sürekli güncelleniyor.
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
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                LatLng userLastLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                mMap.addMarker(new MarkerOptions().title("Your Location").position(userLastLocation));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLastLocation, 15));

            }
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            LatLng userLastLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().title("Your Location").position(userLastLocation));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLastLocation, 15));

        }
        mMap.setOnMapLongClickListener(this);  //setOnMapLongClickListener metodu ile bağladık burayı
        // kullaniciIzni();

        //bu kısım proje ilk açıldığında karşımıza çıkan kısım. Biz kullanıcının eyrini göstereceğiz.
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney)); normalde bu var ama zoomlu istiyoruz
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,10));*/
    }

    public void kullaniciIzni() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //EĞER İZİN VERİLMEDİYSE
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            //EGER İZİN VARSA
            //0,0 ı 500 , 50 olarak değiştirebilirsin yoksa pili çok yiyebilir sürekli konuma bakıyo gibi düşün
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //kullanıcı izinleri verdiği zaman yapılacaklar.İlk kez izni verdiğinde
        if (grantResults.length > 0) {
            if (requestCode == 1) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //haritada bir yere uzunca tıkladığımızda
    @Override
    public void onMapLongClick(LatLng latLng) {
        mMap.clear();
        //sokak adresini,açık adresi falan almak için kullanıyoruz.verilen enlem boylama göre açık adres dönüyo.
        //getDefault kullanıcının diline göre ayarlamasını yapıyor.
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        String address = "";
        try {
            List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addressList != null && addressList.size() > 0) {
                if (addressList.get(0).getThoroughfare() != null) {
                    address += addressList.get(0).getThoroughfare();
                    if (addressList.get(0).getSubThoroughfare() != null) {
                        address += addressList.get(0).getSubThoroughfare();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (address.matches("")) {
            address = "No Address";
        }
        //şimdi işlemlerimiz bitti. Kullaanıcıya Bi marker ile gösterebiliriz.
        mMap.addMarker(new MarkerOptions().position(latLng).title(address));

    }
}
