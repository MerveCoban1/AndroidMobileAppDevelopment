package com.example.authenticationupdatepassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    Button cikisYap,sifreDegis,dogrulama,reset,sil;
    TextView emailKontrol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        if (user==null){//sistemde kullanıcı yok şuan
            //intentin ilk parametresi için: eger inner classtaysak sadece this yazmak hata veriyor. MainActivity.this yazmalıyız.
            Intent intent=new Intent(this,GirisYap.class);
            startActivity(intent);
            finish();
        }
        cikisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                //tekrar main aktivitiye gelicek zaten user olmadığı için main de otomatik giriş yap aktivitisine gönderecek.
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        sifreDegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SifreDegisActivity.class);
                startActivity(intent);
                finish();
            }
        });
        emailKontrol.setText(""+user.isEmailVerified());  //ilk etapta false yazıyor . Çünkü email doğrulatmadık. //bu boolean döndürüyor.
        dogrulama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailDogrulama();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sil();
            }
        });
    }
    public void tanimla(){
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        cikisYap=(Button)findViewById(R.id.cikisYap);
        sil=(Button)findViewById(R.id.hesapsil);
        dogrulama=(Button)findViewById(R.id.dogrulama);
        sifreDegis=(Button)findViewById(R.id.sifreDegis);
        reset=(Button)findViewById(R.id.reset);
        emailKontrol=(TextView)findViewById(R.id.emailKontrol);
    }
    public void emailDogrulama(){
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }
    public void reset(){
        auth.sendPasswordResetEmail(user.getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(),"sifre sifirlama linki gonderildi",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void sil(){
        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent intent=new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
