package com.example.firebasemesajlasma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
    DatabaseReference reference;
    String userId, otherId, user, other, key;
    List<MesajModel> list;
    RecyclerView recyclerView;
    MesajAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.listView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        adapter = new MesajAdapter(list, MainActivity.this);
        recyclerView.setAdapter(adapter);

        reference = FirebaseDatabase.getInstance().getReference();
        userId = "1";
        otherId = "2";
        sendMessage("selam", userId);
        sendMessage("naber", otherId);
        sendMessage("iyi", userId);
        sendMessage("bende iyi", otherId);
        load();
    }

    public void sendMessage(String mesajBody, String id) {
        //bir mesaj gönderdiğimizde iki tane kayıt olacak çümkü kullanıcı mesajını silebilir.
        user = "messages/" + userId + "/" + otherId;
        other = "messages/" + otherId + "/" + userId;
        key = reference.child("messages").child(userId).child(otherId).push().getKey();
        Map map = send(mesajBody, id);
        Map map2 = new HashMap();
        map2.put(user + "/" + key, map);
        map2.put(other + "/" + key, map);

        reference.updateChildren(map2, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

            }
        });
    }

    public Map send(String mesajBody, String userId) {
        Map msj = new HashMap();
        msj.put("mesaj", mesajBody);
        msj.put("from", userId);
        return msj;
    }

    public void load() {

        reference.child("messages").child(userId).child(otherId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MesajModel m = snapshot.getValue(MesajModel.class);
                list.add(m);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MesajModel m = snapshot.getValue(MesajModel.class);
                list.add(m);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                MesajModel m = snapshot.getValue(MesajModel.class);
                list.add(m);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
