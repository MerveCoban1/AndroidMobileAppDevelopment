package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;

@SuppressLint("Registered")
public class Intro extends Activity {
    Intent m;
    Thread introThread;
    MediaPlayer mp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mp=MediaPlayer.create(Intro.this,R.raw.intromusic);
        mp.start();
        introThread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    m=new Intent("android.intent.action.MENU");
                    startActivity(m);
                }
            }
        };
        introThread.start();
    }
}
