package com.example.sunucudakiresimlericekme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Adapters.AdapterListView;
import Models.urunler;
import RestApi.ManagerAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<urunler> bilgiler;
    AdapterListView adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        istekAt();

    }

    public void tanimla() {
        listView = findViewById(R.id.listView);
        bilgiler = new ArrayList<>();
    }

    public void istekAt() {

        Call<List<urunler>> istek = ManagerAll.getInstance().goster();
        istek.enqueue(new Callback<List<urunler>>() {
            @Override
            public void onResponse(Call<List<urunler>> call, Response<List<urunler>> response) {
                if (response.isSuccessful()) {
                    bilgiler = response.body();
                    adp = new AdapterListView(bilgiler, getApplicationContext());
                    listView.setAdapter(adp);
                }
            }

            @Override
            public void onFailure(Call<List<urunler>> call, Throwable t) {

            }
        });
    }
}
