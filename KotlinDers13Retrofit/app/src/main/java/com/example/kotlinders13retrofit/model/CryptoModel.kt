package com.example.kotlinders13retrofit.model

import com.google.gson.annotations.SerializedName

//data snıfıları: içerisine tamamen veri çekeceğimiz ve verileri işleyeceğimiz yapı vardır.
data class CryptoModel(
    //@SerializedName("currency") //bi tane değer gelecek parametresi currency olucak.Onu al burdaki currency değişkenine ata demek
    val currency:String,   //bu değişkenin ismi önemli değil.Ama serializedde önemli aynısı olmalı json çıktısındaki ile
    //ama json çıktısı ile aynı isimdeyse zaten serialized kısmını yazmana gerek yok aslında
    //@SerializedName("price")
    val price:String) {


}