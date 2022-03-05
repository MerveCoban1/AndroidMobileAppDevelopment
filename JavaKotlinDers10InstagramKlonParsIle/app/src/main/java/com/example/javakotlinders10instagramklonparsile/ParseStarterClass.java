package com.example.javakotlinders10instagramklonparsile;

import android.app.Application;

import com.parse.Parse;

public class ParseStarterClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //hatalarımızı logda görmek için ayarlama yapıyoruz.
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("jhFkB9wnLTeemzDqLMajQKr8U6uB2MAQh2ESUfDg")
                .clientKey("Tu4b9Ktrwu23E4NlL6IqNFaN4OhTFznBx0c0199I")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
