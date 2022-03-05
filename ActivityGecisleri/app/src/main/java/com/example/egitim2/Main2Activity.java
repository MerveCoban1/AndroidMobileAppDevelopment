package com.example.egitim2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {
    String adi;
    String sifre;
    String cinsiyet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //bundle ı activityler arası data geçişi için kullanıyoruz.
        Bundle intent=getIntent().getExtras();//buraya gönderdiğimiz değerleri alacağız.
        adi=intent.getString("kAdi");
        sifre=intent.getString("kSifre");
        cinsiyet=intent.getString("kCinsiyet");
        Log.i("degerler"," "+adi+" "+sifre+" "+cinsiyet);
        //" " bu string birleştirme ifadesidir javada hep kullanılır.
    }
}
