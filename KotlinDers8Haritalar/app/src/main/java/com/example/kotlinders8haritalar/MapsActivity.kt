package com.example.kotlinders8haritalar

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception
import java.util.*

/**
 * önce bir api keyi alıyorsun
 *ACCESS_FINE_LOCATION izni manifestte alınmış mı bak
 * harita üzerindeki herhangi bir enlemi boylamı adrese çevirmek için geocoder kullanılıyor
 */
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    //kullanıcının konumunu alacağız
    private lateinit var locationManager: LocationManager //bütün süreci yönetmeye olanak sağlar
    private lateinit var locationListener: LocationListener //asıl işi yapacak

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        /*
        //harita hazır olduğunda çalıştırılan bir fonksiyon
        mMap = googleMap
        //Latlng=latitude&Longitude enlem ve boylamdan oluşuyor
        val sydney = LatLng(-34.0, 151.0)
        //marker dediğimiz orda gördüğümüz kırmızı işaret
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        //zoom yapmak istiyoruz
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15f))
        */
        mMap = googleMap
        mMap.setOnMapLongClickListener(myListener)
        //as ile cast işlemi yapıyoruz
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                //kullanıcının lokasyonu değiştiğinde ne yapacağız
                //her location değiştiğinde tekrar marker eklenmesin istiyoruz bu yüzden
                mMap.clear()  //başka bir marker eklemeden haritayı temizledik
                //şimdi konumu gösterelim
                if (location != null) {
                    val userLocation = LatLng(location.latitude, location.longitude)
                    mMap.addMarker(MarkerOptions().position(userLocation).title("Yours Location"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))

                    //oranın adresini de alalım
                    val geocoder = Geocoder(this@MapsActivity, Locale.getDefault())
                    val adressList =
                        geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    if (adressList != null && adressList.size > 0) {
                        println(adressList.get(0).toString())
                    }


                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                //kullanıcının durumu değiştiğinde ne yapacağız
            }

            override fun onProviderEnabled(provider: String?) {
                //lokasyonu sağlayan servis tekrar devreye girerse ne yapacağız
            }

            override fun onProviderDisabled(provider: String?) {
                //lokasyonu sağlayan servis devre dışı kaldığında ne yapacağız
            }

        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //izin al
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )

        } else {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1,
                1f,
                locationListener
            )
            //locationManager üstünde daha önceden alınan bir konum varsa alalım onu
            val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (lastLocation != null) {
                val lastKnowLatlng = LatLng(lastLocation.latitude, lastLocation.longitude)
                mMap.addMarker(MarkerOptions().position(lastKnowLatlng).title("Yours Location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastKnowLatlng, 15f))
            }


        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.size > 0) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    //kullanıcının konumunu alalım
                    //ilk rakam : en kısa ne kadar sürede ikinci: en düşük ne kadar mesafede biraz büyük tutmakta fayda var rakamları telefon pili için
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        1,
                        1f,
                        locationListener
                    )
                }
            }
        }
    }

    val myListener = object : GoogleMap.OnMapLongClickListener {
        override fun onMapLongClick(p0: LatLng?) {
            mMap.clear()
            val geocoder = Geocoder(this@MapsActivity, Locale.getDefault())
            if (p0 != null) {
                var address = ""
                try {
                    val addressList = geocoder.getFromLocation(p0.latitude, p0.longitude, 1)
                    if (addressList != null && addressList.size > 0) {
                        if (addressList[0].thoroughfare != null) {  //thoroughfare: cadde adı
                            address += addressList[0].thoroughfare
                            if (addressList[0].subThoroughfare != null) {
                                address += addressList[0].subThoroughfare
                            }
                        }
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
                mMap.addMarker(MarkerOptions().position(p0).title(address))
            }
        }
    }

}
