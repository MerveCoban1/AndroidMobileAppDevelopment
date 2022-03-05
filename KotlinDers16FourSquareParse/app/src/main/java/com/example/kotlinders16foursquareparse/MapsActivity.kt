package com.example.kotlinders16foursquareparse

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser
import java.io.ByteArrayOutputStream

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var locationManager:LocationManager
    private lateinit var locationListener:LocationListener
    var latitude=""
    var longitude=""

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater=menuInflater
        menuInflater.inflate(R.menu.save_place,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.save_place){
            saveToParse()
        }
        return super.onOptionsItemSelected(item)
    }

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

        locationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener=object :LocationListener{
            override fun onLocationChanged(location: Location?) {
                if (location!=null){
                    var userLocation=LatLng(location.latitude,location.longitude)
                    mMap.clear()
                    mMap.addMarker(MarkerOptions().position(userLocation).title("Your Location"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15f))
                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

            }

            override fun onProviderEnabled(provider: String?) {

            }

            override fun onProviderDisabled(provider: String?) {

            }
        }
        //izni var mı yok mu onu kontrol edelim
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1)
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2,2f,locationListener)
            mMap.clear()
            val lastLocation=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (lastLocation!=null){
                val lastLocationLtlng=LatLng(lastLocation.latitude,lastLocation.longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLocationLtlng,15f))  //movezoom oraya konuma zoomlamak demek
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
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
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(p0!!).title(globalName))

            latitude=p0.latitude.toString()
            longitude=p0.longitude.toString()
            Toast.makeText(applicationContext,"Artık Kaydedebilirsin",Toast.LENGTH_LONG).show()

        }

    }

    fun saveToParse(){
        //şimdi kaydedelim verilerimizi
        val byteArrayOutputStream=ByteArrayOutputStream()
        globalImage!!.compress(Bitmap.CompressFormat.PNG,50,byteArrayOutputStream)
        val bytes=byteArrayOutputStream.toByteArray()
        //şuan elimdeki image'ı veriye çevirdim

        val parseFile=ParseFile("image.png",bytes)

        val parseObj=ParseObject("Locations")
        parseObj.put("name", globalName)
        parseObj.put("type", globalType)
        parseObj.put("atmosphere", globalAtmosfer)
        parseObj.put("latitude", latitude)
        parseObj.put("longitude", longitude)
        parseObj.put("userName", ParseUser.getCurrentUser().username.toString())
        //image'ı direk alıp kaydedemiyoruz. Bir parse dosyasına çevirmemiz gerek
        parseObj.put("image",parseFile)
        parseObj.saveInBackground { e ->
            if (e!=null){
                Toast.makeText(applicationContext, e.localizedMessage.toString(), Toast.LENGTH_LONG)
            }else{
                Toast.makeText(applicationContext,"Location Created", Toast.LENGTH_LONG)
                val intent= Intent(applicationContext,LocationsActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
