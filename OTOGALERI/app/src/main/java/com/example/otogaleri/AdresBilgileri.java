package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Models_ılerledikce_verileri_tutuyoruz.IlanVer;

public class AdresBilgileri extends AppCompatActivity {
    EditText sehirBilgi,ilceBilgi,mahalleBilgi;
    Button ileri,geri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adres_bilgileri);
        tanimla();
    }
    public void tanimla(){
        sehirBilgi=(EditText)findViewById(R.id.sehirBilgi);
        ilceBilgi=(EditText)findViewById(R.id.ilceBilgi);
        mahalleBilgi=(EditText)findViewById(R.id.mahalleBilgi);
        ileri=(Button)findViewById(R.id.ileri);
        geri=(Button)findViewById(R.id.geri);

        sehirBilgi.setText(IlanVer.getSehir());
        ilceBilgi.setText(IlanVer.getIlce());
        mahalleBilgi.setText(IlanVer.getMahalle());

        ileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sehirBilgi.getText().toString().equals("") && !ilceBilgi.getText().toString().equals("") && !mahalleBilgi.getText().toString().equals("")){
                    IlanVer.setSehir(sehirBilgi.getText().toString());
                    IlanVer.setIlce(ilceBilgi.getText().toString());
                    IlanVer.setMahalle(mahalleBilgi.getText().toString());

                    Intent intent=new Intent(AdresBilgileri.this,AracBilgileri.class);
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
                Intent intent=new Intent(AdresBilgileri.this,IlanTuru.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });
    }
}
