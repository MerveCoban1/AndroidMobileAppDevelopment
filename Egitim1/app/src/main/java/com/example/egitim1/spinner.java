package com.example.egitim1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class spinner extends AppCompatActivity {
    Spinner spinner;
    String[] istanbulIlceleri={"ümraniye","kadıköy","avcılar"};
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner);
        //spinner oluşturalım
        spinner=findViewById(R.id.spinner);
        arrayAdapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,istanbulIlceleri);
        //array adapter tamamlanmış oldu
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),""+spinner.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }
}
