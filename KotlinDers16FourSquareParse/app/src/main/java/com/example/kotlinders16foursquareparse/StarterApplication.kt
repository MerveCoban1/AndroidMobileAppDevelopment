package com.example.kotlinders16foursquareparse

import android.app.Application
import com.parse.Parse

/**
 * sunucumuzla bağlantımızı yapacağız
 * bu sınıfı manifest altında da tanımlamamız gerek
 * artık parse ile bağlantı yapabiliriz
 * Sunucu oluşturmak:
 back4app kullanacağız direk parse için çıkarılmış server platformu
 -proje oluştur-projenin server settings'ine git-core settings-app id al-
 client key al-server için de parse api address'ini al----artık bağladık sunucumuzla parse'ımızı

 */
class StarterApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG)  //logcat de bunları gösterebiliriz
        Parse.initialize(Parse.Configuration.Builder(this)
            .applicationId("78T6tqwLIUf5U8QDhbRHh6vrGlYyw5Qdc58mIjFc")
            .clientKey("hGkCKXtXJGQtfiak0GG2Qh79dBtaIPb1mQFkAVMc")
            .server("https://parseapi.back4app.com/")
            .build())


    }

}