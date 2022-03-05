package com.example.authdatabaseentegre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class BilgiEkleActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    EditText boy,yas;
    Button ekle;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilgi_ekle);
        tanimla();
        action();
    }
    public void tanimla(){
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("bilgiler/"+user.getUid());  //sistemde giris yapılmış olan kullanıcının idsi
        boy=(EditText)findViewById(R.id.boy);
        yas=(EditText)findViewById(R.id.yas);
        ekle=(Button) findViewById(R.id.ekle);

    }
    public void ekle(String boy,int yas){
        Map map=new HashMap();
        map.put("boy",boy);
        map.put("yas",yas);
        reference.setValue(map);
    }
    public void action(){
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String boyum=boy.getText().toString();
                int yasim=Integer.parseInt(yas.getText().toString());
                ekle(boyum,yasim);
            }
        });
    }
}
