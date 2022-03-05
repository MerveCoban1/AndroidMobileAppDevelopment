package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Adapter.IlanlarimAdapter;
import Models_ılerledikce_verileri_tutuyoruz.IlanlarimPojoSinifi;
import io.realm.Realm;
import io.realm.RealmResults;
import veritabaniTablolari.IlanlarVeritabaniTablosu;
import veritabaniTablolari.ResimlerVeritabaniTablosu;

public class Ilanlarim extends AppCompatActivity {
    Realm realm9;
    ListView ilanlarimListView;
    IlanlarimAdapter ilanlarimAdapter;
    List<IlanlarimPojoSinifi> ilanlarimPojoSinifis;
    SharedPreferences sharedPreferences;
    String uye_id,deneme;
    TextView metin,metin2;
    AlertDialogClass alertDialogClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlarim);
        sharedPreferences=getApplicationContext().getSharedPreferences("giriskayit",0);
        uye_id=sharedPreferences.getString("uye_mail",null);
        tanimla();
        listele();
        ilanlarimListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialogClass.ilanlarimAlertDialog(Ilanlarim.this,ilanlarimPojoSinifis.get(position).getIlan_id());
            }
        });
    }
    public void tanimla(){
        realm9=Realm.getDefaultInstance();
        alertDialogClass=new AlertDialogClass();
        metin=(TextView)findViewById(R.id.metin);
        metin2=(TextView)findViewById(R.id.metin2);
        ilanlarimListView=(ListView)findViewById(R.id.ilanlarimListView);
        ilanlarimPojoSinifis=new ArrayList<>();
    }
    public void listele(){
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Ilanlarim");
        progressDialog.setMessage("Ilanlariniz Yukleniyor Lutfen Bekleyin");
        progressDialog.setCancelable(false);
        progressDialog.show();

        realm9.beginTransaction();
        RealmResults<IlanlarVeritabaniTablosu> sonuc=realm9.where(IlanlarVeritabaniTablosu.class).findAll();
        RealmResults<ResimlerVeritabaniTablosu> sonuc2=realm9.where(ResimlerVeritabaniTablosu.class).findAll();
        for(IlanlarVeritabaniTablosu gir : sonuc){
            deneme=gir.getUye_id();
            metin.setText(deneme);
            metin2.setText(uye_id);
            if(metin2.getText().equals(metin.getText())){
                IlanlarimPojoSinifi kisi=new IlanlarimPojoSinifi();
                kisi.setAciklama(gir.getIlanAciklama());
                kisi.setBaslik(gir.getIlanBaslik());
                kisi.setFiyat(gir.getUcret());
                kisi.setIlan_id(gir.getIlan_id());
                kisi.setUye_id(uye_id);
                for(ResimlerVeritabaniTablosu gir2 : sonuc2){
                    metin.setText(gir2.getIlan_id());
                    metin2.setText(gir.getIlan_id());
                    if(metin2.getText().equals(metin.getText())){
                        kisi.setResim(gir2.getResim_yolu());
                    }
                }
                ilanlarimPojoSinifis.add(kisi);
            }
        }
        realm9.commitTransaction();
        ilanlarimAdapter=new IlanlarimAdapter(ilanlarimPojoSinifis, getApplicationContext(),Ilanlarim.this);
        ilanlarimListView.setAdapter(ilanlarimAdapter);
        progressDialog.cancel();

    }
}
