package com.example.kotlinders13retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinders13retrofit.R
import com.example.kotlinders13retrofit.adapter.RecyclerViewAdapter
import com.example.kotlinders13retrofit.model.CryptoModel
import com.example.kotlinders13retrofit.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * api key: dd4455e3cc65c45c155944c30d905c3e
 * api server url: https://api.nomics.com/v1
 *https://api.nomics.com/v1/prices?key=dd4455e3cc65c45c155944c30d905c3e
 *
 * Not:
 * bir sürü call yaptığımızda kodları daha verimli yapabilmek için RxJava kullanacağız
 */
class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {

    private val BASE_URL = "https://api.nomics.com/v1/"
    private var cryptoModels: ArrayList<CryptoModel>? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null

    //Disposable: tek kullanımlık demek
    //aktivite falan kapandığında bu callar kapanıyor
    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable = CompositeDisposable()

         val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        //loadData()
        loadData2()
    }

    private fun loadData() {
        /* val retrofit = Retrofit.Builder()
             .baseUrl(BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
         //api ile retrofiti birbirine bağlayacağız
         val service = retrofit.create(CryptoAPI::class.java)
         //call asenkron bir şekilde verileri alır
         val call = service.getData()
         call.enqueue(object : Callback<List<CryptoModel>> {
            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(call: Call<List<CryptoModel>>, response: Response<List<CryptoModel>>) {
                if (response.isSuccessful) {
                    response.body()?.let { //eğer bu boş gelmediyse demek bu
                        cryptoModels=ArrayList(it)
                        //it=response.body()
                        cryptoModels?.let {
                            recyclerViewAdapter= RecyclerViewAdapter(it,this@MainActivity)
                            recyclerView.adapter=recyclerViewAdapter
                        }
                    }
                }
            }
        })*/
    }

    private fun loadData2() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //RxJava kullanacağımızı belirttik
            .build().create(CryptoAPI::class.java)

        compositeDisposable?.add(retrofit.getData()  //farklı farklı callları add diyerek ekleyebiliyoruz
                .subscribeOn(Schedulers.io())   //arkaplanda dinlerken
                .observeOn(AndroidSchedulers.mainThread())  //ön planda işleme aldık
                .subscribe(this::handleResponse)
        )
    }

    private fun handleResponse(cryptoList : List<CryptoModel>){
        cryptoModels=ArrayList(cryptoList)
        cryptoModels?.let {
            recyclerViewAdapter= RecyclerViewAdapter(it,this@MainActivity)
            recyclerView.adapter=recyclerViewAdapter
        }
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this, "Clicked: ${cryptoModel.currency}", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

}
