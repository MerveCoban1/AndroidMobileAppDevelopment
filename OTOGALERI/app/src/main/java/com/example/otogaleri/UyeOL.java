package com.example.otogaleri;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;
import veritabaniTablolari.UyeBilgileri;

public class UyeOL extends AppCompatActivity {
    Realm realm;
    EditText email, sifre;
    Button kaydet;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_o_l);
        realm = Realm.getDefaultInstance();
        tanimla();
        sharedPreferences=getApplicationContext().getSharedPreferences("giriskayit",0);
        if(sharedPreferences.getString("uye_mail",null)!=null&&sharedPreferences.getString("uye_sifre",null)!=null){
            Intent gecis = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(gecis);
        }
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String kisiMail = email.getText().toString();
                final String kisiSifre = sifre.getText().toString();
                kontrol(kisiMail,kisiSifre);

            }
        });

    }

    public void tanimla() {
        email = (EditText) findViewById(R.id.email);
        sifre = (EditText) findViewById(R.id.sifre);
        kaydet = (Button) findViewById(R.id.kaydet);
    }

    public void ekle(final String kisiMail, final String kisiSifre) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                UyeBilgileri uye = realm.createObject(UyeBilgileri.class);
                uye.setKisiEmail(kisiMail);
                uye.setKisiSifre(kisiSifre);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                sharedPreferences=getApplicationContext().getSharedPreferences("giriskayit",0);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("uye_mail",kisiMail);
                editor.putString("uye_sifre",kisiSifre);
                editor.commit();
                Intent intent2=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent2);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                LayoutInflater inflater=getLayoutInflater();
                View view=inflater.inflate(R.layout.alert_diyalog,null);
                Button tamam=view.findViewById(R.id.tamam);
                AlertDialog.Builder alert=new AlertDialog.Builder(UyeOL.this);
                alert.setView(view);
                alert.setCancelable(false);
                final AlertDialog dialog=alert.create();
                tamam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });


    }
    public void kontrol(String kisiMail,String kisiSifre){
        realm.beginTransaction();
        RealmResults<UyeBilgileri> sonuc = realm.where(UyeBilgileri.class).findAll();
        int a=0;
        for (UyeBilgileri u : sonuc) {
            if(u.getKisiEmail().equals(kisiMail)){
                Toast.makeText(UyeOL.this,"BOYLE BIR MAIL ZATEN KAYITLI LUTFEN TEKRAR DENEYIN",Toast.LENGTH_LONG).show();
                a++;
            }
        }
        if(a==0){
            ekle(kisiMail, kisiSifre);
        }
        realm.commitTransaction();
    }

    public void veriCek() {
        realm.beginTransaction();
        RealmResults<UyeBilgileri> sonuc = realm.where(UyeBilgileri.class).findAll();
        for (UyeBilgileri u : sonuc) {
            //Log.i("dene", u.toString());
        }
        realm.commitTransaction();
    }
}
