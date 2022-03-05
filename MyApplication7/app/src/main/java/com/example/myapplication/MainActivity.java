package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    //layout üzerindeki nesnelerin değişkenlerinin oluşturulması
    Button button4;
    EditText editText3;
    EditText editText5;
    EditText editText6;
    TextView textView2;
    int sonuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //butonlara işlevsellik kazandırmak
        //layout üzerindeki nesnelerin değişkenlere atanması burada yapılır
        //bu metotla nerden alınacağını belirttik.
        sonuc=0;
        button4= (Button) findViewById(R.id.button4); //cast işlemiyle bu fonksiyondan gelen bir view in button a dönüştürülmesinin sağlıyor.
        editText3= (EditText) findViewById(R.id.editText3);
        editText5= (EditText) findViewById(R.id.editText5);
        editText6= (EditText) findViewById(R.id.editText6);
        textView2= (TextView) findViewById(R.id.TextView2);
        //butonlara bastığımızda onların çalışmasını beklediğimşz onclicklistener lar olması gerek.
        button4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                // bu butona tıkladığımızda ne olmasını istiyorsak onu buraya yazıyoruz.
                sonuc++;
                textView2.setText("artirim yapildi:"+ sonuc);
                Toast bildirim=Toast.makeText(getApplicationContext(),"ekleme butonuna basildi",Toast.LENGTH_LONG);
                bildirim.show();
            }
        });
    }
}
