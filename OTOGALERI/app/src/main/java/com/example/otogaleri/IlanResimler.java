package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import Models_ılerledikce_verileri_tutuyoruz.IlanVer;
import io.realm.Realm;
import veritabaniTablolari.ResimlerVeritabaniTablosu;

public class IlanResimler extends AppCompatActivity {
    Button resimSecButonu, resimEkleButonu, cik;
    ImageView secilenResim;
    String ilanId;
    String uye_İd,image;
    Realm realm3;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_resimler);
        realm3 = Realm.getDefaultInstance();
        Bundle intent = getIntent().getExtras();
        ilanId = intent.getString("ilan_id");
        tanimla();
    }

    public void tanimla() {
        uye_İd= IlanVer.getUye_id();
        resimSecButonu = (Button) findViewById(R.id.resimSecButonu);
        resimEkleButonu = (Button) findViewById(R.id.resimEkleButonu);
        cik = (Button) findViewById(R.id.cik);
        secilenResim = (ImageView) findViewById(R.id.secilenResim);

        resimSecButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resimGoster();
            }
        });
        resimEkleButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yukle();
            }
        });
    }

    public void yukle(){
        image=imageToString();

        realm3.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ResimlerVeritabaniTablosu resim = realm.createObject(ResimlerVeritabaniTablosu.class);
                resim.setIlan_id(ilanId);
                resim.setResim_yolu(image);
                resim.setUye_id(uye_İd);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(),"resminiz yuklendi",Toast.LENGTH_LONG).show();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(getApplicationContext(),"resminiz yuklenirken bir hata olustu",Toast.LENGTH_LONG).show();
            }
        });
    }

    //bu fonksiyon galerimizi açıyor.
    public void resimGoster(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,777);
    }

    //bu fonksiyon secilen resmi bitmapa çeviriyor.
    //daha sonra bu bitmapi de Image'e atıp image'i de ımageViewda gösterilmesi işlemini yapıyor.
    @Override
    protected  void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==777 && resultCode==RESULT_OK && data!=null){
            Uri path=data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                secilenResim.setImageBitmap(bitmap);  //göstermek istediğimiz imageviewımız secilenResim
                secilenResim.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //resmin base64 stringe çevirilmesi işlemini yapıyor.
    public String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] byt=byteArrayOutputStream.toByteArray();
        String imageToString= Base64.encodeToString(byt,Base64.DEFAULT);
        return imageToString;
    }
}
