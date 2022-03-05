package com.example.otogaleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import Models_ılerledikce_verileri_tutuyoruz.IlanVer;

public class IlanTuru extends AppCompatActivity {
    Button ileri,geri;
    Spinner ilanTuruSpinner,kimdenSpinner;
    List<String> turList;
    List<String > sahipList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_turu);
        listeDoldur();
        tanimla();
    }
    public void tanimla(){
        ileri=(Button)findViewById(R.id.ileri);
        geri=(Button)findViewById(R.id.geri);
        ilanTuruSpinner=(Spinner)findViewById(R.id.ilanTuruSpinner);
        kimdenSpinner=(Spinner)findViewById(R.id.kimdenSpinner);

        ArrayAdapter<String> turListAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,turList);
        turListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ilanTuruSpinner.setAdapter(turListAdapter);
        ArrayAdapter<String> sahipListAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sahipList);
        sahipListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kimdenSpinner.setAdapter(sahipListAdapter);

        ileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IlanVer.setIlan_tipi(ilanTuruSpinner.getSelectedItem().toString());
                IlanVer.setKimden(kimdenSpinner.getSelectedItem().toString());

                Intent intent=new Intent(IlanTuru.this,AdresBilgileri.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                finish();
            }
        });
        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IlanTuru.this,IlanBilgileri.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });
    }
    public void listeDoldur(){
        turList=new ArrayList<>();
        sahipList=new ArrayList<>();
        turList.add("satilik");
        turList.add("kiraik");
        sahipList.add("sahibinden");
        sahipList.add("galeriden");

    }
}
