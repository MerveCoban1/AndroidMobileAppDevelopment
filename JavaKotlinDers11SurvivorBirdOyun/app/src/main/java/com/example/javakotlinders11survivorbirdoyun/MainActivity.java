package com.example.javakotlinders11survivorbirdoyun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/*Oyun yazmamız için bize yardımcı olacak olan kütüphane libGdx.
internet sayfasına girip download setup app diyoruz.
*name: Survivor Bird
*package: com.example.survivorbird
*Game class: Survivor Bird
*destination: C:\Users\MERVE\AndroidStudioProjects\SurvivorBird
* android sd: tools sdk manager dan sdk yolunu bulabilirsin
*sub project: android
*sonra generate diyip build successful olmasını bekliyoruz.
*ve android studioda yeni projemizi açıyoruz.
*
*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
