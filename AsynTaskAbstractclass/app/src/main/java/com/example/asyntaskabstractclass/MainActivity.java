package com.example.asyntaskabstractclass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView sayilar;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        new arkaplan().execute();//ile çalıştırıyoruz.
    }
    public void tanimla(){
        sayilar=(TextView) findViewById(R.id.sayilar);
    }

    class arkaplan extends AsyncTask<Void, Integer, Void> {
        //alttaki fonksiyonların yerinin bir önemi yok hepsi bir bütün içinde çalışıyor.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();//diğer işlemleri suoer()'in altına yap.
            progressDialog=new ProgressDialog(MainActivity.this);//parametre olarak context istiyor.mainactivity.this  verdik.
            progressDialog.show();
            //işlem öncesi çalıştırılacak metot.
            //mesela bun da bir proggress diyalog açtık
        }

        @Override
        protected Void doInBackground(Void... voids) {  //bu sınıfın olmazsa olmazı Asyntask sınıfını extends etmesi ve bu metoda sahip olmasıdır.Olmak zorunda.
            for (int i = 0; i < 100; i++) {
                publishProgress(i);//ikisinin de değişken tipi aynı olmalı dikkat et.
                //sayilar.setText(String.valueOf(i));//bu yanlış oluyor bu fonksiyon sadece gerekli arkaplan işlemlerini yapıyor.
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //publishProgress();//ilerlemeyi onprogressUpdate'e gönderiyoruz
            return null;
            //arkaplan işlemlerini burada yapıyoruz.
            //bu fonk.asyn<> içindeki ilk parametreyi parametre olarak alıyor.3.sünü de dönüş tipi olarak alıyor.
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values); //şimdi values olarak gelen ifadeyi işlememiz gerekiyor.
            Integer idegeri=values[0];
            Log.i("idegeri:",""+idegeri); //log'un messajı String tipinde olmalıdır
            sayilar.setText(String.valueOf(idegeri));
             //bir işlemin durumunun takip edilmesini sağlayan metot
            //bunun parametresi de asyntask'ın 2. parameteresiyle aynı oolmalı onu alıyor.e

            //NOT: Stringe çevirmek için String.valueOf(idegeri); yapabiliriz. ya da ""+idegeri ile birleştirebiliriz.
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //arkaplan işlemi bittikten sonra yapılacaklar için
            //bu diyalogu kapatabiliriz artık
            sayilar.setText("nihayet bitti");
            progressDialog.cancel();

        }


        @Override
        protected void onCancelled() {
            super.onCancelled();
            //arkaplan işlemleri iptal edildiğinde bir fonksiyon çalışcak bu da
        }
    }
}


