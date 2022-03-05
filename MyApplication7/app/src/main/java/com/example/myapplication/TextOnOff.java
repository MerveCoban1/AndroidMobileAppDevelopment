package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;

public class TextOnOff extends Activity {
EditText editTextInput;
Button buttonYazdir;
ToggleButton toggleButtonOff;
TextView textViewYazi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_onoff);
        //layout öğeleri ile değişkenler ilişkilendirilir
        editTextInput=(EditText)findViewById(R.id.editTextInput);
        buttonYazdir=(Button)findViewById(R.id.buttonYazdir);
        toggleButtonOff=(ToggleButton)findViewById(R.id.toggleButtonOff);
        textViewYazi=(TextView)findViewById(R.id.textViewYazi);
        //toggle buton işlemi
        toggleButtonOff.setOnClickListener(new View.OnClickListener() {  //Onclick yazınca fonk oluşuyo
            @Override
            public void onClick(View v) {
                //toggle butonu durumlarına göre işlemler
                if(toggleButtonOff.isChecked()){
                    editTextInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                }else{
                    editTextInput.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });
        //yazdır butonunun işlemini yapacağız
        buttonYazdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String alinanYazi=editTextInput.getText().toString();
                textViewYazi.setText(alinanYazi);
            }
        });


    }
}
