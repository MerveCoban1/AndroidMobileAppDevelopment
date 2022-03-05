package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
import veritabaniTablolari.ResimlerVeritabaniTablosu;
import veritabaniTablolari.UyeBilgileri;

public class Sicaklik extends AppCompatActivity {
    ImageView denemeResim;
    Realm realmDeneme;
    TextView merve1,merve2,merve3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sicaklik);
        realmDeneme = Realm.getDefaultInstance();
        tanimla();
        //resimGoster();
        uyeler();
    }
    public void tanimla(){
        denemeResim=(ImageView)findViewById(R.id.denemeResim);
        merve1=(TextView)findViewById(R.id.merve1);
        merve2=(TextView)findViewById(R.id.merve2);
    }
    public void resimGoster(){
        realmDeneme.beginTransaction();
        RealmResults<ResimlerVeritabaniTablosu> sonuc=realmDeneme.where(ResimlerVeritabaniTablosu.class).findAll();
        for(ResimlerVeritabaniTablosu gir : sonuc){
            denemeResim.setImageBitmap(StringToBitmap(gir.getResim_yolu()));
        }
        realmDeneme.commitTransaction();
    }
    public Bitmap StringToBitmap(String st) {
            byte [] encodeByte = Base64.decode(st,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
    }
    public void uyeler(){
        realmDeneme.beginTransaction();
        RealmResults<UyeBilgileri> sonuc=realmDeneme.where(UyeBilgileri.class).findAll();
        for(UyeBilgileri gir : sonuc){
            merve1.setText(gir.getKisiEmail());
            merve2.setText(gir.getKisiSifre());

        }
        realmDeneme.commitTransaction();
    }
}
