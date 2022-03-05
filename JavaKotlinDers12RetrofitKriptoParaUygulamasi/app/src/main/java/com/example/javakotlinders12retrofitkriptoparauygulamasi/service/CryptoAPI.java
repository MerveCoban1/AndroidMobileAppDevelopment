package com.example.javakotlinders12retrofitkriptoparauygulamasi.service;

import com.example.javakotlinders12retrofitkriptoparauygulamasi.model.CryptoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {
    //GET POST UPDATE DELETE diye işlemlerimiz olabilir

    @GET("prices?key=5f6e95410211367e54fff990df9ae1b0")  //Bu adrese bir get isteği yolla
    //reactx'i seçtiğinden emin ol
    Observable<List<CryptoModel>> getData();



    //Call<List<CryptoModel>> getData();  //bu metot ile de bir liste döndür crypto modeli tipinde








}
