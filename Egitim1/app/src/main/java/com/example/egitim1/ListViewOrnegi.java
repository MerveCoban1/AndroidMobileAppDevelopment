package com.example.egitim1;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListViewOrnegi extends AppCompatActivity {
    List<KullaniciModeli> kullaniciList;
    KullaniciListAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        listView=findViewById(R.id.listview);
        ListeDoldur();
    }
    public void ListeDoldur(){
        kullaniciList=new ArrayList<>();
        KullaniciModeli k1=new KullaniciModeli("merve","coban","21","gs");
        KullaniciModeli k2=new KullaniciModeli("melike","coban","21","gs");
        KullaniciModeli k3=new KullaniciModeli("sema","coban","21","gs");
        KullaniciModeli k4=new KullaniciModeli("seyda","coban","21","gs");
        kullaniciList.add(k1);
        kullaniciList.add(k2);
        kullaniciList.add(k3);
        kullaniciList.add(k4);
        adapter=new KullaniciListAdapter(kullaniciList,this);
        listView.setAdapter(adapter);
    }
}
