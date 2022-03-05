package com.example.retrofitornek4activitygecisli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Models.Results;
import RestApi.ManagerAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {
    String id, postid;
    TextView postId2, id2, name2, email2, body2;
    List<Results> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tanimla();
        al();
        istek();
    }

    public void tanimla() {
        postId2 = (TextView) findViewById(R.id.postId2);
        id2 = (TextView) findViewById(R.id.id2);
        name2 = (TextView) findViewById(R.id.name2);
        email2 = (TextView) findViewById(R.id.email2);
        body2 = (TextView) findViewById(R.id.body2);
    }

    public void al() {
        Bundle bundle = getIntent().getExtras();
        postid = bundle.getString("postId");
        id = bundle.getString("id");
    }

    public void istek() {
        list2 = new ArrayList<>();
        Call<List<Results>> call = ManagerAll.getInstance().managerGetirResult(postid, id);
        call.enqueue(new Callback<List<Results>>() {
            @Override
            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                if (response.isSuccessful()) {
                    list2 = response.body();
                    atama(list2);
                }
            }

            @Override
            public void onFailure(Call<List<Results>> call, Throwable t) {

            }
        });
    }

    public void atama(List<Results> list2) {
        postId2.setText("" + list2.get(0).getPostId());  //zaten 1 eleman olduğu için get(0) diyip 0.indexteki değeri alabiliriz.
        id2.setText("" + list2.get(0).getId());
        name2.setText(list2.get(0).getName());
        email2.setText(list2.get(0).getEmail());
        body2.setText(list2.get(0).getBody());
    }
}
