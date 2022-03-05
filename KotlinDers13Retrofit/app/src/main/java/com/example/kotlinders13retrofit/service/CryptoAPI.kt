package com.example.kotlinders13retrofit.service

import com.example.kotlinders13retrofit.model.CryptoModel
import io.reactivex.Observable
import retrofit2.http.GET


interface CryptoAPI {

    @GET("prices?key=dd4455e3cc65c45c155944c30d905c3e")
    //observable rx olanı kullan: gözlemlenebilir bir objedir. Veriler geldiğinde alır
    fun getData(): Observable<List<CryptoModel>>
    //fun getData():Call<List<CryptoModel>>
}