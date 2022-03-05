package com.example.authenticationupdatepassword;

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

public class GirisYap extends AppCompatActivity {
    EditText kullaniciEmail, kullaniciSifre;
    Button girisYap;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);
        tanimla();
        action();

    }

    public void tanimla() {
        auth = FirebaseAuth.getInstance();
        kullaniciEmail = (EditText) findViewById(R.id.kullaniciEmail);
        kullaniciSifre = (EditText) findViewById(R.id.kullaniciSifre);
        girisYap = (Button) findViewById(R.id.girisYap);
    }

    public void action() {
        girisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kad = kullaniciEmail.getText().toString();
                String ksifre = kullaniciSifre.getText().toString();
                if (!kad.equals("") && !ksifre.equals("")) {
                    login(kad, ksifre);
                } else {
                    Toast.makeText(getApplicationContext(), "email ya da sifre bos olamaz", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void login(String kad, String ksifre) {
        auth.signInWithEmailAndPassword(kad,ksifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent=new Intent(GirisYap.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "girerken bir hata olustu", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
