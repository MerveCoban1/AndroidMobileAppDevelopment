package com.example.kotlinders5listview

import android.graphics.Bitmap

class Singleton {
    //singleton: tek bir objesi olan sınıf demektir.
    //javada constructor'ı private yaparız sadece bu sınıf içerisinde obje oluşturabiliriz
    //sonra tüm sınıflardan o objeye ulaşılır.
    //kotlin de biraz daha farklı bu durum daha da kolay

    companion object Selected{
        var selectedImage:Bitmap?=null
    }//bunu kullanınca artık tüm sınıflardan aynı değişkene ulaşabiliriz



}