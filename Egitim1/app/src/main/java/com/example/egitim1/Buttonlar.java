package com.example.egitim1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Buttonlar extends AppCompatActivity {
    Button buton1;
    TextView text1;
    EditText text2;
    Button hesaplabutonu;
    TextView sonuc;
    String deger;
    ImageView resim;
    Button resimbutonu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.butonlar);
        tanimla();
        islevVer();
        text1.setText("merve");
        Toast.makeText(this,"bu sekilde bi bildirim mesaji olusturabiliriz",Toast.LENGTH_LONG).show();
        hesaplabutonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deger = text2.getText().toString();
                int faktoriyel2 = Integer.parseInt(deger);
                faktoriyel2 = faktoriyel(faktoriyel2);
                sonuc.setText("sonucunuz:" + faktoriyel2);
            }
        });
        resimbutonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rnd=(int)(Math.random()*5+1);  //1 ile 5 arası random bir sayı üretecek.
                degistir(rnd);
                Toast.makeText(getApplicationContext(),""+rnd+"inci resim gösterildi",Toast.LENGTH_LONG).show();
                //this ile getApplica... aynı anlama geliyo ama burda değişkeni yazarken kabul etmedi this'i
                //ya da
                Toast.makeText(getApplicationContext(),String.valueOf(rnd)+"numarali resim degistirildi",Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void tanimla() {
        buton1 = (Button) findViewById(R.id.metinsiz);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (EditText) findViewById(R.id.text2);
        sonuc = (TextView) findViewById(R.id.sonuc);
        hesaplabutonu = (Button) findViewById(R.id.hesaplabutonu);
        resim=(ImageView)findViewById(R.id.resmim);
        resimbutonu=(Button)findViewById(R.id.resimbutonu);
    }
    private void degistir(int sayi){
        if(sayi==1){
            resim.setImageResource(R.drawable.resim2);  //resmimizin kaynağını değiştiriyoruz
        }else{
            resim.setImageResource(R.drawable.resim3);
        }
    }
    private void islevVer() {
        buton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("butona tiklandı bu sadece consol ekranında görünüyo loga bakarsan görürsün yoksa uygulamada görünmüyor");
            }
        });
    }

    int faktoriyel(int sayi) {
        int sonuc = 1;
        int i;
        for (i = sayi; i > 1; i--) {
            sonuc = sonuc * i;
        }
        return i;
    }
}
