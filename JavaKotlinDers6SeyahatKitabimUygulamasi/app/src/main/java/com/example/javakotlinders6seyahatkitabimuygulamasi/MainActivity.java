package com.example.javakotlinders6seyahatkitabimuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    static ArrayList<String> names;
    static ArrayList<LatLng> locations;
    static ArrayAdapter arrayAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //burada menümüzü bağlıyoruz.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_place, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //menüye tıklandığında ne olacağını belirliyoruz.
        if (item.getItemId() == R.id.add_place_item) {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            intent.putExtra("info","new");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        try {
            MapsActivity.database = this.openOrCreateDatabase("Places", MODE_PRIVATE, null);
            Cursor cursor = MapsActivity.database.rawQuery("SELECT * FROM places", null);

            int nameIx = cursor.getColumnIndex("name");
            int latitudeIx = cursor.getColumnIndex("latitude");
            int longitudeIx = cursor.getColumnIndex("longitude");

            while (cursor.moveToNext()) {
                String nameFromDb = cursor.getString(nameIx);
                String latitudeFromDb = cursor.getString(latitudeIx);
                String longitudeFromDb = cursor.getString(longitudeIx);

                names.add(nameFromDb);

                Double l1 = Double.parseDouble(latitudeFromDb);
                Double l2 = Double.parseDouble(longitudeFromDb);

                LatLng locationFromDb = new LatLng(l1, l2);
                locations.add(locationFromDb);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(arrayAdapter);

        action();
    }

    public void tanimla() {
        listView = findViewById(R.id.listView);
        names = new ArrayList<String>();
        locations = new ArrayList<LatLng>();
    }

    public void action() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("position",position);
                //listViewdan tıkladığımızı da belirtmeliyiz.
                intent.putExtra("info","old");
                startActivity(intent);
            }
        });
    }

}
