package com.example.retrofitkutuphanesi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Models.Bilgiler;
import RestApi.ManagerAll;
import adapters.AdapterSinifi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ListView liste;
    List<Bilgiler> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //artık bir istek yapacağız.
        istek();
        liste=(ListView) findViewById(R.id.liste);

    }
    public void istek(){
        list=new ArrayList<>(); //list nesnesi oluşturuyoruz.Tanımlama değil.
        Call<List<Bilgiler>> bilgiList= ManagerAll.getInstance().getirBilgileri();
        bilgiList.enqueue(new Callback<List<Bilgiler>>() {  //isteği attık şimdi bir response dönüyor.call response değil dikkat.
            @Override
            public void onResponse(Call<List<Bilgiler>> call, Response<List<Bilgiler>> response) {
                if(response.isSuccessful()){
                    //Log.i("xxx",response.body().toString());
                    list=response.body();
                    AdapterSinifi adapter=new AdapterSinifi(list,getApplicationContext());
                    liste.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Bilgiler>> call, Throwable t) {

            }
        });
    }
}
