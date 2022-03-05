package com.example.javakotlinders12retrofitkriptoparauygulamasi.model;

import com.google.gson.annotations.SerializedName;

public class CryptoModel {
    @SerializedName("currency") //ama burdaki isimler json çıktımdaki isimlerle aynı olmak zorunda
    String currency;//burdaki isim aynı olmak zorunda değil
    @SerializedName("price")
    String price;

    public String getCurrency() {
        return currency;
    }


    public String getPrice() {
        return price;
    }

}
