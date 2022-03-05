package com.example.javakotlinders12retrofitkriptoparauygulamasi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.javakotlinders12retrofitkriptoparauygulamasi.R;
import com.example.javakotlinders12retrofitkriptoparauygulamasi.adapter.RecyclerViewAdapter;
import com.example.javakotlinders12retrofitkriptoparauygulamasi.model.CryptoModel;
import com.example.javakotlinders12retrofitkriptoparauygulamasi.service.CryptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * APİ: uygulama programlama arayüzü. Apiler iki tane sistem arasında iletişimi sağlarlar
 * örn: sunucu ve uygulamamız arasında iletişimi sağlarlar. Veri alıp veri vermemizi sağlarlar.
 * istekte bulunduğumuz siteler de sunucularda bize json verisi döndürüyor.
 * eğer json kötü ve karışık görünüyorsa internette json beautifier diye bi site var json çıktısını düzgün gösteriyor.
 * JSON: java script obejct notation.
 * AsyncTask: Çektiğimiz verilerin asenkron bir şekilde gelmesini sağlıyor. Uzun süren işlemlerin asenkron ve arkaplan
 * da yapılması gerekiyor. Kullanıcının arayüzünü bloklamamamız gerekiyor. İnternetten veri çekmekte uzun süren bir işlemdir.
 * Bunu yapabilmek için asynctask kullanıyorduk. Ama artık kullanılmıyor pek. Bunun yerine Retrofit denilen bir kütüphaneyi
 * kullanacağız.
 * Retrofit: HTTP isteklerini yapmamızı ve isteklerini almamızı sağlayan bir kütüphanedir.
 * RxJava'yı da kullanacağız. Bu da java için yazılmış bir kütüphane. Asenkron kullanıma katkı sağlıyor.
 * nomics.com sitesine girip oradan  üzretsiz bi api key aldık. Ve sitedeki api server url ile birlikte bu api keyi
 * kullanarak json çıktımıza ulaşacağız. adres: https://api.nomics.com/v1/prices?key=5f6e95410211367e54fff990df9ae1b0
 * şimdi gradle işlemlerini yapalım
 *Call sayısı arttıkça işler karışmaya başlıyor. RxJava'yı kullanmalıyız.
 artık RxJava ile işlem yapıyorsan Call işlemini yapmaman gerekiyor.
 *
 *
 */


public class MainActivity extends AppCompatActivity {
    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL = "https://api.nomics.com/v1/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    CompositeDisposable compositeDisposable; //uygulama kapalıyken falan bu verileri temizlemiz gerekiyor


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        //şimdi datayı almaya çalışalım.Retrofiti oluşturalım
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loadData();

    }

    private void loadData() {

        CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class); //veri çekmeye hazırım servis hazır
        compositeDisposable=new CompositeDisposable();
        compositeDisposable.add(cryptoAPI.getData()
        .subscribeOn(Schedulers.io())  //veriyi hangi threadde gözlemleyeceğiz
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::handleResponse));

        /*Call<List<CryptoModel>> call = cryptoAPI.getData();
        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if (response.isSuccessful()) {
                    List<CryptoModel> list = response.body();
                    cryptoModels = new ArrayList<>(list);//bu şekilde listeyi arrayliste çevirdik.

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    adapter = new RecyclerViewAdapter(cryptoModels);
                    recyclerView.setAdapter(adapter);

                   for (CryptoModel model : cryptoModels) {
                        System.out.println(model.getCurrency());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });*/
    }

    private void handleResponse(List<CryptoModel> cryptoModelList){
        cryptoModels = new ArrayList<>(cryptoModelList);//bu şekilde listeyi arrayliste çevirdik.

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new RecyclerViewAdapter(cryptoModels);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
