package com.example.javakotlinders1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView sayac,sayac2;
    Button basla,durdur;
    Runnable runnable; //bir işlemi birden fazla kez belirttiğimiz sayıda yapmamıza olanak sağlar.
    Handler handler; //runnable'ı ele aldığımız bir arayüz.
    int sayi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //countDownSayaci();
        runnableHandlerOrnek();


    }

    public void countDownSayaci() {
        //countDownTimer:geriye sayma sayacı.Oyunlarda falan kullanılabilir.
        sayac = (TextView) findViewById(R.id.sayac);
        //mesela belirli bir saniye içinde birşey yapmasını istiyorsak kullanıcının bunu kullanabiliriz
        //ilk yazdığım 10 saniye sayaç ne kadar saysın
        //ikinci yazdığım da 1 saniyede bir saysın demek oluyor.
        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                //her bir saniyede bir ne yapması gerektiğini bu metodun içine yazıyoruz.
                //bir her saniyede kaçıncı saniyede olduğumuzu yazmak istiyoruz
                sayac.setText("Left : " + millisUntilFinished / 1000); //bölü 1000 diyerek saniyeye çevirdik.
            }

            @Override
            public void onFinish() {
                //10 saniye bitince ne yapması gerektiğini buraya yazıyoruz
            }
        }.start();
    }

    public void runnableHandlerOrnek(){
        sayi=0;
        sayac2=(TextView)findViewById(R.id.sayac2);
        basla=(Button)findViewById(R.id.basla);
        durdur=(Button)findViewById(R.id.durdur);
        //baslaya basınca 0dan baslayıp sayacak.Her saniyede bir textView'ı güncelleyeceğiz.
        basla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kronometre açıkken çalışıyoken bi kez daha basılsın istemiyoruz.
               // basla.setClickable(false); //böyle olunca butona basılmıyo
                basla.setEnabled(false);//bunu yapınca butona hem basılmıyo hem de butonun rengi soluyo basılmayacağı anlaşılıyo
                handler=new Handler();
                runnable=new Runnable() {
                    @Override
                    public void run() {
                        //buraya yazdığım herşey benim belirttiğim periyot içersinde olacaktır.
                        //en son handler kullanarak runnable'ı başlatacağız.
                        sayac2.setText("Time: "+sayi);
                        sayi++;
                        //kaç saniyede runnable çalışacak.
                        handler.postDelayed(runnable,1000);//her saniye başı bu işlemi yap diyoruz.
                    }
                };
                handler.post(runnable);
            }
        });
        durdur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basla.setEnabled(true);
                handler.removeCallbacks(runnable);//arka tarafta çalışan runnable'ımızı kapatıyoruz.
                sayi=0;
                //sayac2.setText("Time: "+sayi); //bunu yazınca sıfır oluyo durdurur durdurmaz
                //ama şuan hangisinde durduyduysak o yazıyo zaten
            }
        });
    }
}
