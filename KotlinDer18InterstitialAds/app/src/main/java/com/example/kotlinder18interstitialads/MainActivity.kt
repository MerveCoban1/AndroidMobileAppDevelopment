package com.example.kotlinder18interstitialads

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.activity_main.*

/**
 * bu reklamımım idsi Interstitial: ca-app-pub-4718213835461634/9354875604
 google play'e yüklerken lazım oluyor bu
 * Interstitial test id: ca-app-pub-3940256099942544/1033173712
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"  //bu id deneme id'si dikkat et
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        //ben geri gelsem de her seferinde 2.aktiviteye giderken reklam açılsın istiyorsam eğer
        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                mInterstitialAd.loadAd(AdRequest.Builder().build())
            }
        }

        basGit.setOnClickListener {
            val intent=Intent(applicationContext,Main2Activity::class.java)
            startActivity(intent)
            //bu tek seferliğe mahsus çalışıyor.
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            }
        }
    }
}
