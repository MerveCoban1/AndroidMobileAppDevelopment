package com.example.activityandlistviewornegi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView text1;
    TextView text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text1=(TextView) findViewById(R.id.text1);
        text2=(TextView) findViewById(R.id.text2);
        al();

    }
    public void al(){
        Bundle bundle=getIntent().getExtras();
        String isim1=bundle.getString("isim");
        String soyisim2=bundle.getString("soyisim");
        text1.setText(isim1);
        text2.setText(soyisim2);
    }
}
