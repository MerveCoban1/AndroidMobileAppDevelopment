package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Models_ılerledikce_verileri_tutuyoruz.IlanVer;

public class MotorPerformans extends AppCompatActivity {
    EditText motorTipiBilgi,motorHacmiBilgi;
    Button ileri,geri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_performans);
        tanimla();
    }
    public void tanimla(){
        motorTipiBilgi=(EditText)findViewById(R.id.motorTipiBilgi);
        motorHacmiBilgi=(EditText)findViewById(R.id.motorHacmiBilgi);
        ileri=(Button) findViewById(R.id.ileri);
        geri=(Button) findViewById(R.id.geri);

        motorTipiBilgi.setText(IlanVer.getMotorTipi());
        motorHacmiBilgi.setText(IlanVer.getMotorHacmi());

        ileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!motorTipiBilgi.getText().toString().equals("") && !motorHacmiBilgi.getText().toString().equals("")){
                    IlanVer.setMotorTipi(motorTipiBilgi.getText().toString());
                    IlanVer.setMotorHacmi(motorHacmiBilgi.getText().toString());

                    Intent intent=new Intent(MotorPerformans.this,Yakit.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Lutfen Tum Alanlari Doldurun",Toast.LENGTH_LONG).show();
                }

            }
        });
        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MotorPerformans.this,AracBilgileri.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });
    }
}
