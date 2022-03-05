package com.example.javakotlinders2kennyiyakala;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView can, skor, enIyiSkor;
    ImageView imageView, imageView2, imageView3, imageView4, imageView5, imageView6;
    ImageView imageView7, imageView8, imageView9;
    int score;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    SharedPreferences sharedPreferences;
    int basti = 0;
    Button retry;
    String yarismaa;
    int deme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //birden fazla resim koyacağız.Sıralayacağız Bunun için düzgün sıralanması için
        //gridlayout kullanacağız.Sadece viewlara row ve column veriyoruz.
        tanimla();
        hideImage();
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void tanimla() {
        sharedPreferences = this.getSharedPreferences("com.example.javakotlinders2kennyiyakala", Context.MODE_PRIVATE);

        score = 0;
        can = (TextView) findViewById(R.id.can);
        skor = (TextView) findViewById(R.id.skor);
        enIyiSkor = (TextView) findViewById(R.id.enIyiSkor);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        imageView7 = (ImageView) findViewById(R.id.imageView7);
        imageView8 = (ImageView) findViewById(R.id.imageView8);
        imageView9 = (ImageView) findViewById(R.id.imageView9);
        retry = (Button) findViewById(R.id.retry);
        imageArray = new ImageView[]{imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9};

        yarismaa = sharedPreferences.getString("skor", "0");
        enIyiSkor.setText("Best Score: " + yarismaa);

    }

    public void hideImage() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE); //görünmüyor yani basılmıyor da
                }
                Random random = new Random();
                int i = random.nextInt(9);//0 ile 8 arasında bir sayı getirecek bana.
                int x = random.nextInt(3);
                imageArray[i].setVisibility(View.VISIBLE);
                if (x == 1) {
                    imageArray[i].setImageResource(R.drawable.kotucocuk);
                    imageArray[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            basti++;
                            can.setText("Kalan Can: " + (4 - basti));
                        }
                    });
                } else {
                    imageArray[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            score++;
                            skor.setText("Skor: " + score);
                        }
                    });
                }
                if (basti == 4) {
                    handler.removeCallbacks(runnable);
                    deme = Integer.parseInt(yarismaa);
                    if (score > deme) {
                        Toast.makeText(getApplicationContext(),"girdi",Toast.LENGTH_LONG).show();
                        String yarisma = String.valueOf(score);
                        sharedPreferences.edit().putString("skor", yarisma);
                        enIyiSkor.setText("Best Score: " + yarisma);
                    }

                    for (ImageView image : imageArray) {
                        image.setVisibility(View.INVISIBLE); //görünmüyor yani basılmıyor da
                    }


                    handler.removeCallbacks(runnable);
                }
                //this bir üstünde bulunduğu yeri işaret ediyor.Runnable'ı çalıştır diyoruz.
                handler.postDelayed(this, 500);
            }
        };
        handler.post(runnable);


    }


}
