package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Models_ılerledikce_verileri_tutuyoruz.IlanVer;

public class AracBilgileri extends AppCompatActivity {
EditText markaBilgi,seriBilgi,modelBilgi;
Button ileri,geri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_bilgileri);
        tanimla();
    }
    public void tanimla(){
        markaBilgi=(EditText)findViewById(R.id.markaBilgi);
        seriBilgi=(EditText)findViewById(R.id.seriBilgi);
        modelBilgi=(EditText)findViewById(R.id.modelBilgi);
        ileri=(Button)findViewById(R.id.ileri);
        geri=(Button)findViewById(R.id.geri);

        markaBilgi.setText(IlanVer.getMarka());
        seriBilgi.setText(IlanVer.getSeri());
        modelBilgi.setText(IlanVer.getModel());

        ileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!markaBilgi.getText().toString().equals("") && !seriBilgi.getText().toString().equals("") && !modelBilgi.getText().toString().equals("")){
                    IlanVer.setMarka(markaBilgi.getText().toString());
                    IlanVer.setSeri(seriBilgi.getText().toString());
                    IlanVer.setModel(modelBilgi.getText().toString());

                    Intent intent=new Intent(AracBilgileri.this,MotorPerformans.class);
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
                Intent intent=new Intent(AracBilgileri.this,AdresBilgileri.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });
    }
}
