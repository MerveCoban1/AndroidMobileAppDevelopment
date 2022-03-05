package com.example.picassokutuphanesi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        resimCek();
    }
    public void tanimla(){
        img=(ImageView) findViewById(R.id.img);
    }
    public void resimCek(){
        Picasso.with(getApplicationContext()).load("https://i.hizliresim.com/xYggpk.png").into(img);
    }
}
