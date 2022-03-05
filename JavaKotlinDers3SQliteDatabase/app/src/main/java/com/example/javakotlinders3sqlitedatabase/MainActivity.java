package com.example.javakotlinders3sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            SQLiteDatabase database=this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            //varchar sqlite de string demektir.metinlere varchar diyoruz.
            //int ya da integer da yazabilirim farketmiyor.
            //sql kodlarını büyük harfle yazıyoruz.
            //primary key ile otomatik olarak artırmış oluyoruz.
            //id'yi belirtirken int olunca sıkıntı çıkıyor. ınteger yap!
 //           database.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY,name VARCHAR,age INT)");
 //           database.execSQL("INSERT INTO musicians(name,age) VALUES('merve',50)");
            //tek tek hücrelerin içinde geziyo verileri okuyo.
            Cursor cursor=database.rawQuery("SELECT * FROM musicians",null);
            //cursor'a hangi sütunlara gideceğini söylememiz gerekiyor.
            int nameIx=cursor.getColumnIndex("name");//name'in kaçıncı indexte olduğunu öğreniyoruz.
            int ageIx=cursor.getColumnIndex("age");

            //cursor ilerleyebildiği kadar ilerlesin
            while (cursor.moveToNext()){
                System.out.println("Name: "+cursor.getString(nameIx));
                System.out.println("Age: "+cursor.getInt(ageIx));
            }
            cursor.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
