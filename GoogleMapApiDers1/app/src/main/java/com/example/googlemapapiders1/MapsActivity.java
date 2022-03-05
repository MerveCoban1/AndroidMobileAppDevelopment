package com.example.googlemapapiders1;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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

        LatLng sydney = new LatLng(-34, 151);
        LatLng sydney2 = new LatLng(40.8415972, 35.616067); //bu şekilde birden fazla marker ekleyebiliriz.
        //marker harita üzerine imleç atıyor.
        //title imleçe tıklanınca üzerinde yazan text mesajı
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker 1"));
        mMap.addMarker(new MarkerOptions().position(sydney2).title("Markeer ikii"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        /* ŞİMDİ MAP TYPELARA BAKALIM */
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);  //mavili koyu renkli harita çıkıyor.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14.0f)); //yakınlaştırıyoruz.

    }
}
//internete istediğim yeri yazdım adreste @ten sonraki ikinci virgüle kadar olan kısmı aldım.
//40.8415972,35.616067
