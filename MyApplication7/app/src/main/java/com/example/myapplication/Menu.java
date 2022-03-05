package com.example.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class Menu extends ListActivity {
    String siniflar[]={"MainActivity","EgitimListesi","TextOnOff"}; //SINIF İSİMLERİ
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //diziyi işleyebilmek için bir arrayadaptöre ihtiyacımız var
        ArrayAdapter menuAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,siniflar);
        //siniflardan buraya veri geleceğini belirtiyoruz.
        setListAdapter(menuAdapter);   //sınıflarımızı ilişkilendirmiş olduk
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //tıklandığı zaman neler olacağını belirtelim.listeden bişey seçildiği zaman bize int değer vericek
        String selectedListItem=siniflar[position];
        //hangi classın seçildiğini bulmak için selection işlemi yapılmalı
        try {
            Class selectedClass=Class.forName("com.example.myapplication."+selectedListItem);
            //burdan hangi sınıfın seçildiğini bulucaz ve buna göre bir intent oluşturacağız
            Intent ie=new Intent(this,selectedClass);
            startActivity(ie);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
