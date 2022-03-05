package com.example.nbetieczane;
/*
* projemizde yapmamız gereken ilk şey token'ı bulup almak.
* projede arkaplan işlemlerini handlerlarla yapacağız.Handlerların viewlara ulaşma yetkileri var.
Asyntasklar kullanmayacağız.
* javascript arayüzlerinden köprülerinden yararlanacağız.Tokenları almak için.Tokenlar javascript tarafında çünkü
her istekte bir token oluşur.Yani öncelikle token'a ulaşacağız.
*Token'a ulaşmak için öncelikle bir sınıf oluşturalım bu sınıf javascript köprüsü kuracak.JavaScript interfacelerinden yararlanarak ilgili token'ı bulacağız.
*xml içine girmiyoruz.Arka tarafta bir webView oluşturacağız.
*
*token'ımızı alıp oluşturduğumuzda artık handler'a mesaj olarak yollayabiliriz.handlerda da
* html pars etme işlemi yapıp içinde çağıracağız.
*
*eczane'nin bilgilerini de çekeceğiz adıdır mailidir adresidir falan filan bunları çekeceğimiz için bir model sınıfı oluşturamlıyız.
*bu bilgileri pars ettik. sonra listviewda göstereceğiz.
*
*
*
*
* */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import Models.Eczane;
import Models.EczaneDetay;

public class MainActivity extends AppCompatActivity{
    String tokenText="";
    WebView webView;
    Spinner spinner;
    Document document;
    List<EczaneDetay> EczaneList;
    EczaneAdapter eczaneAdapter;
    ListView listView;
    Button listeleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView=new WebView(getApplicationContext());
        webView.getSettings().setJavaScriptEnabled(true); //webView'ın javascript ayarlamasını aktif etmemiz gerekiyor.
        //şimdi webViewımıza javascript interfacesi verelim
        webView.addJavascriptInterface(new JsBridge(),"Android");//artık sınıfımızı javascript interfacesi olarak webView'ımıza verdik.
        //name'e android yazılmalı.Başka bir şey yazıldığında yanlış oluyor.
        this.getToken();
        listeleButton=(Button)findViewById(R.id.listeleButton);
        listView=(ListView) findViewById(R.id.listView);
        final String ilceler[]={"Adalar","Arnavutköy","Ataşehir"};//bu şekilde tüm iller yazılı olacak.
        final int ilceid[]={1,33,34};
        spinner=(Spinner) findViewById(R.id.ilceSpinner);
        //şimdi spinner'a ait array adapterını oluşturalım
        ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ilceler);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(dataAdapter);
        listeleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item=spinner.getSelectedItem().toString();
                int index=Integer.parseInt(String.valueOf(java.util.Arrays.asList(ilceler).indexOf(item)));
                int id=ilceid[index];
                getEczane(String.valueOf(id));
            }
        });


    }

    public void getEczane(String id){
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:window.Android.htmlEczaneDetay("+
                        "'<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
            }
        });
        webView.loadUrl("http://apps.istanbulsaglik.gov.tr/Eczane/nobetci?id="+id+"&token="+tokenText);

    }

    public void getToken(){
        //webViewın token olmasını sağlayan işlem:
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //işlemimiz bittikten sonra ne yapacağımız.
                view.loadUrl("javascript:window.Android.htmlContentForToken("+
                        "'<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
            }
        });
        webView.loadUrl("http://apps.istanbulsaglik.gov.tr/Eczane");//nereden token alacağız onu yazıyoruz.
