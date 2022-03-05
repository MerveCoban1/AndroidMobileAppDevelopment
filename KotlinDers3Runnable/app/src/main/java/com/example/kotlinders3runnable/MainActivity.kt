package com.example.kotlinders3runnable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var number=0
    //runnable bir interface handler bir sınıf
    var runnable: Runnable= Runnable {  }
    var handler: Handler=Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //runnable'ı kullanmak için hem runnable hem de handler objesine ihtiyacım var
    }
    fun start(view: View){
        number=0
        runnable=object :Runnable{
            override fun run() {
                //içine ne yazarsak arka planda bizim için çalıştıracak
                number++
                timeTextView.text="Time: $number"
                //şimdi kaç saniyede bir yapacağımızı handler ile belirtelim
                handler.postDelayed(this,1000)
            }
        }
        handler.post(runnable)
    }

    fun stop(view: View){
        handler.removeCallbacks(runnable)
        number=0
        timeTextView.text="Time: 0"

    }
}
