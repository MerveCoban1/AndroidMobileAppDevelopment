package com.example.realmformornegi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    Realm realm;
    EditText kAdi,ad,sifre;
    RadioGroup cinsiyet;
    Button gonder,guncelle;
    String kullaniciAdi,kSifre,isim,kCinsiyet;
    ListView listView;
    Integer pozisyon=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm=Realm.getDefaultInstance();
        viewTanimlamalari();
        ekle();
        pozisyonBul();
    }
    public void viewTanimlamalari(){
        kAdi=findViewById(R.id.kAdi);
        ad=findViewById(R.id.ad);
        sifre=findViewById(R.id.sifre);
        cinsiyet=findViewById(R.id.cinsiyet);
        gonder=findViewById(R.id.gonder);
        guncelle=findViewById(R.id.guncelle);
        listView=findViewById(R.id.listView);
    }
    public void ekle(){
        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //butona tıklandığında girilen verileri bir değişkene aldık.
                kullaniciAdi=kAdi.getText().toString();
                kSifre=sifre.getText().toString();
                isim=ad.getText().toString();
                Integer id=cinsiyet.getCheckedRadioButtonId();//hangisine tıklandıysa id sinin aldık.
                RadioButton hangiCinsiyet=(RadioButton)findViewById(id);
                kCinsiyet=hangiCinsiyet.getText().toString();
                kaydet();
                kAdi.setText("");
                ad.setText("");
                sifre.setText("");
            }
        });
        guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmResults<KisiBilgileri> list=realm.where(KisiBilgileri.class).findAll();
                final KisiBilgileri kisi=list.get(pozisyon);
                Integer id=cinsiyet.getCheckedRadioButtonId();
                RadioButton buton=findViewById(id);
                final String cinsiyetT=buton.getText().toString();
                final String isimT=ad.getText().toString();
                final String kAdiT=kAdi.getText().toString();
                final String sifreT=sifre.getText().toString();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        kisi.setCinsiyet(cinsiyetT); //transaction içinde yazalım ki bir bütün olarak set edilsin.
                        kisi.setSifre(sifreT);
                        kisi.setIsim(isimT);
                        kisi.setKullaniciAdi(kAdiT);
                    }
                });
                goster();
            }
        });
    }
    public void kaydet(){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                KisiBilgileri kisi=realm.createObject(KisiBilgileri.class);
                kisi.setIsim(isim);
                kisi.setCinsiyet(kCinsiyet);
                kisi.setKullaniciAdi(kullaniciAdi);
                kisi.setSifre(kSifre);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(), "basarili", Toast.LENGTH_SHORT).show();
                goster();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(getApplicationContext(),"basarisiz",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void goster(){
        RealmResults<KisiBilgileri> bilgiler=realm.where(KisiBilgileri.class).findAll();
        /*for(KisiBilgileri k:bilgiler){
            Log.i("gelenler",k.toString());
        }*/
        if(bilgiler.size()>0){
            adapter adapter=new adapter(bilgiler,getApplicationContext());
            listView.setAdapter(adapter);

        }
    }
    public void pozisyonBul(){
        //hangi listView'ımıza tıkladığımızı bulalım
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            RealmResults<KisiBilgileri> list=realm.where(KisiBilgileri.class).findAll();
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("pozisyon",""+position);
                alert(position);
                kAdi.setText(list.get(position).getKullaniciAdi());
                ad.setText(list.get(position).getIsim());
                sifre.setText(list.get(position).getSifre());
                pozisyon=position;
            }
        });
    }
    public void sil(final int position){
        //once veritabanındaki verileri çekelim
        final RealmResults<KisiBilgileri> gelenList=realm.where(KisiBilgileri.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                KisiBilgileri kisi=gelenList.get(position);
                kisi.deleteFromRealm();
                goster();//goster'i çağırmak listeyi yenilemek demektir yoksa siler ama hala gösterir o kaydı ekranda .
            }
        });
    }
    public void alert(final int position){
        LayoutInflater inflater=getLayoutInflater();
        //alert diyalogumuza ait xml dosyamızı tanımlamak için view tanımlayacağız.
        View view=inflater.inflate(R.layout.layoutalertdiyalog,null);
        Button evet=(Button) view.findViewById(R.id.evet);
        Button hayir=(Button)view.findViewById(R.id.hayir);
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setView(view);
        //sadece hayır butonuna tıkladığında kapansın istiyorum:
        alert.setCancelable(false);
        //artık alert diyalogumuzu oluşturabiliriz.
        final AlertDialog dialog=alert.create();
        dialog.show();
        evet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sil(position);
                dialog.cancel();
            }
        });
        hayir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

}
