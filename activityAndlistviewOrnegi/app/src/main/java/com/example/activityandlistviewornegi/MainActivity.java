package com.example.activityandlistviewornegi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<modelSinifi> list;  //model sınıfım tipinde bir liste oluşturmam gerekiyor
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modelSinifi m1 = new modelSinifi("merve", "coban");
        modelSinifi m2 = new modelSinifi("melike", "coban");
        modelSinifi m3 = new modelSinifi("furkan", "basaran");
        modelSinifi m4 = new modelSinifi("oguzhan", "koc");
        list=new ArrayList<>();
        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);
        listView=(ListView) findViewById(R.id.listView);
        //şimdi de adapter nesnesi oluşturmam gerekiyor.
        adapterSinifi adp=new adapterSinifi(list,this,this);
        listView.setAdapter(adp);

    }
}
