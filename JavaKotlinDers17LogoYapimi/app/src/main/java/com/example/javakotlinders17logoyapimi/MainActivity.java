package com.example.javakotlinders17logoyapimi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * drawable-new-image asset-launcher icons(adapter and legancy) yazanın seçili olduğundan emin ol
 * istersen ordan hemen backgroundunu ve logosunu seçebilirsin ama kendin yapmak istiyorsan
 * adobe xd'den logonu tasarla.-drawable-new-image asset-foregroundda image seçili pathden resmi seç
 * yaptığın adobede,boyutunu küçült arka planı geçmesin öndeki resmin backgroundda da aynını yap
 * adobede export ederken artboardı export etme içindekileri grupla onu export et
 * en son isimleri değişmeden next de ki eski default logonun üstüne yazsın
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
