package com.example.javakotlinders9parslearning;

import android.app.Application;

import com.parse.Parse;

public class ParseStarterClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //hatalarımızı logda görmek için ayarlama yapıyoruz.
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("C0nELLabRJiIi7io5osPfeZuTVZUeR8sHQkO4VDt")
                .clientKey("BH84GoURrtmKGnuXalSKASIMqR2laIgU7lH15oLQ")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
