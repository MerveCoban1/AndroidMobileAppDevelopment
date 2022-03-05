package com.example.kotlinders1storingdata

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //var sharedPreferences: SharedPreferences?=null
    //ya böyle tanımlıycaz ya da
    lateinit var sharedPreferences: SharedPreferences
    var ageFromPreferences: Int? = null
    var age: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //sharedPreferences
        //name: veritabanı adımızdır genelde açtığımız package name'i veririz.
        //mode: oluşturulan dosya sadece kendi uygulaması içinde ulaşılsın istediğimiz için modumuz private
        sharedPreferences = this.getSharedPreferences(
            "com.example.kotlinders1storingdata",
            Context.MODE_PRIVATE
        )
        ageFromPreferences = sharedPreferences.getInt("age", -1)
        if (ageFromPreferences == -1) {
            textView.text = "Your Age: "
        } else {
            textView.text = "Your Age: $ageFromPreferences"
        }
        age = 71
    }

    fun delete(view: View) {
        ageFromPreferences = sharedPreferences.getInt("age", -1)
        if (ageFromPreferences != -1) {
            //kayıtlı veri var
            //edit kullanıyorsak apply da dememiz gerekiyor.
            sharedPreferences.edit().remove("age").apply()
            textView.text = "Your Age: "

        }
    }

    fun save(view: View) {

        val myAge = ageText.text.toString().toIntOrNull()
        if (myAge != null) {
            textView.text = "Your Age: $myAge"
            //ya da
            //textView.text="Your Age: "+myAge
            sharedPreferences.edit().putInt("age", myAge).apply()
        }
    }

    fun next(view: View) {

        val intent = Intent(applicationContext, Main2Activity::class.java)
        intent.putExtra("age", age)
        startActivity(intent)
    }
}
