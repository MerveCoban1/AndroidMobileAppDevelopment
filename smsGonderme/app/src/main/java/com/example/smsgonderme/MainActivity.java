package com.example.smsgonderme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<modelSinifim> list;
    ListView liste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //uygulamayı kendi cihazımıza yüklediğimizde gerçekleşecek bu uygulama
        //şuan sanal makinede çalıştığımız için mesaj göndermeyecek
        ListeDoldur();
        tanimla();
    }
    public void ListeDoldur(){
        list=new ArrayList<>();
        modelSinifim m1= new modelSinifim("merve","05346402012");
        modelSinifim m2=new modelSinifim("ayse","7896423");
        list.add(m1);
        list.add(m2);
    }
    public void tanimla(){
        liste=(ListView) findViewById(R.id.mesajList);
        //şimdi bir adapter oluşturmalıyız.
        adapterSinifim adapter=new adapterSinifim(list,this,this);
        liste.setAdapter(adapter);
    }
}
