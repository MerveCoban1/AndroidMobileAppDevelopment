package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Models_ılerledikce_verileri_tutuyoruz.IlanVer;

public class IlanBilgileri extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String uyeMailAdresi;
    EditText ilanBaslik,ilanAciklama,ilanUcret;
    Button ileri,geri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_bilgileri);
        tanimla();
    }
    public void tanimla(){
        sharedPreferences=getApplicationContext().getSharedPreferences("giriskayit",0);
        uyeMailAdresi=sharedPreferences.getString("uye_mail",null);
        ilanBaslik=(EditText)findViewById(R.id.ilanBaslik);
        ilanUcret=(EditText)findViewById(R.id.ilanUcret);
        ilanAciklama=(EditText)findViewById(R.id.ilanAciklama);
        ileri=(Button) findViewById(R.id.ileri);
        geri=(Button) findViewById(R.id.geri);

        //eger buraya geri dönüldüyse once önceden yazılmış kaydedilmiş bilgileri tekrar yazılmış olarak orada görüyoruz.
        ilanBaslik.setText(IlanVer.getBaslik());
        ilanAciklama.setText(IlanVer.getAciklama());
        ilanUcret.setText(IlanVer.getUcret());

        ileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ilanBaslik.getText().toString().equals("") && !ilanAciklama.getText().toString().equals("")&& !ilanUcret.getText().toString().equals("")){
                    IlanVer.setBaslik(ilanBaslik.getText().toString());
                    IlanVer.setAciklama(ilanAciklama.getText().toString());
                    IlanVer.setUcret(ilanUcret.getText().toString());
                    IlanVer.setUye_id(uyeMailAdresi);
                    Intent intent=new Intent(IlanBilgileri.this,IlanTuru.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Lutfen Tum Alanlari Doldurun",Toast.LENGTH_LONG).show();
                }
            }
        });
        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IlanBilgileri.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });
    }
}
