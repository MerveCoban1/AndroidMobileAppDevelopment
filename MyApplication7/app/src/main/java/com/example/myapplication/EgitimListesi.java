package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class EgitimListesi extends Activity {
    //eğitim listesi tanımlayacağız
    String[] egitimler ={"php seti","ticaret seti","dreamweaver","laravel"};
    //List View tanımlayacağız //bunun için layout oluşturuyoruz.
    ListView egitimListesi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.egitim_listesi);
        egitimListesi=(ListView)findViewById(R.id.ListViewEgitim);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,egitimler);
        egitimListesi.setAdapter(adapter);
        //şimdi click event lerini oluşturacağız.

        AdapterView.OnItemClickListener listener=new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //bir tane diyalog penceresi oluşturalım
                final AlertDialog.Builder dialog=new AlertDialog.Builder(EgitimListesi.this);
                dialog.setTitle("egitim setinizin adi : ");
                dialog.setMessage(egitimler[position]);
                dialog.setCancelable(false);
                dialog.setPositiveButton("cik", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.create().show();
            }
        };
        egitimListesi.setOnItemClickListener(listener);
    }
}
