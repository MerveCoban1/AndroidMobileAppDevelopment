package com.example.javakotlinders8instaclonefirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

/*RecyclerView ListView'dan daha verimli çalıştığı için onu kullanacağız.
 * internetten veri çekeceğimiz zaman firebase kullanacağımız zaman vs daha verimli olması için kullanılıyor
 * listView kullanılması önerilmiyor.*/

public class FeedActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore firebaseFirestore;
    ArrayList<String> userEmailFromDb;
    ArrayList<String> userResimFromDb;
    ArrayList<String> userBaslikFromDb;
    ArrayList<String> userYorumFromDb;
    RecyclerView recyclerView;
    FeedRecyclerAdapter feedRecyclerAdapter;

    @Override //menüyü bağlamamız için kullanıyoruz bu metodu
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflater:bir xml dosyası oluşturduğumuzda eğer onu kodla bağlayacaksak kullanıyoruz.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.insta_options_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cikisYapItem) {
            auth.signOut();
            Intent intentToCikis = new Intent(FeedActivity.this, MainActivity.class);
            startActivity(intentToCikis);
            finish();
        } else if (item.getItemId() == R.id.uploadItem) {
            Intent intentToUpload = new Intent(FeedActivity.this, UploadActivity.class);
            startActivity(intentToUpload);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        tanimla();
        verileriCek();
        verileriGoster();

    }

    private void tanimla() {

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userBaslikFromDb = new ArrayList<>();
        userEmailFromDb = new ArrayList<>();
        userResimFromDb = new ArrayList<>();
        userYorumFromDb = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);

    }

    public void verileriCek() {

        CollectionReference collectionReference = firebaseFirestore.collection("Posts");

        //verileri filtrelemek istiyosak where kullanıyoruz.

        /*collectionReference.whereEqualTo("baslik","merve").addSnapshotListener()...
        dediğimizde basliği merve olan verileri çekiyoruz sadece.*/

        // Bizde verileri tarihe göre çekeceğiz Yani verileri dizeceğiz.
        //           Tarihe göre, azalarak çektik. En Yeni atılan en üstte çıkıyor.

        collectionReference.orderBy("tarih", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(getApplicationContext(), error.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                }
                if (value != null) {
                    for (DocumentSnapshot snapshot : value.getDocuments()) {
                        Map<String, Object> data = snapshot.getData();

                        String baslik = (String) data.get("baslik");
                        String resim = (String) data.get("resim");
                        String useremail = (String) data.get("useremail");
                        String yorum = (String) data.get("yorum");
                        //aldığımız verileri bi dizide tutacağız.

                        userBaslikFromDb.add(baslik);
                        userEmailFromDb.add(useremail);
                        userResimFromDb.add(resim);
                        userYorumFromDb.add(yorum);
                        //şimdi bunları recyclerViewda gösterelim

                        //diziye bişey eklendiğinde recyclerView'ı uyarmamız gerekiyor.
                        feedRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    public void verileriGoster() {

        recyclerView.setLayoutManager(new LinearLayoutManager(FeedActivity.this));
        feedRecyclerAdapter = new FeedRecyclerAdapter(userEmailFromDb, userBaslikFromDb, userYorumFromDb, userResimFromDb);
        recyclerView.setAdapter(feedRecyclerAdapter);

    }

}
