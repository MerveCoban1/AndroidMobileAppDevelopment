package com.example.firebaseauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    EditText gmail, password;
    Button kayit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ilkOrnek();
        //ikinciOrnek();
        tanimla();
        auth = FirebaseAuth.getInstance();
        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail= gmail.getText().toString();
                gmail.setText("");
                String sifre=password.getText().toString();
                password.setText("");
                if(!mail.equals("")&&!sifre.equals("")){
                    loginUser(mail,sifre);
                }
            }
        });

    }

    public void ilkOrnek() {
        auth = FirebaseAuth.getInstance();//tüm kullanıcı işlemlerini bu nesne barındıracak.
        //bir kullanıcı var mı yok mu ona bakmalıyız.
        user = auth.getCurrentUser(); //basit düzeyde bir kontrol yapıyoruz.
        if (user == null) {
            Intent intent = new Intent(this, GirisYapActivity.class);
            startActivity(intent);
        }
    }

    public void ikinciOrnek() {
        tanimla();
        auth = FirebaseAuth.getInstance();
        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kadi, sifre;
                kadi = gmail.getText().toString();
                sifre = password.getText().toString();
                if (!kadi.equals("") && !sifre.equals("")) {
                    kayitOl(kadi, sifre);
                    gmail.setText("");
                    password.setText("");
                }
            }
        });
    }

    public void tanimla() {
        gmail = (EditText) findViewById(R.id.gmail);
        password = (EditText) findViewById(R.id.password);
        kayit = (Button) findViewById(R.id.kayitOl);
    }

    private void kayitOl(String kad, String sif) {
        //addCompleteListener eğer ki bir tamamlanma olursa fonksiyonu, herhangi bir hata olursa diye bir işlem
        auth.createUserWithEmailAndPassword(kad, sif).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //egerki basarili bir sekilde gerçekleşmişse buraya gelecek.
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "kayit islemi basarili", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, Test.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "hata meydana geldi", Toast.LENGTH_LONG).show();
                    Log.i("sonuc", task.toString());
                }
            }
        });
    }

    private void loginUser(String gmail, String pass) {
        //addComplete fonksiyonunu işlem doğru gerçekleşiyo mu gerçekleşmiyo mu onnu kontrol etmek için yazıyoruz.
        auth.signInWithEmailAndPassword(gmail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "boyle bir kullanici yok", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
