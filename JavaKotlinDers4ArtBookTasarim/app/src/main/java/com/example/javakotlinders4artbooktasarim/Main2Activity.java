package com.example.javakotlinders4artbooktasarim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> nameArray;
    ArrayList<Integer> idArray;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tanimla();
        getData();
        action();
    }

    public void tanimla() {
        nameArray = new ArrayList<>();
        idArray = new ArrayList<>();
        listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter(Main2Activity.this, android.R.layout.simple_list_item_1, nameArray);
        listView.setAdapter(arrayAdapter);
    }

    public void getData() {
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Arts", MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM arts", null);
            int nameIx = cursor.getColumnIndex("artname");
            int idIx = cursor.getColumnIndex("id");
            while (cursor.moveToNext()) {
                nameArray.add(cursor.getString(nameIx));
                idArray.add(cursor.getInt(idIx));
            }
            arrayAdapter.notifyDataSetChanged();//yeni veri ekldiğinde güncellensin diye
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void action(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                intent.putExtra("artId",idArray.get(position));
                intent.putExtra("info","old");

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //hangi menüyü göstericez bu aktivitede onu belirliyoruz.
        //not:bir xml yaptığımızada onu aktivity içersinde göstermek için inflater kullanıyoruz.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_art, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //kullanıcı herhangi bir item'ı seçince ne olacağını belirliyoruz.
        if (item.getItemId() == R.id.add_art_item) {
            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
            intent.putExtra("info","new");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
