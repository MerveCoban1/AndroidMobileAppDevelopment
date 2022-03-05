package com.example.maileaktivasyonlinkignderme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Models.Result;
import RestApi.ManagerAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText kullaniciAdiEditText, sifreEditText, mailEditText;
    Button ekle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();

    }

    public void tanimla() {
        kullaniciAdiEditText = (EditText) findViewById(R.id.kullaniciAdiEditText);
        sifreEditText = (EditText) findViewById(R.id.sifreEditText);
        mailEditText = (EditText) findViewById(R.id.mailEditText);
        ekle = (Button) findViewById(R.id.ekle);
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                istek();
            }
        });
    }

    public void istek() {
        String kullaniciAdi,kullaniciSifre,kullaniciMail;
        kullaniciAdi=kullaniciAdiEditText.getText().toString();
        kullaniciSifre=sifreEditText.getText().toString();
        kullaniciMail=mailEditText.getText().toString();
        if(!kullaniciAdi.equals("")&&!kullaniciMail.equals("")&&!kullaniciSifre.equals("")){
            Call<Result> istek= ManagerAll.getInstance().ekle(kullaniciAdi,kullaniciSifre,kullaniciMail);
            istek.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    if(response.body().result){ //true ve false değerleri döndürüyorduk boolean olarak.

                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {

                }
            });
        }else{
            Toast.makeText(getApplicationContext(),"eksik bilgi var",Toast.LENGTH_SHORT).show();
        }

    }
}
