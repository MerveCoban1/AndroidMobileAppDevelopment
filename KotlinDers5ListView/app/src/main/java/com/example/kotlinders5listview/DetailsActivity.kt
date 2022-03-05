package com.example.kotlinders5listview

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        //verileriAl()
        verileriAl2()

    }
    fun verileriAl(){
        val intent=intent
        val landMarkName=intent.getStringExtra("name")
        gosterTextView.text=landMarkName
        val singleton=Singleton.Selected
        val selectedBitmap=singleton.selectedImage
        gosterImageView.setImageBitmap(selectedBitmap)
    }

    fun verileriAl2(){
        val intent=intent
        val landMarkName=intent.getStringExtra("name")
        gosterTextView.text=landMarkName
        val landMarkImageId=intent.getIntExtra("image",0)
        //aldığımız int'i bitmape çevircez
        val selectedBitmap=BitmapFactory.decodeResource(applicationContext.resources,landMarkImageId)
        gosterImageView.setImageBitmap(selectedBitmap)


    }
}
