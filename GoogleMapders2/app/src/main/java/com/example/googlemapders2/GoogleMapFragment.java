package com.example.googlemapders2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapFragment extends Fragment implements OnMapReadyCallback {
    View view;
    MapView mapView;
    GoogleMap gm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_google_map, container, false);
        mapView=(MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        //onMapReady() fonksiyonunun tetiklenebilmesi için
        mapView.getMapAsync(GoogleMapFragment.this);


        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gm=googleMap;
        LatLng sydney = new LatLng(40.8415972, 35.616067);
        gm.addMarker(new MarkerOptions().position(sydney).title("Marker 1"));
       // gm.setMapType(GoogleMap.MAP_TYPE_HYBRID);  //mavili koyu renkli harita çıkıyor.
        gm.animateCamera(CameraUpdateFactory.zoomTo(6.0f)); //yakınlaştırıyoruz.
    }

}
