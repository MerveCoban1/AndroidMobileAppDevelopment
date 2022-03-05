package com.example.mailgonderme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText adres,konu,icerik;
    Button gonder;
    String icerikT,adresT,konuT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        gonder();
    }
    public void tanimla(){
        adres=(EditText) findViewById(R.id.adres);
        konu=(EditText)findViewById(R.id.konu);
        icerik=(EditText)findViewById(R.id.icerik);
        gonder=(Button)findViewById(R.id.gonder);
    }
    public void bilgiAl(){
        icerikT=icerik.getText().toString();
        adresT=adres.getText().toString();
        konuT=konu.getText().toString();
    }
    public void gonder(){
        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bilgiAl();
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL,adresT);
                intent.putExtra(Intent.EXTRA_SUBJECT,konuT);
                intent.putExtra(Intent.EXTRA_TEXT,icerikT);
                startActivity(intent);
            }
        });
    }
}
