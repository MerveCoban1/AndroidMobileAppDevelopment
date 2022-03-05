package com.example.javakotlinders15navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * developer.android.com adresine gidiyoruz.
  Jetpack'e giriyoruz. Jetpack: Android için geliştirilen bir kütüphane bütünü
 * navigation: görünümler arası gezmeyi kolaylaştıran bir kütüphane. Özellikle fragment
  kullanırken navigation da kullanmak iyi olur.
 * gradle:app'e ve gradle:project'e eklemem gerekenleri ekledim. Sitede de yazıyor zaten
 *
 * navigasyon grafiği oluşturmak için: res- sağ tık- new -android resource file diyoruz.
 resource type'ını navigation seçmemiz lazım oluştururken. !!
 *navigasyonumu oluşturduk iki fragment arasındaki sonra maine geliyoruz
 xml_mainde tek bir görünüm olacak o da navhost !!! onu xmle atıyoruz ve bitiyor.
 *şimdi kodlarını yazmamız gerekiyor. Build-Rebuild Project diyoruz bizim için hazır sınıf oluşturuyor.
 directions sınıfları falan oluştu. First Fragmente gidelim butona basılında ne olacağını yazdık
 *Şimdi veri aktaracağız bunun için argümanları göreceğiz.
 *mesela 1.den 2.ye veri yollayacağız diyelim 2. fragmente basıyoruz my_graph'da
 ve argümanlar kısmında +'ya basıyoruz age adında integer bir argüman tanımlıyoruz.
 sonra tekrar build rebuild yapıyoruz.!!!


 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
