package com.example.kotlinders9seyahatkitabim

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
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
import java.util.jar.Manifest

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var locationManager:LocationManager
    private lateinit var locationListener:LocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapLongClickListener(myListener)
        mMap.clear()
        locationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationListener=object :LocationListener{
            override fun onLocationChanged(location: Location?) {
                if (location!=null){
                    val sharedPreferences=this@MapsActivity.getSharedPreferences("com.example.kotlinders9seyahatkitabim",Context.MODE_PRIVATE)
                    val firstTimeCheck=sharedPreferences.getBoolean("notFirstTime",false)
                    if (firstTimeCheck==false){
                        val newUserLocation=LatLng(location.latitude,location.longitude)
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newUserLocation,15f))
                        sharedPreferences.edit().putBoolean("notFirstTime",true).apply()
                    }
                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                TODO("Not yet implemented")
            }

            override fun onProviderEnabled(provider: String?) {
                TODO("Not yet implemented")
            }

            override fun onProviderDisabled(provider: String?) {
                TODO("Not yet implemented")
            }
        }
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1)
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2,2f,locationListener)

            val intent=intent
            val info=intent.getStringExtra("info")
            if (info.equals("new")){
                val lastLocation=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (lastLocation!=null){
                    val lastLocationLtlng=LatLng(lastLocation.latitude,lastLocation.longitude)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLocationLtlng,15f))
                }
            }else{
                mMap.clear()
                val selectedPlace=intent.getSerializableExtra("selectedPlace") as Place
                val selectedLocation=LatLng(selectedPlace.latitude!!,selectedPlace.longitude!!)
                mMap.addMarker(MarkerOptions().title(selectedPlace.address).position(selectedLocation))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedLocation,15f))
            }
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode==1){
            if (grantResults.size>0){
                if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2,2f,locationListener)
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    val myListener=object :GoogleMap.OnMapLongClickListener{
        override fun onMapLongClick(p0: LatLng?) {
            //geocoder kullanarak adresi göstercez
            val geocoder=Geocoder(this@MapsActivity, Locale.getDefault())
            var address=""
            if (p0!=null){
                try {
                    val addressList=geocoder.getFromLocation(p0.latitude,p0.longitude,1)
                    if (addressList!=null&&addressList.size>0){
                        if (addressList[0].thoroughfare!=null){
                            address+=addressList[0].thoroughfare
                            if (addressList[0].subThoroughfare!=null){
                                address+=addressList[0].subThoroughfare
                            }
                        }
                    }else{
                        address="New Addres"
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }
                mMap.clear()
                mMap.addMarker(MarkerOptions().position(p0).title(address))

                val newPlace=Place(address,p0.latitude,p0.longitude)
                //veritabanına eklemeden önce soruyoruz gerçekten eklemek istiyor musunuz diye
                val dialog=AlertDialog.Builder(this@MapsActivity)
                dialog.setCancelable(false)   //ekranın başka yerine basılınca çıkılmasın diye
                dialog.setTitle("Are You Sure")
                dialog.setMessage(newPlace.address)
                dialog.setPositiveButton("Yes"){dialog, which ->
                    //veritabanına eklenecek veri
                    try {
                        val database=openOrCreateDatabase("Places",Context.MODE_PRIVATE,null)
                        database.execSQL("CREATE TABLE IF NOT EXISTS places(address VARCHAR,latitude DOUBLE,longitude DOUBLE)")
                        val toCompile="INSERT INTO places(address,latitude,longitude) VALUES(?,?,?)"
                        val sqLiteStatement=database.compileStatement(toCompile)
                        sqLiteStatement.bindString(1,newPlace.address)
                        sqLiteStatement.bindDouble(2,newPlace.latitude!!)
                        sqLiteStatement.bindDouble(3,newPlace.longitude!!)
                        sqLiteStatement.execute()

                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                }
                dialog.setNegativeButton("No"){dialog, which ->

                }
                dialog.show()

            }
        }

    }

    override fun onBackPressed() {
        val intentToMain=Intent(this,MainActivity::class.java)
        startActivity(intentToMain)
        finish()
        super.onBackPressed()
    }












}
