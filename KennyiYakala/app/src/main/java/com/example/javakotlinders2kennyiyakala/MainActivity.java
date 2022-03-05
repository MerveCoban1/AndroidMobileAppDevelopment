package com.example.javakotlinders2kennyiyakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView zaman, skor, enIyiSkor;
    ImageView imageView, imageView2, imageView3, imageView4, imageView5, imageView6;
    ImageView imageView7, imageView8, imageView9;
    int score;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //birden fazla resim koyacağız.Sıralayacağız Bunun için düzgün sıralanması için
        //gridlayout kullanacağız.Sadece viewlara row ve column veriyoruz.
        tanimla();
        countDownTimer();
    }

    public void tanimla() {
        sharedPreferences = this.getSharedPreferences("com.example.javakotlinders2kennyiyakala", Context.MODE_PRIVATE);

        score = 0;
        zaman = (TextView) findViewById(R.id.zaman);
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
        imageArray = new ImageView[]{imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9};

        String name = sharedPreferences.getString("skor", "name");
        enIyiSkor.setText("Best Score: " + name);

        hideImage();

    }

    //bu metodun herhangi bir view tarafından çağırılacağını bildirmek için View view parametresini ekliyoruz.
    public void increaseScore(View view) {
        //her imageView'ımıza onClick metodu tanımladık hepsininki de aynı
        //ismi de increaseScore. Bu isimle tıpatıp aynı metodumuzu oluşturduk.
        //bu metot bize her bir imageview'a basıldığında ne yapılacağını söyler.
        score++;
        skor.setText("Skor: " + score);
    }

    public void countDownTimer() {
        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                zaman.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                zaman.setText("Zaman Bitti");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE); //görünmüyor yani basılmıyor da
                }
                String deneme = sharedPreferences.getString("skor", "deneme");
                int deme = Integer.parseInt(deneme);
                if (score > deme) {
                    sharedPreferences.edit().putString("skor", "" + score).apply();
                }

                final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //kendi aktivitemizi yeniden başlatıyoruz.
                        Intent intent = getIntent();
                        finish(); //güncel aktiviteyi bitirip tekrar başlatacak.Yormamış olacağız.
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();

            }
        }.start();
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
                }
                //this bir üstünde bulunduğu yeri işaret ediyor.Runnable'ı çalıştır diyoruz.
                handler.postDelayed(this, 500);
            }
        };
        handler.post(runnable);
    }


}
