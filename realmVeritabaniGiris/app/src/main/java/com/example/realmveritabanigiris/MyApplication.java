package com.example.realmveritabanigiris;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config =new  RealmConfiguration.Builder().name("ilkornegim").build();//bu name i her projede farklı verecekmişiz
        Realm.setDefaultConfiguration(config);
    }
}
