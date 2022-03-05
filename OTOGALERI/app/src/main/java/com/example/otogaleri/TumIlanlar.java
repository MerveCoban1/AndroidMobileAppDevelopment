package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmResults;
import veritabaniTablolari.IlanlarVeritabaniTablosu;

public class TumIlanlar extends AppCompatActivity {
    Realm realm8;
    ListView listViewTumIlanlar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tum_ilanlar);
        realm8=Realm.getDefaultInstance();

        vericek();
    }
    public void vericek(){
        realm8.beginTransaction();
        RealmResults<IlanlarVeritabaniTablosu> sonuc=realm8.where(IlanlarVeritabaniTablosu.class).findAll();

        for(IlanlarVeritabaniTablosu gir : sonuc){

        }
        realm8.commitTransaction();
    }
}
