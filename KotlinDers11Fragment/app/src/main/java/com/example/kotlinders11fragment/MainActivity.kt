package com.example.kotlinders11fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

/**
 * java-com-new-fragment diyerek boş bir fragment oluşturabiliriz
 *fragmentleri göstermek istediğimiz kısma da xml'de layout koymamız gerekiyor
 frameLayout bunun için çok uygun. Bildiğimiz gibi framelayout'u tek bir şey kullana
 cağımız zaman tercih ediyorduk
 */
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        action()
    }

    fun action(){
        birinciButton.setOnClickListener {
            val fragmentManager=supportFragmentManager
            //yapacağımız işlemleri başlatmak için transaction oluşturuyoruz
            val fragmentTransaction=fragmentManager.beginTransaction()

            val firstFragment=Fragment1()
            //fragmentTransaction.add(R.id.frameLayout,firstFragment).commit()
            fragmentTransaction.replace(R.id.frameLayout,firstFragment).commit()


        }
        ikinciButton.setOnClickListener {
            val fragmentManager=supportFragmentManager
            val fragmentTransaction=fragmentManager.beginTransaction()

            val secondFragment=Fragment2()
            //add direk üstüne ekleme yapıyor daha önceki varsa da o da kalıyor üstüste bişey oluyor.
            //fragmentTransaction.add(R.id.frameLayout,secondFragment).commit()
            fragmentTransaction.replace(R.id.frameLayout,secondFragment).commit()
        }
    }
}
