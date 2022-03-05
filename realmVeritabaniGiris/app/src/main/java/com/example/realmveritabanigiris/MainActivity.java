package com.example.realmveritabanigiris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm=Realm.getDefaultInstance();
        tabloyaEkle();
        goster();
    }
    public void tabloyaEkle(){
        realm.beginTransaction();
        KisilerTablosu kisilerTablosu=realm.createObject(KisilerTablosu.class);//nesnemizi oluşturduk.
        kisilerTablosu.setKisiIsmi("sevda");
        kisilerTablosu.setSoyisim("celebi");
        kisilerTablosu.setMaas(12000);
        kisilerTablosu.setYas(21);
        realm.commitTransaction();//beginle bu commitin arasına yazılanlar bir bütünlük içinde gerçekleşir
        //yani ya hepsi gerçekleşir ya da bir hata olduğunda hiçbiri gerçekleşmez
    }
    public void goster(){
        realm.beginTransaction();
        RealmResults<KisilerTablosu> sonuc=realm.where(KisilerTablosu.class).findAll();
        //şuan tablodaki tüm kayıtlar bu sonuc değişkeninin içine atandı.
        for(KisilerTablosu k:sonuc){
            Log.i("girdi",k.toString());
        }
        realm.commitTransaction();
    }
}
