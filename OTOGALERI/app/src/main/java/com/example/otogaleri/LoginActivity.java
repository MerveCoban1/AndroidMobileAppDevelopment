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
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
import veritabaniTablolari.UyeBilgileri;

public class LoginActivity extends AppCompatActivity {
    EditText email, sifre;
    TextView uyeOl;
    Button giris;
    Realm realm;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        realm = Realm.getDefaultInstance();
        tanimla();
        sharedPreferences=getApplicationContext().getSharedPreferences("giriskayit",0);
        if(sharedPreferences.getString("uye_mail",null)!=null&&sharedPreferences.getString("uye_sifre",null)!=null){
            Intent gecis = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(gecis);
        }
        uyeOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UyeOL.class);
                startActivity(intent);
            }
        });
        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String kullaniciEmail = email.getText().toString();
                final String kullaniciSifre = sifre.getText().toString();
                girisYap(kullaniciEmail,kullaniciSifre);
            }
        });


    }
    public void girisYap(String kullaniciEmail,String kullaniciSifre){
        realm.beginTransaction();
        RealmResults<UyeBilgileri> gelenList = realm.where(UyeBilgileri.class).findAll();
        for (UyeBilgileri kisi : gelenList) {
            if (kisi.getKisiEmail().equals(kullaniciEmail) && kisi.getKisiSifre().equals(kullaniciSifre)) {
                sharedPreferences=getApplicationContext().getSharedPreferences("giriskayit",0);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("uye_mail",kullaniciEmail);
                editor.putString("uye_sifre",kullaniciSifre);
                editor.commit();

                Intent gecis = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(gecis);
            }else{
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.alert_diyalog, null);
                Button tamam = view.findViewById(R.id.tamam);
                AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                alert.setView(view);
                alert.setCancelable(false);
                final AlertDialog dialog = alert.create();
                tamam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        }
        realm.commitTransaction();
    }

    public void tanimla() {
        email = (EditText) findViewById(R.id.email);
        sifre = (EditText) findViewById(R.id.sifre);
        uyeOl = (TextView) findViewById(R.id.uyeOl);
        giris = (Button) findViewById(R.id.giris);
    }
}
