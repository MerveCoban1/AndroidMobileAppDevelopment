package com.example.htmlparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private static final String url = "https://tr.123rf.com/stok-fotoğraf/web_sitesi.html?sti=mw72rllhqx0igky4yu|";
    //document oluştururken jsoup kullanıyorsun
    Document document;
    TextView textView, h1TextView;
    String h1Elementi;
    Elements h1Element;
    Bitmap bitmap; //resmimizi almak için //yerine picasso da kullanabilirdik
    Elements srcElement;
    String imageUrl;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new getirHtml().execute();
    }

    public class getirHtml extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textView = (TextView) findViewById(R.id.textView);
            h1TextView = (TextView) findViewById(R.id.h1TextView);
            img = (ImageView) findViewById(R.id.resim);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //arkaplan işlemlerini yapacağız
            //html'imize/web sitemize istek atalım
            try {
                document = Jsoup.connect(url).get();//document döndürüyor
                h1Element = document.select("h1");//elements döndürüyor.
                srcElement = document.select("img");//burda html img taginin içindeki src yani resim linkini aldık
                imageUrl = srcElement.attr("src");
                //InputStream input=new java.net.URL(imageUrl).openStream();
                //bitmap= BitmapFactory.decodeStream(input);


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //sonuçların gösterilmesini sağlayacağız
            Log.i("kaynak", "" + document); //şuan sayfamızın kaynak kodunu getirdik.
            textView.setText(document.title());
            h1Elementi = h1Element.text();
            h1TextView.setText(h1Elementi);
            // img.setImageBitmap(bitmap);
            Picasso.with(getApplicationContext()).load(imageUrl).into(img);
        }
    }
}
