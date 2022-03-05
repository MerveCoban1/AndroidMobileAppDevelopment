package com.example.javakotlinders13fragmentler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

/*
*Eğer içerisinde tek bir görünüm olacaksa FrameLayout kullanabiliriz.
*Normalde fragment için new fragment diyebiliyoruz ve xml dosyası da geliyor aynı activity gibi
Ama biz sıfırdan kendimiz oluşturacağız o yüzden sınıf oluşturduk ve bir xml dosyası oluşturduk.
*
* */
public class MainActivity extends AppCompatActivity {
    Button button,button2;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        action();
    }

    public void tanimla(){
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        frameLayout=findViewById(R.id.frameLayout);
    }

    public void action(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                FirstFragment firstFragment=new FirstFragment();
                //add kullanınca fragmentler kaybolmadan birbirinin üztüne çıkıyor.
                //fragmentTransaction.add(R.id.frameLayout,firstFragment).commit();
                fragmentTransaction.replace(R.id.frameLayout,firstFragment).commit();


            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                SecondFragment secondFragment=new SecondFragment();
                fragmentTransaction.replace(R.id.frameLayout,secondFragment).commit();
            }
        });
    }
}
