package com.example.kotlinders6sqlitedatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            val myDatabase=this.openOrCreateDatabase("Musicians", Context.MODE_PRIVATE,null)
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY,name VARCHAR,age INT)")
            //veriEkle(myDatabase)
            //veriCek(myDatabase)
            //filtreliVeriCek(myDatabase)
            //guncelle(myDatabase)
            //sil(myDatabase)

        }catch (e:Exception){
            e.printStackTrace()
        }


    }

    fun veriEkle(myDatabase:SQLiteDatabase){
        //veri ekleyelim veritabanımıza
         myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES('merve',22)")
         myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES('ahmet',25)")
         myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES('sevda',12)")
    }

    fun veriCek(myDatabase:SQLiteDatabase){
        //verileri çekelim
        val cursor=myDatabase.rawQuery("SELECT * FROM musicians",null)  //filtre istemediğimiz için null dedik
        val nameIx=cursor.getColumnIndex("name")
        val ageIx=cursor.getColumnIndex("age")
        val idIx=cursor.getColumnIndex("id")
        while (cursor.moveToNext()){
            println("name: "+cursor.getString(nameIx))
            println("age: "+cursor.getInt(ageIx))
            println("id: "+cursor.getInt(idIx))
        }
        cursor.close()
    }

    fun filtreliVeriCek(myDatabase:SQLiteDatabase){
        //filtreleme yapalım
        val cursor2=myDatabase.rawQuery("SELECT * FROM musicians WHERE name='merve'",null)
        //val cursor2=myDatabase.rawQuery("SELECT * FROM musicians WHERE name LIKE '%s'",null)
        //sonu s ile bitenleri sıralar

        val nameIx2=cursor2.getColumnIndex("name")
        val ageIx2=cursor2.getColumnIndex("age")
        val idIx2=cursor2.getColumnIndex("id")
        while (cursor2.moveToNext()){
            println("name: "+cursor2.getString(nameIx2))
            println("age: "+cursor2.getInt(ageIx2))
            println("id: "+cursor2.getInt(idIx2))
        }
        cursor2.close()
    }

    fun guncelle(myDatabase:SQLiteDatabase){
        myDatabase.execSQL("UPDATE musicians SET age=61 WHERE name='merve'")
    }

    fun sil(myDatabase:SQLiteDatabase){
        myDatabase.execSQL("DELETE FROM musicians  WHERE name='merve'")
    }
}
