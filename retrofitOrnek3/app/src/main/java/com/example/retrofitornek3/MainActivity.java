package com.example.retrofitornek3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Models.YeniBilgi;
import RestApi.ManagerAll;
import adapter.AdapterListIcin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<YeniBilgi> list;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        istek();
    }

    public void istek() {
        list = new ArrayList<>();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Bilgiler Ekrani");
        progressDialog.setMessage("icerik yükleniyor, lütfen bekleyiniz...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Call<List<YeniBilgi>> servis = ManagerAll.getInstance().getirBilgi();
        servis.enqueue(new Callback<List<YeniBilgi>>() {
            @Override
            public void onResponse(Call<List<YeniBilgi>> call, Response<List<YeniBilgi>> response) {
                if (response.isSuccessful()) {
                    //Log.i("cevap",response.body().toString());                   //response'un body si bir list döndürüyor.
                    list = response.body();
                    // Log.i("boyut",""+list.size());//"" bununla birleştirince bir string ifade gibi oldu.
                    AdapterListIcin adp = new AdapterListIcin(list, getApplicationContext());
                    listView.setAdapter(adp);
                }
                //liste dolduğunda yani işlem bitmiş oluyor.Progress diyalogum kapanmalı
                progressDialog.cancel();
            }

            @Override
            public void onFailure(Call<List<YeniBilgi>> call, Throwable t) {

            }
        });
    }
}
