package com.example.kotlinders1storingdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //textView2 de göster
        val intent=intent
        val ageGived=intent.getIntExtra("age",0)
        textView2.text=""+ageGived
        toastMessage()
    }
    fun toastMessage(){
        Toast.makeText(applicationContext,"merhabaa",Toast.LENGTH_LONG).show()
    }

    fun alert(view: View){
        //basıldığında. Alert açılacak
        val alert=AlertDialog.Builder(this)
        alert.setTitle("diggat")
        alert.setMessage("diggat et bebegim")
        alert.setPositiveButton("ederim"){dialog, which ->
            //ederime basılınca ne olacak
            Toast.makeText(applicationContext,"aferim",Toast.LENGTH_LONG).show()
        }
        //{} bu ve içindekiler lambda gösterimi
        alert.setNegativeButton("etmem"){dialog, which ->

        }
        alert.show()


    }
}
