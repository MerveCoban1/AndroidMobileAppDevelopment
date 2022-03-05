package com.example.firebaseegitim2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref1, ref2;
    List<Messages> list;
    RecyclerView recyclerView;
    MessagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ilkOrnek();
        //ikinciOrnek();
        verileriCek();


    }

    public void verileriCek() {
        list = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        adapter = new MessagesAdapter(list, getApplicationContext());
        recyclerView.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();
        ref1 = database.getReference();

        ref1.child("messages").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Messages messages = snapshot.getValue(Messages.class);
                adapter.notifyDataSetChanged(); //eger bir güncelleme olursa adapterda da güncelleme oluyor .
                list.add(messages);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void ikinciOrnek() {
        database = FirebaseDatabase.getInstance();
        ref1 = database.getReference();
        String mainChild = "messages/";

        //key üreteceğiz random olarak.
        // Log.i("gelenKey",GetKeys.getKey(ref1));
        String key = GetKeys.getKey(ref1);

        Map icerik = new HashMap();
        icerik.put("icerik", "merhaba");
        icerik.put("date", "03.06.12");

        Map add = new HashMap();
        add.put(mainChild + key, icerik);
        ref1.updateChildren(add, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(getApplicationContext(), "oldu", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void ilkOrnek() {
        database = FirebaseDatabase.getInstance();
        ref1 = database.getReference("nrhn");
        ref2 = database.getReference("abdllh");
        //ref1=database.getReference("Users/nrhn");  bu kullanımda users ın çocugu nrhn olur nrhnın cocuklaı da asagıdakiler olur.
        UserDetails userDetails = new UserDetails("nurhan", "coban", "40");
        ref1.setValue(userDetails);//bu şekilde pojo sınıfımızı oldugu gibi nrhn nin çocugu yaptık.
        Map map = new HashMap();
        map.put("sehir", "amasya");
        map.put("ulke", "turkiye");
        map.put("tcNo", "0000000");
        ref2.setValue(map); //bu şekilde hashmaplerle de yapabiliriz.
    }
}
