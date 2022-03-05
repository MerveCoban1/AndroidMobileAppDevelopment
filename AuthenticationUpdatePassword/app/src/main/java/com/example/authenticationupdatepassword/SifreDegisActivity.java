package com.example.authenticationupdatepassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SifreDegisActivity extends AppCompatActivity {
    EditText sifreYeni;
    Button sifreDegis;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifre_degis);
        tanimla();
    }
    public void tanimla(){
        sifreYeni=(EditText)findViewById(R.id.sifreYeni);
        sifreDegis=(Button)findViewById(R.id.sifreDegistir);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        sifreDegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                degis(sifreYeni.getText().toString());
            }
        });

    }
    public void degis(String yeniSifre){
        user.updatePassword(yeniSifre).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent intent=new Intent(SifreDegisActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
