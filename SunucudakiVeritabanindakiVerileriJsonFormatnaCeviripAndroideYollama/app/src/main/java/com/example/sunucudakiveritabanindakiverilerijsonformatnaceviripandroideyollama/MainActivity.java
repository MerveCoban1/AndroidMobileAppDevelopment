package com.example.sunucudakiveritabanindakiverilerijsonformatnaceviripandroideyollama;
//sunucudaki veritabanına bağlanıp verileri listeledik,sildik.
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Adapters.AdapterListView;
import Model.Kullanici;
import Model.Result;
import RestApi.ManagerAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<Kullanici> kullanici;
    ListView liste;
    AdapterListView adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        liste = (ListView) findViewById(R.id.liste);
        istek();
    }

    public void tikla() {
        //eğer layout'a tıklanma verirsek normal buton gibi olacak
        //ama listView'a tıklanma verirsek tıklandığı yerin position'unu döndürecek.
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               goster(kullanici.get(position).getId());

            }
        });
    }

    public void istek() {
        kullanici = new ArrayList<>();
        Call<List<Kullanici>> x = ManagerAll.getInstance().goster();
        x.enqueue(new Callback<List<Kullanici>>() {
            @Override
            public void onResponse(Call<List<Kullanici>> call, Response<List<Kullanici>> response) {
                if (response.isSuccessful()) {
                    kullanici = response.body();
                    adp = new AdapterListView(kullanici, getApplicationContext(), MainActivity.this);
                    liste.setAdapter(adp);
                }
            }

            @Override
            public void onFailure(Call<List<Kullanici>> call, Throwable t) {

            }
        });
    }
    public void goster(final String id) {
        LayoutInflater inflater =getLayoutInflater();
        View view = inflater.inflate(R.layout.layoutdialog, null);
        Button evet = view.findViewById(R.id.evet);
        Button hayir = view.findViewById(R.id.hayir);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(view);
        alert.setCancelable(false);
        final AlertDialog alertDialog = alert.create();
        hayir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        evet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Result>  sil= ManagerAll.getInstance().deleteFromDb(id);
                sil.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        istek();
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                    }
                });
                alertDialog.cancel();
            }
        });
        alertDialog.show();
    }
}
