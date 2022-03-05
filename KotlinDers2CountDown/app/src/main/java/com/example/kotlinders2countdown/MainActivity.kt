package com.example.kotlinders2countdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //belirlediğimiz herhangi bir periyotta verdiğimiz işlemleri yapıyor
        //geriye sayma sayacıdır.
        //10 saniye çalışacak
        //1 saniyede bir de işlem yaptıracağız
        object :CountDownTimer(10000,1000){
            override fun onFinish() {
                //işlem bitince ne olacak
                textView.text="Left: 0"
            }

            override fun onTick(millisUntilFinished: Long) {
                //her belirtilen saniyede ne olacak
                //millisUntilFinished bitmeye kalan saniye demek
                textView.text="Left: ${millisUntilFinished/1000}"
            }

        }.start()
        //countDownTimer abstract bir sınıf olduğundan nesnesi direk oluşturulamaz
        //bir değişken oluştur  countDownTimer'ın özelliklerini kullanan bir nesne olucak


    }
}
