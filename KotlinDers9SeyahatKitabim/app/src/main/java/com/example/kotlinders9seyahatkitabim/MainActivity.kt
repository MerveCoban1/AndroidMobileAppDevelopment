package com.example.kotlinders9seyahatkitabim

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var placesArray=ArrayList<Place>()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater=menuInflater
        menuInflater.inflate(R.menu.add_place,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.add_place_option){
            val intent=Intent(this,MapsActivity::class.java)
            intent.putExtra("info","new")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbdenVerileriCek()
        verileriListele()

    }

    fun dbdenVerileriCek(){
        //veritabanından verilerimizi çekelim
        try {
            val database=openOrCreateDatabase("Places",Context.MODE_PRIVATE,null)
            val cursor=database.rawQuery("SELECT * FROM places",null)
            val addressIndex=cursor.getColumnIndex("address")
            val latitudeIndex=cursor.getColumnIndex("latitude")
            val longitudeIndex=cursor.getColumnIndex("longitude")

            while (cursor.moveToNext()){
                val addressFromDb=cursor.getString(addressIndex)
                val latitudeFromDb=cursor.getDouble(latitudeIndex)
                val longitudeFromDb=cursor.getDouble(longitudeIndex)
                val myPlace=Place(addressFromDb,latitudeFromDb,longitudeFromDb)
                placesArray.add(myPlace)

            }
            cursor.close()

        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun verileriListele(){
        val customAdapter=CustomAdapter(placesArray,this)
        listView.adapter=customAdapter
        listView.setOnItemClickListener{parent, view, position, id ->
            val intent=Intent(this,MapsActivity::class.java)
            intent.putExtra("info","old")
            intent.putExtra("selectedPlace",placesArray.get(position))
            startActivity(intent)
        }
    }
}
