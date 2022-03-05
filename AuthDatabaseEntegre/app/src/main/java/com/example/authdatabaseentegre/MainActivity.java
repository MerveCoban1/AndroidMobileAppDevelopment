package com.example.authdatabaseentegre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    TextView bilgiText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        if(user==null){
            Intent i=new Intent(MainActivity.this,KayitOlActivity.class);
            startActivity(i);
            finish(); //cihazımızın geri tuşuna basılınca tekrar main aktivitiye gelmemesi için finishliyoruz.
        }
    }
    public void tanimla(){
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        bilgiText=(TextView)findViewById(R.id.bilgiText);
        bilgiText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,BilgiEkleActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
