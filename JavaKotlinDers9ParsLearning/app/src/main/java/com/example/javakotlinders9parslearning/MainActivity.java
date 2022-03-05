package com.example.javakotlinders9parslearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;

/*
* pars kullanabilmek için build:gradle appimize
ext {
    parseVersion = "1.17.3"
}
dependencies{
implementation "com.parse:parse-android:$parseVersion"
    implementation "com.parse:parse-fcm-android:$parseVersion"
}  eklemelerimizi yapıyoruz.
*parse'ı projemizde başlatabilmemiz için yeni bir sınıf oluşturmamız gerekiyor.
ayrı bir sınıfta işlem yapmamız gerekiyor.
*Pars server'ı ile nasıl çalışılır?
back4app e giriyoruz internetten. Parse server sitesi bu site
buil new app-server settings-core settings-ordaki verileri parseStarter sınıfına ekliyoruz.
server=parse api address demek. bağlantımızı yapıyoruz.
*manage parse settingte parse versiyonun yani server seçimin var. Betalar önerilmiyor.
*artık parsa veri kaydetmeye hazırız.
*
*
*
*
* */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kullaniciylaGirisYap();

    }

    public void veritabaninaVeriKaydet() {
        ParseObject object = new ParseObject("Fruits");
        /*VERİ KAYDETME*/
        object.put("name", "apple");
        object.put("calories", 100);
        //savenİnBackground'un callbackli olanınında işlem başarılı olursa ya da başarısız olursa gibi seçenekleri de mevcut.
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void veritabanindanVeriCek() {
        /*VERİ OKUMA*/
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Fruits");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    if (objects.size() > 0) {
                        for (ParseObject object : objects) {
                            String objectName = object.getString("name");
                            int objectCalories = object.getInt("calories");
                            System.out.println(objectName);
                            System.out.println(objectCalories);

                        }
                    }
                }
            }
        });
        /*filtreleme de yapabiliriz. Mesela sadece muzu çekmek istiyoruz diyelim
        query.whereEqualTo("name", "apple");
        query.findInBackground();......aynısı yapınca sadece elma olanlar gelicek.*/

    }

    public void kullaniciOlustur() {
        ParseUser user = new ParseUser();//diyip bir kullanıcı oluşturabiliyoruz.
        user.setUsername("James");
        user.setPassword("123456");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    Toast.makeText(getApplicationContext(), "kullanici olusturuldu", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void kullaniciylaGirisYap() {
        ParseUser.logInInBackground("James", "123456", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    Toast.makeText(getApplicationContext(), "Hosgeldin" + user.getUsername(), Toast.LENGTH_LONG).show();

                }
            }
        });


    }


}
