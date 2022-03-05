package com.example.mobilcihazdansunucuuzerndekvertabannakaytekleme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import Models.Deneme;
import RestApi.ManagerAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        istek();

    }


    public void istek() {
        Call<Deneme> abv = ManagerAll.getInstance().ekle();
        abv.enqueue(new Callback<Deneme>() {
            @Override
            public void onResponse(Call<Deneme> call, Response<Deneme> response) {
                Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Deneme> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "basarisiz", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
