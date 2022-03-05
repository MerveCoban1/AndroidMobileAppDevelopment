package com.example.retrofitornek4activitygecisli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Models.Bilgi;
import RestApi.ManagerAll;
import adapter.AdapterSinifi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<Bilgi> list;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        istek();
    }

    public void istek() {
        list = new ArrayList<>();
        Call<List<Bilgi>> call = ManagerAll.getInstance().getirCall();
        call.enqueue(new Callback<List<Bilgi>>() {
            @Override
            public void onResponse(Call<List<Bilgi>> call, Response<List<Bilgi>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    AdapterSinifi adp = new AdapterSinifi(list, getApplicationContext(), MainActivity.this);
                    listView.setAdapter(adp);
                }
            }

            @Override
            public void onFailure(Call<List<Bilgi>> call, Throwable t) {

            }
        });
    }
}
