package com.example.firebaseegitimi1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref1,id,isim,soyisim,yas,durum,x,y;
    Button ekle;
    EditText key,value;
    TextView result;
    List<User> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //firebaseIlkOrnek();
        //firebaseIkinciOrnek();
        //firebaseUcuncuOrnek();
        firebaseDorduncuOrnek();

    }

    public void firebaseDorduncuOrnek(){
        list=new ArrayList<>();
        database=FirebaseDatabase.getInstance();
        ref1=database.getReference("mrvcbn");
        ref1.setValue("dsfs");

        id=ref1.child("id");
        id.setValue("1");
        isim=ref1.child("isim");
        isim.setValue("merve");
        soyisim=ref1.child("soyisim");
        soyisim.setValue("coban");
        yas=ref1.child("yas");
        yas.setValue(22);
        durum=ref1.child("durum");
        durum.setValue(true);

        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User d=dataSnapshot.getValue(User.class); //aldığımız valueler bu sınıf tipinde olsun diyoruz.
                //Log.i("bak",d.getIsim()); //bu şekilde valuemizin valuesine ulaştık.
                list.add(d);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //bir keye nasıl çocuk key eklenir?
    public void firebaseUcuncuOrnek(){
        database=FirebaseDatabase.getInstance();
        ref1=database.getReference("mrvcbn");
        //eger ana keye valuesi varken çocuk key eklersek valuesi kaybolur.
        ref1.setValue("dsfs");

        id=ref1.child("id");
        id.setValue("1");

        isim=ref1.child("isim");
        isim.setValue("merve");

        soyisim=ref1.child("soyisim");
        soyisim.setValue("coban");

        yas=ref1.child("yas");
        yas.setValue(22);  //bunun int oldugunu anlıyor ve int tipinde tutuyor bu degeri.

        durum=ref1.child("durum");
        durum.setValue(true);

        x=ref1.child("x");
        y=x.child("y");
        y.setValue("y");//eger key anakey değilse yani çocuksuz bir keyse eger onun bir valuesi yoksa veritabanında görünmüyor.

        //şimdi çocukları okuyalım
        ref1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.i("sonuclar",dataSnapshot.getValue().toString());
                //bu loglama bize tüm cocukların valuelerini döndürür sadece valueyi döndürüyor keyi değil.
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void firebaseIkinciOrnek(){
        //biz key-value şeklinde yapıyoruz. Ama key-child şeklinde de olur. Mantiken aslında key-valuelerimizin üstünde de bir key var.
        database=FirebaseDatabase.getInstance();
        key=(EditText)findViewById(R.id.key);
        value=(EditText)findViewById(R.id.value);
        result=(TextView) findViewById(R.id.result);
        ekle=(Button) findViewById(R.id.ekle);

        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyText=key.getText().toString();
                String valueText=value.getText().toString();
                ref1=database.getReference(keyText);
                ref1.setValue(valueText);
                key.setText("");
                value.setText("");
            }
        });
        //şimdi databasedeki valuemizi gösterelim.
        ref1.addValueEventListener(new ValueEventListener() {  //addValueListener ile valueleri sürekli dinle diyoruz.
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                result.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void firebaseIlkOrnek(){
        database=FirebaseDatabase.getInstance();//database'in tum ornegini almış olduk.ne kadar key varsa alıyoruz
        //database firebase json tipinde olacağı için keyler vardı.
        //daha sonra bu nesne üzerinden istediğimiz keylere/çocuklara erişebileceğiz.
        ref1=database.getReference("adi");//istediğimiz keyi alabiliriz. Eğer böyle bir key yoksa oluşturulur.
        ref1.setValue("merve");

        //bir keye ait veriyi okumak için
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("bak",dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref1.setValue("ayse");
    }
}
