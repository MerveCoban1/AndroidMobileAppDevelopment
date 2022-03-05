package com.example.otogaleri;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Veritabani extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config=new RealmConfiguration.Builder().name("otogalerimmm").build();
        Realm.setDefaultConfiguration(config);

    }
}
