package com.example.egitim1;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class viewlar extends AppCompatActivity {
    WebView vb;
    CheckBox java;
    CheckBox php;
    RadioButton kiz;
    RadioButton erkek;
    RadioGroup grupradyo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewornekleri);
        tanimla();
        //goster();
        //gosterHtml();
        checkboxOrnegi();
        radioButtonOrnegi();
    }
    public void tanimla(){
        vb=(WebView)findViewById(R.id.webview);
        php=findViewById(R.id.php);
        java=findViewById(R.id.java);
        kiz=findViewById(R.id.kiz);
        erkek=findViewById(R.id.erkek);
        grupradyo=findViewById(R.id.grupradyo);
    }
    public void goster(){
        //web sitesindeki javascript kodlarının çalıştırılmasını istiyorsak eğer bu tanımlamayı yapmak zorundayız
        vb.getSettings().setJavaScriptEnabled(true);
        vb.loadUrl("https://www.google.com.tr");
        //eğerki bir uygulamada internet kullanıyorsak internet iznini almamız gerekiyor.
        //konumdan yararlanacaksak eğer o zamanda konum iznini almamız gerekiyor.
    }
    public void gosterHtml(){
        String htmlKodu="<html><head>Basligim</head><body>merhaba</body></html>";
        vb.loadData(htmlKodu,"text/html","UTF-8");
        //kendi yazdığım html kodlarını bu sekilde webviewde çalıştırıyoruz
    }
    public void checkboxOrnegi(){
        php.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(php.isChecked()){
                    Toast.makeText(getApplicationContext(),"php secildi",Toast.LENGTH_LONG).show();//this yerine bunu yazmak daha sağlıklı
                }else{
                    Toast.makeText(getApplicationContext(),php.getText()+"secenegi iptal edildi",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    public void radioButtonOrnegi(){
        kiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),kiz.getText()+"secildi",Toast.LENGTH_LONG).show();
            }
        });
        erkek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),erkek.getText()+"secildi",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void idAl(){
   //bu sanırım olmuyor bçyle tek başına butonla beraber kullandı hoca bunu
                //bir sürü buton olduğunu düşün hepsine setOnClickListener yapmaya gerek kalmıyor bu grup ile
                int gelenId=grupradyo.getCheckedRadioButtonId();//hangi radyo butonuna basıldıysa onun idsinin alıyoruz
                resimdegis(gelenId);


    }
    public void resimdegis(int id){
        if(id==R.id.erkek){
            //toast mesajını burada verebilirsin
            //img.setImageResource(R.drawable.bir);//resmi değiştirdik
            //img ImageView dan bir degisken
        }else if(id==R.id.kiz){

        }
        //eger secilip de butona basılınca değişsin istiyorsak
        //buton.setOnclickListener...............
        //onclick()
        //içine gelen id yi burada alıyoruz.
        //ve resimdegis fonksiyonunu burada değişiyoruz.
    }
}
