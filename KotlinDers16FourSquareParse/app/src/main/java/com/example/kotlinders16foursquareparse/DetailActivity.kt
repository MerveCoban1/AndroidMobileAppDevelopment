package com.example.kotlinders16foursquareparse

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * activity_maps.xml'i kopyaladık detail.xml'e context'ini ve id'sini değiştirdik tabiki deee!!
 * bu sınıfı da maps sınıfına benzetiyoruz
 */
class DetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private var chosenPlace=""
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapDetail) as SupportMapFragment  //burada mapDetail değiştirmesini unutmaaa!!!!!!!!!!!!
        mapFragment.getMapAsync(this)

        val intent=intent
        chosenPlace=intent.getStringExtra("name") as String

    }

    override fun onMapReady(p0: GoogleMap) {
        mMap=p0

        val query=ParseQuery<ParseObject>("Locations")
        query.whereEqualTo("name",chosenPlace)
        query.findInBackground { objects, e ->
            if (e!=null){
                Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_LONG).show()
            }else{
                if (objects.size>0){
                    for (parseObject in objects){
                        val image=parseObject.get("image") as ParseFile
                        //parseFile' ı download edip bitmape çevireceğim
                        image.getDataInBackground { data, e ->
                            if (e!=null){
                                Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_LONG).show()
                            }else{
                                val bitmap=BitmapFactory.decodeByteArray(data,0,data.size)
                                imageDetailImage.setImageBitmap(bitmap)

                                val name=parseObject.get("name") as String
                                val latitude=parseObject.get("latitude") as String
                                val longitude=parseObject.get("longitude") as String
                                val type=parseObject.get("type") as String
                                val atmosphere=parseObject.get("atmosphere") as String

                                nameDetailText.text=name
                                typeDetailText.text=type
                                atmosferDetailText.text=atmosphere

                                val latitudeDouble=latitude.toDouble()
                                val longitudeDouble=longitude.toDouble()
                                val chosenLocation= LatLng(latitudeDouble,longitudeDouble)
                                mMap.addMarker(MarkerOptions().position(chosenLocation).title(name))
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chosenLocation,15f))


                            }
                        }
                    }
                }
            }
        }

    }
}
