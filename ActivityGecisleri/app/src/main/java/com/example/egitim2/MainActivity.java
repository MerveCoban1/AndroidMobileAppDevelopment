package com.example.egitim2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText kullaniciAdi;
    EditText kullaniciSifre;
    RadioGroup cinsiyet;
    Button gonder;
    String kAdi;
    String kSifre;
    String kCinsiyet; //diğer activity ye göndereceğiz o yüzden değişkenlerde tutuyoruz aldıklarımızı
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        tiklaGec();
    }
    public void tanimla(){
        kullaniciAdi=findViewById(R.id.kullaniciAdi);
        kullaniciSifre=findViewById(R.id.kullaniciSifre);
        cinsiyet=findViewById(R.id.cinsiyet);
        gonder=findViewById(R.id.gonderButonu);
    }
    public void degerAl(){
        kAdi=kullaniciAdi.getText().toString();
        kSifre=kullaniciSifre.getText().toString();
        kCinsiyet=((RadioButton)findViewById(cinsiyet.getCheckedRadioButtonId())).getText().toString();
    }
    public void tiklaGec(){
        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                degerAl();
                //Log.i("degerler",""+kAdi+""+kSifre+""+kCinsiyet); //logcat e degerler yaz bu tagi altında butona basılınca bubları ekrana yazdıracağız
                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                intent.putExtra("kAdi",kAdi);
                intent.putExtra("kSifre",kSifre);
                intent.putExtra("kCinsiyet",kCinsiyet); //yeşil yazılı string diğer activity de bu değerleri alacağımız değişken adımız olacak.
                startActivity(intent);



            }
        });
    }
}
