package com.example.kotlinders16foursquareparse

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_place_name.*

/**
 * bir sonraki aktiviteye verileri aktaracağız bunun için pek güvenli olmasa da şuanlık
 * global tanımlayacağız verilerimizi. Tüm sınıflardan ulaşabileceğiz bu değişkenlere
 */
var globalName=""
var globalType=""
var globalAtmosfer=""
var globalImage:Bitmap?=null

class PlaceNameActivity : AppCompatActivity() {

    var selected: Uri? = null
    var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_name)
        action()
    }

    fun action(){
        selectedimageView.setOnClickListener {
            izinAl()
        }
        next.setOnClickListener {
            globalImage=bitmap
            globalName=nameEditText.text.toString()
            globalType=typeEditText.text.toString()
            globalAtmosfer=atmosferEditText.text.toString()

            val intent=Intent(applicationContext,MapsActivity::class.java)
            startActivity(intent)
        }
    }

    fun izinAl(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 2)
        } else {
            val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intentToGallery, 1)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 2) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intentToGallery, 2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            selected = data.data
            if (selected != null) {
                if (Build.VERSION.SDK_INT >= 28) {
                    val source = ImageDecoder.createSource(this.contentResolver, selected!!)
                    bitmap = ImageDecoder.decodeBitmap(source)
                    selectedimageView.setImageBitmap(bitmap)
                } else {
                    //getBitmap api28 sonrasında çalışmıyor
                    bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selected)
                    selectedimageView.setImageBitmap(bitmap)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}
