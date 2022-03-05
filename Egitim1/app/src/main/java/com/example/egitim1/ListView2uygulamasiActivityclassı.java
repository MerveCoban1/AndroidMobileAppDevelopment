package com.example.egitim1;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListView2uygulamasiActivityclassı extends AppCompatActivity {
    ListView listView;
    List<mesajModel> liste;
    uygulama2ninadapteri adp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewornek2uygulamasi);
        listView=findViewById(R.id.listview22);
        liste=new ArrayList<>();
        mesajModel m1=new mesajModel("merhaba nasilsin","merve",R.drawable.indir);
        mesajModel m2=new mesajModel("beni ara","reyhan",R.drawable.indir);
        mesajModel m3=new mesajModel("seni bekliyorum","sema",R.drawable.indir);
        liste.add(m1);
        liste.add(m2);
        liste.add(m3);
        adp=new uygulama2ninadapteri(liste,this);
        listView.setAdapter(adp);
    }
}
