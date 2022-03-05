package com.example.alertdiyalog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button uyeOl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uyeOl=(Button)findViewById(R.id.uyeOl);
        uyeOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diyalogAc();
            }
        });

    }
    public void diyalogAc(){

        LayoutInflater inflater=getLayoutInflater(); //activityy classında olduğumuz için direk böyle oluşturabiliyoruz.
        View view=inflater.inflate(R.layout.alertlayoutu,null);
        //şimdi alertlayout xmlindeki viewlarımızı tanımlayalım
        final EditText kisim=view.findViewById(R.id.kisim);
        final EditText sifre=view.findViewById(R.id.sifre);
        Button ol=view.findViewById(R.id.ol);
                //alert diyalogunun gösterilmesi işlemini yapalım:
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setView(view);
                  //ekranın boş bir yerine tıkladığımıda kapansın istemiyorsak:
        alert.setCancelable(false);
        final AlertDialog diyalog=alert.create();
        ol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),kisim.getText().toString()+" "+sifre.getText().toString(),Toast.LENGTH_LONG).show();
                diyalog.cancel();//alert diyalogum kapansın diye
            }
        });
        diyalog.show();
    }
}