/*öncelikle webview token alacağımız urlyi load ediyor.daha sonra sayfanın dönen kaynağını gönderiyoruz alttaki sınıfa
* sonra içerisine dönen kaynakla token'ı pars edeceğiz. htmlContentForToken metotu ile.*/
    }

    class JsBridge extends MainActivity {
        @JavascriptInterface //javascript interfacelerinden destek alabilmek için bu annotationu kullanark bir metot oluşturacağız.
        public void htmlContentForToken(String str) {  //bu stringden kastımız bir url
            //bu urlyi de arka planda bir webView kullanarak gerçekleştireceğiz.Çünkü dedikki handlerlar viewlarla iletişime geçebiliyor.
            //token'ı alacağız splitler yardımıyla
            String token[] = str.split("token"); //urlmizin çıktısınız token olan yerini al dedik.

            if(token.length>1){
                //demekki elimizde bir veri var.
                //token'ımız s[1]de loglayarak nerede olduğunu gördük.
                String token2[]=token[1].split(Pattern.quote("}"));//token'ımızın }'dan öndeki yerini aldık.sadece adresin kalmasını istiyoruz yani token'ın
                tokenText=token2[0].replaceAll(" ","").replaceAll(":","").replaceAll("\"","");
                //şuan tokenımızı aldık.!!!!!
                //mesaj olarak handler'a bu token'ı yollayalım.
                Message message=new Message();//bi token ve bir id gönderceğiz.
                message.what=1; //numaralandırıyoruz.
                message.obj=tokenText;
                handler.sendMessage(message);
            }
        }
        @JavascriptInterface
        public void htmlEczaneDetay(String str){
            Message message=new Message();
            message.what=2;
            message.obj=str;
            handler.sendMessage(message);
        }
    }

    private Handler handler=new Handler(){
        //handle'ımıza token'ı göndereceğiz.token'ı  gönderdiğimizde bu handler arkaplanda hmtl'i pars edecek.
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){//tokenımızı alacağız.
                tokenText=(String) msg.obj;
            }else if(msg.what==2){
                Eczane ec=parseHtml((String)msg.obj);
                EczaneList=ec.getEczaneDetay();
                eczaneAdapter=new EczaneAdapter(EczaneList,MainActivity.this,MainActivity.this);
                listView.setAdapter(eczaneAdapter);
            }
        }
    };
    private Eczane parseHtml(String htmlKaynak){
        document=Jsoup.parse(htmlKaynak);
        //ilgili bir etiketi seçmek için document.select() den yararlanıyoruz.
        Elements table=document.select("table.ilce-nobet-detay");
        Elements ilceDetay=table.select("caption>b"); //ilcedetayımızın artık get(0) ve get(1)'i var
        Eczane eczane=new Eczane();
        eczane.setTarih(ilceDetay.get(0).text()); //tarihimizi aldık.
        eczane.setIlceIsmi(ilceDetay.get(1).text());
        Elements eczaneDetayElement=document.select("table.nobeetci-eczane");
        List<EczaneDetay> eczaneDetayList=new ArrayList<>();
        for (Element el:eczaneDetayElement){
            EczaneDetay eczaneDetay=getEczaneDetay(el);
            if (eczaneDetay!=null){
                eczaneDetayList.add(eczaneDetay);
            }
        }
        eczane.setEczaneDetay(eczaneDetayList);
        return eczane;
    }
    public EczaneDetay getEczaneDetay(Element el){
        EczaneDetay eczaneDetay=new EczaneDetay();
        Elements eczaneIsmiTag=el.select("thead");
        String eczaneIsmi=eczaneIsmiTag.select("div").attr("title");
        eczaneDetay.setEczaneIsmı(eczaneIsmi);

        Elements trTags=el.select("tbody>tr");
        Elements adresTags=trTags.select("tr#adres");
        String adres=adresTags.select("label").get(1).text();
        eczaneDetay.setAdres(adres);

        Elements telTags=trTags.select("tr#Tel");
        String tel=telTags.select("label").get(1).text();
        eczaneDetay.setTelefon(tel);

        Element faxTags=trTags.get(2);
        String fax=faxTags.select("label").get(1).text();
        if (!fax.equals("")) {
            eczaneDetay.setFax(fax);
        }

        Element adresTarifTags=trTags.get(3);
        String adresTarif=adresTarifTags.select("label").get(1).text();
        if (!adresTarif.equals("")) {
            eczaneDetay.setTarif(adresTarif);
        }

        return eczaneDetay;
    }





}
