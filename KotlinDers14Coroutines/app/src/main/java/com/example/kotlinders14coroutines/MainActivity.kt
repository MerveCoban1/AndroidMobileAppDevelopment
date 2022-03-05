package com.example.kotlinders14coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Light Weightness
        GlobalScope.launch {
            repeat(100000){
                launch {
                    println("android")
                }
            }
        }
        //RunBloking: önce içerisi çalışıyo yani blokluyo orayı ora bitiyo sonra alttan normal kodlarımız çalışmaya devam ediyor
        runBlocking {
            //direk bir coroutine başlatabiliyoruz
            launch {
                delay(2000)
                println("run blocking")
            }
        }
        //GlobalScope: arkaplanda çalışıyo bloklamadan yani altta olanlar hemen bu çalışırken çalışmaya devam ediyor
        GlobalScope.launch {
            delay(2000)
            println("global scope")
        }
        //CoroutineScope: GlobalScope gibi çalışıyor ama globalScope bütün application içersinde çalıştırılmasını sağlarken
        //coroutine de hangi threadlerde çalıştırılacağını falan seçebiliyoruz. Genelde bu kullanılır bu yüzden
        CoroutineScope(Dispatchers.Default).launch {
            delay(2000)
            println("coroutine scope")
        }
        //iç-içe coroutine
        runBlocking {
            launch {
                delay(2000)
                println("run blocking")
            }
            coroutineScope { //başka bir coroutine içinde olduğumuz için küçük harfle başlayarak çalıştırabildik. Normalde dışarda çalışmıyor.
                launch {
                    delay(3000)
                    println("coroutine scope")
                }
            }
        }
        //Dispatchers
        //Dispatchers.Default -> CPU Intensive yoğun işlemlerde kullanılıyor. Mesela görsel işleme
        //Dispatchers.IO -> Input/output işlemleri internetten bişey çekerken vs kullanılır
        //Dispatchers.Main -> Kullanıcının göreceği işlemleri burada yapmalıyız
        //Dispatchers.Unconfined -> miras alıyor içinde çalıştırılan yere göre değişiyor, kendi ayarlıyor diyebiliriz
        runBlocking {
            //içinde istediğim kadar launch yapabilirim
            launch(Dispatchers.Main) {
                //biz coroutine şu threadlerde çalıştırılsın diyoruz O da bizim için çalıştırıyor
                println("Main Thread: ${Thread.currentThread().name}")
            }
            launch(Dispatchers.IO) {
                println("IO Thread: ${Thread.currentThread().name}")
            }
            launch(Dispatchers.Default) {
                println("Default Thread: ${Thread.currentThread().name}")
            }
            launch(Dispatchers.Unconfined) {
                println("Unconfined Thread: ${Thread.currentThread().name}")
            }
        }


    }
}
