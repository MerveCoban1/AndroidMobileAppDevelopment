package com.example.kotlinders4kennyyiyakala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler: Handler=Handler()
    var runnable: Runnable= Runnable {  }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tanimla()
        kennyyiSakla()
        countDownTimer()

    }

    fun tanimla() {
        imageArray.add(imageView)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        imageArray.add(imageView10)
        imageArray.add(imageView11)
        imageArray.add(imageView12)
        imageArray.add(imageView13)
        imageArray.add(imageView14)
        imageArray.add(imageView15)
        imageArray.add(imageView16)
    }

    fun tikla(view: View) {
        score++
        skorTextView.text = "Score: " + score
    }

    fun countDownTimer() {
        object : CountDownTimer(15000, 1000) {
            override fun onFinish() {
                zamanTextView.text = "Zaman: 0"
                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                //uyarı mesajı oluşturacağız
                //Alert
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Tekrar Oynamak İstiyor musunuz?")
                alert.setPositiveButton("Evet") { dialog, which ->
                    //oyun tekrar açılacak
                    //herhangi bir aktiviteyi tekrar başlatmak için bir yol var
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("Hayir") { dialog, which ->
                    Toast.makeText(this@MainActivity, "Oyun Bitti", Toast.LENGTH_LONG).show()
                }
                alert.show()
            }

            override fun onTick(millisUntilFinished: Long) {
                zamanTextView.text = "Zaman: " + (millisUntilFinished / 1000)

            }

        }.start()
    }

    fun kennyyiSakla() {
        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                val random = java.util.Random()
                val randomIndex = random.nextInt(17)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable, 500)
            }
        }
        handler.post(runnable)
    }

}
