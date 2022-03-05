package com.example.kotlinders15workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import java.util.concurrent.TimeUnit

/**
 * gradle eklemelerimi yaptım.dependencies kısmına
 * yeni bir worker sınıfı oluşturacağız
 *
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Data androidx. olması lazım
        val data = Data.Builder().putInt("intKey", 1).build()
        //veriyi yolladık

        //Constraints de androidx. olmalı
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)  //ağa bağlı olması lazım dedik
            .setRequiresCharging(false)  //teledon şarjda olmak zorunda değil dedik
            .build()
        //artık işimizi yapabiliriz
        //jvm yi file-project structure-modulsten değiştirdik

        //bu tek sefer yapıyo
        /*val myWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<RefreshDatabase>()
            .setConstraints(constraints)
            .setInputData(data)
            //.setInitialDelay(5,TimeUnit.HOURS)  //5 saat sonra yapıyo mesela bu
            .addTag("myTag")
            .build()
        WorkManager.getInstance(this).enqueue(myWorkRequest)*/

        //şimdi periyodik olanını yapalım.   15 dkda bir yap dedik. En az 15 dakikada bir
        //yapılıyomuş bu workmanager kuralı. daha düşük bir sürede yapılamazmış
        val myWorkRequest:PeriodicWorkRequest= PeriodicWorkRequestBuilder<RefreshDatabase>(15,TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setInputData(data)
            .build()
        //WorkManager.getInstance(this).enqueue(myWorkRequest)
        //workManager da işlemlerim çalışıyor mu noluyor kontrol etmek için enqueue yerine:
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(myWorkRequest.id).observe(this,
            Observer {
                if (it.state==WorkInfo.State.FAILED){
                    Toast.makeText(applicationContext,"failed",Toast.LENGTH_LONG).show()
                }else if (it.state==WorkInfo.State.RUNNING){
                    Toast.makeText(applicationContext,"running",Toast.LENGTH_LONG).show()
                } else if (it.state==WorkInfo.State.SUCCEEDED){
                    Toast.makeText(applicationContext,"succeeded",Toast.LENGTH_LONG).show()
                }
            })

        //mesela yaptığımız işlemleri iptal etmek istiyoruz:
        //WorkManager.getInstance(this).cancelAllWork()     //hepsi iptal edilir

        //Chaining: WorkManager sen şu işlemle başla o bitsin şunu yap sonra şunu yap diyebiliyoruz
       /* val oneTimeRequest:OneTimeWorkRequest= OneTimeWorkRequestBuilder<RefreshDatabase>()
            .setConstraints(constraints)
            .setInputData(data)
            .build()
        WorkManager.getInstance(this).beginWith(oneTimeRequest)  //bi tanesi ile başla
            .then(oneTimeRequest)
            .then(oneTimeRequest)
            .enqueue()*/


    }
}
