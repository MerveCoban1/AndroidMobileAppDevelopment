package com.example.authdatabaseentegre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class KayitOlActivity extends AppCompatActivity {
    EditText kadi, sifre;
    Button kaydol;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);
        tanimla();
        action();
    }

    public void tanimla() {
        auth = FirebaseAuth.getInstance();
        kadi = (EditText) findViewById(R.id.kadi);
        sifre = (EditText) findViewById(R.id.sifre);
        kaydol = (Button) findViewById(R.id.kaydol);
    }

    public void action() {
        kaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kayitOl(kadi.getText().toString(), sifre.getText().toString());
            }
        });
    }

    public void kayitOl(String kullaniciAdi, String kullaniciSifre) {
        auth.createUserWithEmailAndPassword(kullaniciAdi, kullaniciSifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "basarili", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(KayitOlActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "basarili", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
