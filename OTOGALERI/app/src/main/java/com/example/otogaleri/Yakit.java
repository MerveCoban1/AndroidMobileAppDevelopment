package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import Models_ılerledikce_verileri_tutuyoruz.IlanVer;
import io.realm.Realm;
import veritabaniTablolari.IlanlarVeritabaniTablosu;

public class Yakit extends AppCompatActivity {
    EditText yakitTipiBilgi,ortalamaYakitBilgi;
    Button ileri,geri;
    Realm realm2;
    Integer sayi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yakit);
        realm2=Realm.getDefaultInstance();
        tanimla();
    }
    public void tanimla(){
        yakitTipiBilgi=(EditText)findViewById(R.id.yakitTipiBilgi);
        ortalamaYakitBilgi=(EditText)findViewById(R.id.ortalamaYakitBilgi);
        ileri=(Button) findViewById(R.id.ileri);
        geri=(Button) findViewById(R.id.geri);

        yakitTipiBilgi.setText(IlanVer.getYakitTipi());
        ortalamaYakitBilgi.setText(IlanVer.getOrtalamaYakit());

        ileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(!yakitTipiBilgi.getText().toString().equals("") && !ortalamaYakitBilgi.getText().toString().equals("")){
                   IlanVer.setYakitTipi(yakitTipiBilgi.getText().toString());
                   IlanVer.setOrtalamaYakit(ortalamaYakitBilgi.getText().toString());
                   ilaniVeritabaninaKaydet();

               }else {
                   Toast.makeText(getApplicationContext(),"Lutfen Tum Alanlari Doldurun",Toast.LENGTH_LONG).show();
               }

            }
        });
        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Yakit.this,MotorPerformans.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });
    }
    public void ilaniVeritabaninaKaydet(){
        realm2.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Random rnd = new Random();
                sayi = rnd.nextInt(100000000);

                IlanlarVeritabaniTablosu ilan = realm.createObject(IlanlarVeritabaniTablosu.class);
                ilan.setIlan_id(sayi.toString());  //sayiyi ilana özel olarak rastgele urettık
                ilan.setUye_id(IlanVer.getUye_id());
                ilan.setIlanAciklama(IlanVer.getAciklama());
                ilan.setIlanBaslik(IlanVer.getBaslik());
                ilan.setSehirBilgi(IlanVer.getSehir());
                ilan.setIlceBilgi(IlanVer.getIlce());
                ilan.setMahalleBilgi(IlanVer.getMahalle());
                ilan.setMarkaBilgi(IlanVer.getMarka());
                ilan.setSeriBilgi(IlanVer.getSeri());
                ilan.setModelBilgi(IlanVer.getModel());
                ilan.setMotorHacmiBilgi(IlanVer.getMotorHacmi());
                ilan.setMotorTipiBilgi(IlanVer.getMotorTipi());
                ilan.setOrtalamaYakitBilgi(IlanVer.getOrtalamaYakit());
                ilan.setYakitTipiBilgi(IlanVer.getYakitTipi());
                ilan.setKimden(IlanVer.getKimden());
                ilan.setIlanTipi(IlanVer.getIlan_tipi());
                ilan.setUcret(IlanVer.getUcret());

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(),"Ilaniniz Basariyla Kaydedildi",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(Yakit.this,IlanResimler.class);
                intent.putExtra("ilan_id",sayi.toString());
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                finish();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(getApplicationContext(),"Ilaniniz Kaydedilemedi",Toast.LENGTH_LONG).show();
            }
        });
    }
}
