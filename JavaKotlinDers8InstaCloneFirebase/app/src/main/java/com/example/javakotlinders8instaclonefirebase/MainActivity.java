package com.example.javakotlinders8instaclonefirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText emailEditText, sifreEditText;
    Button signInButton, signUpButton;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        kontrol();
        action();
    }

    private void kontrol() {
        //kullanıcı yoksa user null döndürüyormuş
        if (user != null) {
            Intent intent = new Intent(MainActivity.this, FeedActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void action() {
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = emailEditText.getText().toString();
                String sifre = sifreEditText.getText().toString();
                if (!mail.equals("") && !sifre.equals("")) {
                    auth.signInWithEmailAndPassword(mail, sifre).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Intent intent = new Intent(MainActivity.this, FeedActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Bos Alan Birakamazsiniz", Toast.LENGTH_LONG).show();
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = emailEditText.getText().toString();
                String sifre = sifreEditText.getText().toString();
                if (!mail.equals("") && !sifre.equals("")) {
                    //completeListener işlem bitince demek oluyor.
                    auth.createUserWithEmailAndPassword(mail, sifre).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            //işlem başarılıysa
                            Intent intent = new Intent(MainActivity.this, FeedActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //başarılı değilse
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Bos Alan Birakamazsiniz", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void tanimla() {
        emailEditText = findViewById(R.id.emailEditText);
        sifreEditText = findViewById(R.id.sifreEditText);
        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);

        auth = FirebaseAuth.getInstance();
        //getCurrentUser(): güncel olarak giriş yapmış bir kullanıcı varsa git bana onu getir.
        user = auth.getCurrentUser();
    }
}
