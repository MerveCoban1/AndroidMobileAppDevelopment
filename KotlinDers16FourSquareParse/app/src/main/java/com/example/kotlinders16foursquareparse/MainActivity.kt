package com.example.kotlinders16foursquareparse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser
import kotlinx.android.synthetic.main.activity_main.*

/**
 * gradle eklemelerini yap
 * parse ile sunucumuzu bağlamak için bir sınıf oluşturacağız
 *
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //veriKaydet()
        //veriOku()
        //filtreliVeriCek()
        kontrol()
        action()

    }

    fun veriKaydet() {
        //parse'a veri kaydetmek için
        val parseObj =
            ParseObject("Fruits")  //fruits diye bir sınıf oluşturduk(varsa bi daha oluşturmuyo ekleme yapıyo)
        parseObj.put("name", "muz")  //bu verileri ekledik
        parseObj.put("calories", 100)
        //callback'li bişeyi seçmek demek yaptığı işlemin sonucunu da verecek demektir
        parseObj.saveInBackground { e ->
            if (e != null) {
                Toast.makeText(applicationContext, e.localizedMessage.toString(), Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(applicationContext, "veri kaydoldu", Toast.LENGTH_LONG).show()

            }
        }
    }

    fun veriOku() {
        //parse'dan veri çekelim
        val query = ParseQuery.getQuery<ParseObject>("Fruits")
        query.findInBackground { objects, e ->
            if (e != null) {
                Toast.makeText(applicationContext, e.localizedMessage.toString(), Toast.LENGTH_LONG)
                    .show()
            } else {
                if (objects.size > 0) {
                    for (parseObject in objects) {
                        val name = parseObject.get("name") as String
                        val calories = parseObject.get("calories") as Int
                        println("name: $name")
                        println("calories: $calories")
                    }
                }
            }
        }

    }

    fun filtreliVeriCek() {
        //filtreli bir şekilde veri çekmek istersem eğer:
        val query2 = ParseQuery.getQuery<ParseObject>("Fruits")
        query2.whereEqualTo("name", "muz").findInBackground { objects, e ->
            for (parseObject in objects) {
                val name = parseObject.get("name") as String
                val calories = parseObject.get("calories") as Int
                println("name: $name")
                println("calories: $calories")
            }
        }

        val query3 = ParseQuery.getQuery<ParseObject>("Fruits")
        query3.whereLessThan("calories", 300)//kalorisi 300den az olanları getir dedik
        query3.findInBackground { objects, e ->
            for (parseObject in objects) {
                val name = parseObject.get("name") as String
                val calories = parseObject.get("calories") as Int
                println("name: $name")
                println("calories: $calories")
            }
        }
    }

    fun action(){
        signUpButton.setOnClickListener {
            val user=ParseUser()
            user.username=userNameEditText.text.toString()
            user.setPassword(passwordEditText.text.toString())
            user.signUpInBackground {e ->
                if (e!=null){
                    Toast.makeText(applicationContext, e.localizedMessage.toString(), Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(applicationContext, "User Created", Toast.LENGTH_LONG).show()
                    val intent=Intent(applicationContext,LocationsActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

        }

        signInButton.setOnClickListener {
            ParseUser.logInInBackground(userNameEditText.text.toString(),passwordEditText.text.toString()){user, e ->
                if (e!=null){
                    Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(applicationContext, "Hosgeldin: ${user.username}", Toast.LENGTH_LONG).show()
                    val intent=Intent(applicationContext,LocationsActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    fun kontrol(){
        val user=ParseUser.getCurrentUser()
        if(user.username!=null){
            val intent=Intent(applicationContext,LocationsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
