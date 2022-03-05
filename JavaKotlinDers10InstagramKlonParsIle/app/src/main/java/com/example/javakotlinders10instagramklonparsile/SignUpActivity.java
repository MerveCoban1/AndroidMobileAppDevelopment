package com.example.javakotlinders10instagramklonparsile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/*
 *parse platformdan build gradle eklemelerimizi yapıyoruz.
 * back4app'den kendi server'ımızı oluşturuyoruz.
 * ParseStarter sınıfımızı server ile bağlıyoruz. Bu sınıfı manifestte tanımlamalısın Dikkat et
 * internet iznini de al
 *
 * */
public class SignUpActivity extends AppCompatActivity {
    EditText kullaniciAdiEditText, sifreEditText;
    Button signInButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        tanimla();
        kontrol();
        action();

    }

    public void tanimla() {
        kullaniciAdiEditText = findViewById(R.id.kullaniciAdiEditText);
        sifreEditText = findViewById(R.id.sifreEditText);
        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);
    }

    public void action() {
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(kullaniciAdiEditText.getText().toString(), sifreEditText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e != null) {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Hosgeldin " + user.getUsername(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignUpActivity.this, FeedActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
                user.setUsername(kullaniciAdiEditText.getText().toString());
                user.setPassword(sifreEditText.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Kullanici Olusturuldu", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignUpActivity.this, FeedActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
    }

    public void kontrol(){
        ParseUser parseUser=ParseUser.getCurrentUser();
        if (parseUser!=null){
            Intent intent = new Intent(SignUpActivity.this, FeedActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
